package com.girish.jdbcInsert;

import java.sql.*;

public class JDBCInsert {
    public static void main(String[] args) {
        Connection myConn;
        Statement myStmt;
        ResultSet myResult;

        String dbUrl = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
        String dbUser = "root";
        String dbPwd = "root";

        try {
            // 1. Getting the connections to database
            myConn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);

            // 2. Creating statements
            myStmt = myConn.createStatement();

            // 3. Inserting a row into a employees database table.
            System.out.println("Inserting a new row to employees database\n");
            int rowsAffected = myStmt.executeUpdate(
                    "INSERT INTO EMPLOYEES" +
                            "(last_name, first_name, email, department, salary) " +
                            "values " +
                            "('Srinivas', 'Girish', 'girish.srinivas@gmail.com', 'IT Department', '120000')");
            myResult = myStmt.executeQuery("SELECT * FROM EMPLOYEES ORDER BY last_name");

            // 4. Navigating through the result set
            while (myResult.next()) {
                System.out.println(myResult.getString("first_name")
                        + " "
                        + myResult.getString("last_name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
