package com.utills.JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Tnecesoc on 2016/9/6.
 *
 * General template of database connector.
 */
public abstract class DataSource {

    protected static String url;

    protected static Connection connection = null;

    protected static Statement statement = null;

    protected static boolean testConnection() {
        try {
            initConnection();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void initConnection() throws SQLException {
        connection = DriverManager.getConnection(url, RootAccount.getUsername(), RootAccount.getPassword());
    }

    private static void closeConnection() throws SQLException {
        connection.close();
    }

    protected static void doSQLAction(SQLAction action) {
        try {
            initConnection();
            action.act();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
