package com.girish.jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcTest {
    public static void main(String[] args) {
        Connection myConn;
        Statement myStmt;
        ResultSet myResult;

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?serverTimezone=UTC",
                                                "root", "root");
            System.out.println("Database connection successful...\n");

            myStmt = myConn.createStatement();

            myResult = myStmt.executeQuery("SELECT * FROM EMPLOYEES");

            while (myResult.next()) {
                System.out.println(myResult.getString("first_name")
                                    + " "
                                    + myResult.getString("last_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
