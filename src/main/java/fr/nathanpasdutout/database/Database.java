package fr.nathanpasdutout.database;

import org.sqlite.JDBC;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Database {

    private String path = JDBC.PREFIX;

    private Connection conn = null;

    public Database(String path) {
        this.path += path;

        try {
            File file = new File(path);
            if(file.createNewFile() && !file.isDirectory()) {
                this.init();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void init() {
        try {
            Connection conn = DriverManager.getConnection(this.path);

            Class.forName("org.sqlite.JDBC");
            if(conn != null) {
                Statement state =  conn.createStatement();
                state.execute(
                        "CREATE TABLE user (discord_id INT PRIMARY KEY, puuid VARCHAR(80) NOT NULL UNIQUE)"
                );

                state.close();
                conn.close();
            }

        } catch(SQLException e) {
            System.err.println("Error while initializing database: " + e.getMessage());
        } catch(ClassNotFoundException e) {
            System.err.println("Class 'org.sqlite.JDBC' not found");
        }
    }

    public void connection() {
        try {
            this.conn = DriverManager.getConnection(this.path);

            if(this.conn == null) {
                throw new SQLException();
            }
        } catch(SQLException e) {
            System.err.println("Database connection as failed: " + e.getMessage());
        }
    }

    public ResultSet sendQuery(String query) {
        try {
            Statement state = this.conn.createStatement();
            ResultSet result = null;

            if(query.split(" ")[0].equalsIgnoreCase("SELECT")) {
                 result = state.executeQuery(query);
            } else {
                state.execute(query);
            }

            try {
                state.close();
            } catch(SQLException e) {
                System.err.println("Error while closing statement: " + e.getMessage());
            }

            return result;
        } catch(SQLException e) {
            System.err.println("SQL Request as failed: " + e.getMessage());
        }

        return null;
    }

    public void close() {
        try {
            this.conn.close();
        } catch(SQLException e) {
            System.err.println("Error while closing database connection: " + e.getMessage());
        }
    }

    public String getPath() {
        return this.path;
    }
}
