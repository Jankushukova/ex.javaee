package db;

import modals.Post;
import modals.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class DBManager {

    private static Connection connection;

    static {
        connect();
    }

    public static void connect() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nedoinsta?useUnicode=true&serverTimezone=UTC","root", ""
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }





}