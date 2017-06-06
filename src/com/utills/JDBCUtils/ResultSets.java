package com.utills.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

/**
 * Created by Tnecesoc on 2017/5/28.
 */
public class ResultSets {

    private static Object getFieldFromColumn(ResultSet resultSet, Field field) throws SQLException {

        String fieldName = field.getName();

        if (field.isAnnotationPresent(SQLColumn.class)) {
            SQLColumn col = field.getAnnotation(SQLColumn.class);
            fieldName = col.value();
        }

        String fieldClassName = field.getType().getSimpleName();

        switch (fieldClassName) {
            case "Boolean":
            case "boolean":
                return resultSet.getBoolean(fieldName);
            case "Byte":
            case "byte":
                return resultSet.getByte(fieldName);
            case "Integer":
            case "int":
                return resultSet.getInt(fieldName);
            case "Float":
            case "float":
                return resultSet.getFloat(fieldName);
            case "Double":
            case "double":
                return resultSet.getDouble(fieldName);
            case "Long":
            case "long":
                return resultSet.getLong(fieldName);
            case "String":
                return resultSet.getString(fieldName);
            case "BigDecimal":
                return resultSet.getBigDecimal(fieldName);
            default:
                return resultSet.getObject(fieldName);
        }
    }


    public static boolean isEmptySet(ResultSet which) throws SQLException {
        boolean ans = !which.next();
        which.first();

        return ans;
    }

    public static <T> T fromRow(ResultSet resultSet, T convertObject, Class<T> baseClass) throws SQLException {

        try {

            if (isEmptySet(resultSet)) {
                return convertObject;
            }

            if (convertObject == null) {
                convertObject = baseClass.newInstance();
            }


            for (Field field : baseClass.getDeclaredFields()) {

                if (field.isAnnotationPresent(SQLIgnore.class)) {
                    continue;
                }

                field.setAccessible(true);
                field.set(convertObject, getFieldFromColumn(resultSet, field));
            }

            return convertObject;

        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("Class " + baseClass.getName() + " must have a default constructor.");
            e.printStackTrace();
        }

        return null;

    }

    public static <T> Collection addAllFromResultSet(ResultSet resultSet, Collection<T> collection, Class<T> baseClass) throws SQLException {

        if (collection == null) {
            collection = new Vector<>();
        }

        try {

            if (isEmptySet(resultSet)) {
                return collection;
            }

            do {
                T tmp = baseClass.newInstance();

                for (Field field : baseClass.getDeclaredFields()) {

                    if (field.isAnnotationPresent(SQLIgnore.class)) {
                        continue;
                    }

                    field.setAccessible(true);
                    field.set(tmp, getFieldFromColumn(resultSet, field));
                }

                collection.add(tmp);

            } while (resultSet.next());

        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("Class " + baseClass.getName() + " must have a default constructor.");
            e.printStackTrace();
        }
        return collection;

    }


}
