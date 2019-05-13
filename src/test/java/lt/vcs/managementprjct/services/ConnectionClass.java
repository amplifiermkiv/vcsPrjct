package lt.vcs.managementprjct.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    public Connection connection;

    public Connection connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C://SQL/CargoDB.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}