package com.utills.JDBCUtils;

/**
 * Created by Tnecesoc on 2016/10/23.
 */
public class RootAccount {

    private final static String username = "root";
    private static String password = "";

    static String getUsername() {
        return username;
    }

    static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        RootAccount.password = password;
    }

}
