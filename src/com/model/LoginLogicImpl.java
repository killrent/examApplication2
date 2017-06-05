package com.model;

import com.entity.PaperBean;
import com.entity.UserTransferBean;
import com.entity.relation.UserRPaper;
import com.utills.JDBCUtils.GeneralRepository;
import com.model.Interfaces.LoginLogic;
import com.utills.JDBCUtils.ResultSets;

import javax.jws.soap.SOAPBinding;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginLogicImpl extends GeneralRepository implements LoginLogic {

    public LoginLogicImpl() {

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


    }

    @Override
    public boolean checkEmail(String email) {

        boolean ans[] = new boolean[1];

        ans[0] = false;

        doSQLAction(() -> {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM `user` WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            ans[0] = !resultSet.next();
            resultSet.close();
        });
        return ans[0];
    }

    @Override
    public UserTransferBean signUp(String email, String pwd) {
        UserTransferBean ans[] = new UserTransferBean[1];

        ans[0] = null;

        doSQLAction(() -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO `user` (email, password) VALUE (?, ?)");
            statement.setString(1, email);
            statement.setString(2, pwd);
            statement.execute();
            statement.close();

            statement = connection.prepareStatement(
                    "SELECT * FROM `user` WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            statement.close();
        });

        return ans[0];
    }

    @Override
    public UserTransferBean signIn(String email, String pwd) {
        UserTransferBean ans[] = new UserTransferBean[1];

        ans[0] = null;

        doSQLAction(() -> {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM `user` WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, pwd);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                resultSet.close();
                statement.close();
                return;
            }

            ans[0] = ResultSets.fromRow(resultSet, null, UserTransferBean.class);
            int id = resultSet.getInt("id");
            resultSet.close();
            statement.close();

            statement = connection.prepareStatement(
                    "SELECT * FROM `paper_use` WHERE who = ?");

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            ResultSets.addAllFromResultSet(resultSet, ans[0].getUserRPapers(), UserRPaper.class);

            resultSet.close();
            statement.close();

            statement = connection.prepareStatement(
                    "SELECT DISTINCT * " +
                            "FROM paper_use, paper " +
                            "WHERE paper_use.which = paper.id " +
                            "  AND paper_use.who = ? " +
                            "ORDER BY paper_use.submit_time");

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            ResultSets.addAllFromResultSet(resultSet, ans[0].getPaperBean(), PaperBean.class);
            resultSet.close();
            statement.close();
        });

        return ans[0];
    }
}