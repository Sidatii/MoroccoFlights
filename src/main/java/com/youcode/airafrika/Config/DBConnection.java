package com.youcode.airafrika.Config;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    @Getter
    @Setter
    private static Connection conn;
    private static DBConnection instance;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection establishConnection() {
        if (conn == null) {
            try {
                Properties props = new Properties();
                try (InputStream in = DBConnection.class.getResourceAsStream("/db.properties")){
                    if (in != null)
                        props.load(in);
                    else
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Resource 'db.properties' is not found");
                }catch (NullPointerException | IOException exception) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred While Loading Props", exception);
                }
                String url = props.getProperty("url");
                String dbname = props.getProperty("dbname");
                String username = props.getProperty("username");
                String password = props.getProperty("password");

                Class.forName("org.postgresql.Driver");
                setConn(DriverManager.getConnection(url + dbname, username, password));

                if (conn != null) {
                    System.out.println("Connection to PostgreSQL database established.");
                }

            } catch (ClassNotFoundException | SQLException e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred", e);
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, "An Error Occurred", e);
            }
        }
    }
}
