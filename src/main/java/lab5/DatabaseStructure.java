package lab5;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseStructure {

    private static String CREATE_ROUTE = "CREATE TABLE route(r_id SERIAL NOT NULL PRIMARY KEY," +
            "r_from VARCHAR(20) NOT NULL," +
            "r_to VARCHAR(20) NOT NULL)";
    private static String DROP_ROUTE = "DROP TABLE route";

    private static String CREATE_TICKET = "CREATE TABLE ticket(t_id SERIAL NOT NULL PRIMARY KEY," +
            "t_date DATE NOT NULL," +
            "w_id INT REFERENCES wagon(w_id) ON DELETE CASCADE," +
            "t_price INT NOT NULL)";
    private static String DROP_TICKET = "DROP TABLE ticket";

    private static String CREATE_WAGON = "CREATE TABLE wagon(w_id SERIAL NOT NULL PRIMARY KEY," +
            "w_title VARCHAR(20) NOT NULL," +
            "race_id INT REFERENCES race(race_id) ON DELETE CASCADE," +
            "w_numberfree INT NOT NULL)";
    private static String DROP_WAGON = "DROP TABLE wagon";

    private static String CREATE_RACE = "CREATE TABLE race(race_id SERIAL NOT NULL PRIMARY KEY ," +
            "race_departurePoint varchar(20) NOT NULL," +
            "race_number INT NOT NULL," +
            "race_start varchar(20) NOT NULL," +
            "race_finish varchar(20) NOT NULL," +
            "race_route_id INT NOT NULL REFERENCES route(r_id) ON DELETE CASCADE," +
            "race_periodicity varchar(20) NOT NULL)";
    private static String DROP_RACE = "DROP TABLE race";

    public static void createTables() throws DatabaseConnectionException, SQLException {
        ConnectionManager connectionManager = new ConnectionManager();
        try (Connection connection = connectionManager.createConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(CREATE_ROUTE);
            statement.execute(CREATE_RACE);
            statement.execute(CREATE_WAGON);
            statement.execute(CREATE_TICKET);

        }
        catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    public static void dropTables() throws SQLException {
        ConnectionManager connectionManager = new ConnectionManager();
        try (Connection connection = connectionManager.createConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(DROP_TICKET);
            statement.execute(DROP_WAGON);
            statement.execute(DROP_RACE);
            statement.execute(DROP_ROUTE);
        }
        catch (SQLException | DatabaseConnectionException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

}
