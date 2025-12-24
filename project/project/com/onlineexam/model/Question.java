package com.onlineexam.model;

public class Question {
    public String question, a, b, c, d, correct;

    public Question(String q, String a, String b, String c, String d, String correct) {
        this.question = q;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correct = correct;
    }
}
