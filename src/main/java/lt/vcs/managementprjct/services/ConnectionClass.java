package lt.vcs.managementprjct.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionClass {
    public Connection connection;
    public Statement statement;


    public Connection connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C://SQL/CargoDB.db");
        } catch (SQLException e) {
            e.getMessage();
        }
        return connection;
    }

    public Connection connect1() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C://SQL/CargoDB.db", "root", "");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.getMessage();
        }
        return connection;
    }
}