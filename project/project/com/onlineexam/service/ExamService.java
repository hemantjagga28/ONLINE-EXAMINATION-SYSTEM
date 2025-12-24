package com.onlineexam.service;

import com.onlineexam.model.Question;
import com.onlineexam.util.DBUtil;
import java.sql.*;
import java.util.*;

public class ExamService {

    public List<Question> getQuestionsFromDB() {

        List<Question> list = new ArrayList<>();

        String sql = "SELECT * FROM question";
        try (Connection con = DBUtil.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Question(
                        rs.getString("question"),
                        rs.getString("optiona"),
                        rs.getString("optionb"),
                        rs.getString("optionc"),
                        rs.getString("optiond"),
                        rs.getString("correct_answer")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.shuffle(list);
        return list.subList(0, Math.min(10, list.size()));
    }

    public void saveResultToDB(String roll, String name, int score, int total) {

        String sql = "INSERT INTO result VALUES (NULL, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, roll);
            ps.setString(2, name);
            ps.setInt(3, score);
            ps.setInt(4, total);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
