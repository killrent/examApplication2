package com.utills.JDBCUtils;

import java.sql.PreparedStatement;

/**
 * Created by Tnecesoc on 2016/10/23.
 * <p>
 * The mission of the profile repository is to implement the 5 main functions below.
 * 1. sign in.
 * 2. sign up.
 * 3. view information.
 * 4. update information.
 * 5. Create invitations.
 * </p>
 * Functions are realized in separated class.
 */
public class GeneralRepository extends DataSource {

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String PARAM = "?useUnicode=true&autoReconnect=true&rewriteBatchedStatements=TRUE";

    private static final String DATA_SOURCE_URL = "jdbc:mysql://localhost:3306/";

    protected static final String DATABASE = "enimaxe";

    static {

        url = DATA_SOURCE_URL + PARAM;

        try {

            Class.forName(DRIVER);

            doSQLAction(() -> {
                PreparedStatement statement = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS `" + DATABASE + "`");
                statement.execute();
            });

            url = DATA_SOURCE_URL + DATABASE + PARAM;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static boolean isAuthorized() {
        return testConnection();
    }

}
