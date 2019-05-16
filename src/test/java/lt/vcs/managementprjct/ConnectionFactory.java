package lt.vcs.managementprjct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    static Connection instance;
    public static Connection getFactory(String URL) throws SQLException {

        if (instance == null) {
            return DriverManager.getConnection("jdbc:sqlite:C://SQL/CargoDB.db");
            //DriverManager == ConnectionFactory.getFactory
        } else {
            return instance;
        }
    }

    //unit test use only
    @Deprecated
    public static void setConnection(Connection connection){
        ConnectionFactory.instance = connection;
    }
}
