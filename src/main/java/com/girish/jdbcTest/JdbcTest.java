package com.girish.jdbcTest;

import java.sql.*;

public class JdbcTest {
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myResult = null;

        try {
            // 1. Getting the connections to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?serverTimezone=UTC",
                                                "root", "root");
            System.out.println("Database connection successful...\n");

            // 2. Creating statements
            myStmt = myConn.createStatement();

            // 3. Executing the select query
            myResult = myStmt.executeQuery("SELECT * FROM EMPLOYEES");

            // 4. Navigating through the result set
            while (myResult.next()) {
                System.out.println(myResult.getString("first_name")
                                    + " "
                                    + myResult.getString("last_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myConn.close();
            myResult.close();
            myStmt.close();
        }
    }
}
