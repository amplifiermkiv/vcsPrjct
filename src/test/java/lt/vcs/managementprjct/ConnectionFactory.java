package lt.vcs.managementprjct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static ConnectionFactory INSTANCE = null;

    public static ConnectionFactory getInstance() throws SQLException {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionFactory();
        }
        return INSTANCE;
    }

    // unit test use only
    @Deprecated
    public static void setInstance(ConnectionFactory connectionFactory) {
        ConnectionFactory.INSTANCE = connectionFactory;
    }

    public Connection getConnection(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }
}
