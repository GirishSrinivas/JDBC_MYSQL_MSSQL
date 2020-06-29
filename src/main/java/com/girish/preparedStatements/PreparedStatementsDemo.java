package com.girish.preparedStatements;

import java.sql.*;

public class PreparedStatementsDemo {
    public static void main(String[] args) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myResult = null;

        String dbUrl = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
        String dbUser = "root";
        String dbPwd = "root";

        try {
            // 1. Getting the connections to database
            myConn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);

            String selectQuery = "SELECT * " +
                                    "FROM EMPLOYEES " +
                                    "WHERE salary > ? " +
                                    "AND department = ?";

            // 2. PreparedStatement
            myStmt = myConn.prepareStatement(selectQuery);

            // 3. Set the parameters
            myStmt.setDouble(1, 80000);
            myStmt.setString(2, "Legal");

            // 4. Execute the query
            myResult = myStmt.executeQuery();

            // 5. Display the result
            displayQueryResult(myResult);

            /**
             * Reuse the prepare statement salary > 25000 AND department = 'HR'
             */
            System.out.println("\nReuse the prepare statement salary > 25000 AND department = 'HR'");
            // 6. Set the parameters
            myStmt.setDouble(1, 25000);
            myStmt.setString(2, "HR");

            // 7. Execute the query
            myResult = myStmt.executeQuery();

            // 8. Display the result
            displayQueryResult(myResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(myConn, myStmt, myResult);
        }

    }

    private static void displayQueryResult(ResultSet myResult) {
        try {
            int count = 0;
             while (myResult.next()) {
                 count++;
                    System.out.println(myResult.getString("first_name") + " "
                            + myResult.getString("last_name") + " "
                            + myResult.getString("email") + " "
                            + myResult.getDouble("salary") + " "
                            + myResult.getString("department"));
             }

             if (count == 0) {
                 System.out.println("No Data found...");
             }
        } catch (Exception e) {
            e.printStackTrace();
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
