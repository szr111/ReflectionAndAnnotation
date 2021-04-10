package com.yc.reflection;

import com.yc.annotation.DBConnection;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@DBConnection
public class Conncetion {
    public static void main(String[] args) throws ClassNotFoundException {

        Class aClass = Conncetion.class;

        DBConnection db = (DBConnection) aClass.getDeclaredAnnotation(DBConnection.class);
        final String driver = db.driver();
        final String url = db.url();
        final String password = db.password();
        final String user = db.user();


        Class.forName(driver);
        try {
            final Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
