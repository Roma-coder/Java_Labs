package lab5;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;

public class TestConnectionManager {

    private ConnectionManager connectionManager;

    @BeforeTest
    public void beforeTest() {
        connectionManager = new ConnectionManager();
    }

    @Test
    public void testConnection() throws DatabaseConnectionException {
        Connection connection = connectionManager.createConnection();
        Assert.assertNotNull(connection);
    }

}
