package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connector
{
    private static Connection conn = null;
    public static Connection getConnection()
    {
        String url = "jdbc:mysql://localhost:3306/pbo_db";
            String user = "root";
            String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e)
        {
            System.out.println("MySQL driver not found.");
            e.printStackTrace();
        } catch (SQLException e)
        {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
        return conn;
    }
}