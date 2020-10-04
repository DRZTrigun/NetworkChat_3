package ru.geekbrains.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/userArr", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("SWW during connection",e);
        }
    }

}
