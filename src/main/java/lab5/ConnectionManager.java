package lab5;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private Properties properties;
    private Connection con = null;

    {
        properties = new Properties();
    }

    public ConnectionManager() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection createConnection() throws DatabaseConnectionException {
        try {
            properties.load(getClass().getResourceAsStream("/database.properties"));
            con = DriverManager.getConnection(
                    properties.getProperty("connection.url"),
                    properties.getProperty("connection.login"),
                    properties.getProperty("connection.password")
            );
        }
        catch (SQLException | IOException ex) {
            throw new DatabaseConnectionException(ex.getMessage());
        }
        return con;
    }

    public void closeConnection() {
        try {
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
