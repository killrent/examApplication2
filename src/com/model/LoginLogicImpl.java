//package com.model;
//
//import com.utills.JDBCUtils.GeneralRepository;
//import com.model.Interfaces.LoginLogic;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//
//public class LoginLogicImpl extends GeneralRepository {
//
//    private static final String TABLE = "user";
//
//    public LoginLogicImpl() {
//
//        doSQLAction(() -> {
//            PreparedStatement statement = connection.prepareStatement(
//                    "CREATE TABLE IF NOT EXISTS user " +
//                    "(\n" +
//                    "  id INT(11) PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,\n" +
//                    "  name VARCHAR(20)," +
//                    "  email VARCHAR(255) UNIQUE NOT NULL," +
//                    "  password VARCHAR(20) NOT NULL" +
//                    ")");
//
//            statement.execute();
//        });
//
//
//    }
//
//    @Override
//    public boolean checkEmail(String email) {
//
//        boolean ans[] = new boolean[1];
//
//        ans[0] = false;
//
//        doSQLAction(() -> {
//            PreparedStatement statement = connection.prepareStatement(
//                    "SELECT * FROM `" + TABLE + "` WHERE email = ?");
//            statement.setString(1, email);
//            ResultSet resultSet = statement.executeQuery();
//
//            ans[0] = !resultSet.next();
//            resultSet.close();
//        });
//
//        System.out.print("调用成功！");
//        return ans[0];
//    }
//
//    @Override
//    public boolean signUp(String email, String pwd) {
//        boolean ans[] = new boolean[1];
//
//        ans[0] = false;
//
//        doSQLAction(() -> {
//            PreparedStatement statement = connection.prepareStatement(
//                    "INSERT INTO `" + TABLE + "` (email, password) VALUE (?, ?)");
//            statement.setString(1, email);
//            statement.setString(2, pwd);
//            statement.execute();
//            ans[0] = true;
//        });
//
//        return ans[0];
//    }
//
//    @Override
//    public boolean signIn(String email, String pwd) {
//        boolean ans[] = new boolean[1];
//
//        ans[0] = false;
//
//        doSQLAction(() -> {
//            PreparedStatement statement = connection.prepareStatement(
//                    "SELECT * FROM `" + TABLE + "` WHERE email = ? AND password = ?");
//            statement.setString(1, email);
//            statement.setString(2, pwd);
//            ResultSet resultSet = statement.executeQuery();
//
//            ans[0] = resultSet.next();
//            resultSet.close();
//        });
//
//        return ans[0];
//    }
//}