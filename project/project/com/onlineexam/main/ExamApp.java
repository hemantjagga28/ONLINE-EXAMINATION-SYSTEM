package com.onlineexam.main;

import com.onlineexam.model.Question;
import com.onlineexam.service.ExamService;
import java.util.*;

public class ExamApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExamService service = new ExamService();
        String roll;
        while (true) {
            System.out.print("Enter Roll Number: ");
            roll = sc.nextLine().trim();
            if (roll.matches("\\d+")) {
                break;
            }
            System.out.println("Invalid! Roll Number must contain only numbers.");
        }
        String name;
        while (true) {
            System.out.print("Enter Name: ");
            name = sc.nextLine().trim();
            if (name.matches("[a-zA-Z ]+")) {
                break;
            }
            System.out.println("Invalid! Name must contain only letters.");
        }
        List<Question> questions = service.getQuestionsFromDB();
        int score = 0;
        for (Question q : questions) {
            System.out.println(q.question);
            System.out.println("A) " + q.a);
            System.out.println("B) " + q.b);
            System.out.println("C) " + q.c);
            System.out.println("D) " + q.d);
            String ans;
            while (true) {
                System.out.print("Answer (A/B/C/D): ");
                ans = sc.nextLine().toUpperCase();
                if (ans.matches("[ABCD]"))
                    break;
                System.out.println("Only A/B/C/D allowed");
            }
            if (ans.equals(q.correct))
                score++;
        }

        service.saveResultToDB(roll, name, score, questions.size());
        System.out.println("Score: " + score + "/" + questions.size());
    }
}