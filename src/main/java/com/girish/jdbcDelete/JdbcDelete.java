package com.girish.jdbcDelete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcDelete {
    public static void main(String[] args) {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myResult = null;

        String dbUrl = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
        String dbUser = "root";
        String dbPwd = "root";

        try {
            // 1. Getting the connections to database
            myConn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);

            // 2. Creating statements
            myStmt = myConn.createStatement();

            // 2qa. Displaying a row of data for Jonh Doe before update
            System.out.println("Before Deletion...");
            displayEmployee(myStmt, "John", "Doe");

            // 3. updating a row into a employees database table.
            System.out.println("Deleting a row in employees database\n");
            int rowsAffected = myStmt.executeUpdate(
                    "DELETE FROM EMPLOYEES " +
                            "WHERE last_name = 'Doe' AND first_name = 'John'");
            myResult = myStmt.executeQuery("SELECT * FROM EMPLOYEES ORDER BY last_name");

            // 4. Navigating through the result set
            System.out.println("After Deleting...");
            displayEmployee(myStmt, "John", "Doe");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(myConn, myStmt, myResult);
        }
    }

    private static void displayEmployee(Statement myStmt, String firstName, String lastName) {
        try {
            String sql = "SELECT * FROM EMPLOYEES WHERE first_name = '" + firstName +"'" +
                    " AND last_name = '" +lastName +"'";
            ResultSet myResult = myStmt.executeQuery(sql);

            if (!myResult.next()) {
                System.out.println("Employee not found: " + firstName + " " + lastName);
            } else {
                while (myResult.next()) {
                    System.out.println(myResult.getString("first_name")
                            + " "
                            + myResult.getString("last_name")
                            + " "
                            + myResult.getString("email"));
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void close(Connection myConn, Statement myStmt, ResultSet myResult) {
        try {
            if(null != myConn){
                myConn.close();
            }

            if(null != myStmt){
                myStmt.close();
            }

            if(null != myResult) {
                myResult.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
