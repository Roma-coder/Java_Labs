package lab5;

import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestDatabaseStructure {

    @Test
    public void testCreateTables() throws DatabaseConnectionException, SQLException {
        try { DatabaseStructure.dropTables(); } catch (Exception ignored) { }
        DatabaseStructure.createTables();
    }



    @Test
    public void testDropTables() throws SQLException {
        try { DatabaseStructure.createTables(); } catch (Exception ignored) { }
        DatabaseStructure.dropTables();
    }

}
