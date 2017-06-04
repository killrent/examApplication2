package com.utills.JDBCUtils;

import java.sql.SQLException;

/**
 * Created by Tnecesoc on 2016/10/23.
 *
 * Callback Function used in Datasource.
 */
@FunctionalInterface
public interface SQLAction {

    void act() throws SQLException;

}
