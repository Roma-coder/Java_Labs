package lab6;

import lab5.DatabaseConnectionException;
import lab5.DatabaseStructure;
import lab6.dao.RouteDao;
import lab6.model.Route;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.SQLException;
import java.util.Optional;

public class TestRouteDao {

    Route route;
    RouteDao routeDao;

    @BeforeClass
    public void beforeClass() throws DatabaseConnectionException, SQLException {
        try {
            DatabaseStructure.dropTables();
        } catch (SQLException ignored) { }
        DatabaseStructure.createTables();
        routeDao = new RouteDao();
    }

    @Test
    public void testInsert() throws DaoException {
        route = new Route("From", "To");
        Long id = routeDao.save(route);
        route.setId(id);
    }

    @Test(dependsOnMethods = "testInsert")
    public void testFindById() throws DaoException {
        Optional<Route> routeOptional = routeDao.findById(route.getId());
        Assert.assertTrue(routeOptional.isPresent());
        Assert.assertEquals(route, routeOptional.get());
    }

    @Test(dependsOnMethods = "testInsert")
    public void testFindAll() throws DaoException {
        Object[] actual = routeDao.findAll().toArray();
        Object[] expected = { route };
        Assert.assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = {"testInsert","testFindById","testFindAll"})
    public void testUpdate() throws DaoException {
        route.setFrom("NewFrom");
        routeDao.update(route);
        route.setFrom("From");
    }

    @Test(dependsOnMethods = {"testInsert","testUpdate"})
    public void testDelete() throws DaoException {
        routeDao.delete(route.getId());
    }
}
