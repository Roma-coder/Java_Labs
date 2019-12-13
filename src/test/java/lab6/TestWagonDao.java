package lab6;

import lab5.DatabaseConnectionException;
import lab5.DatabaseStructure;
import lab6.dao.WagonDao;
import lab6.model.Wagon;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Optional;

public class TestWagonDao {

    private Wagon wagon;
    private WagonDao wagonDao;

    @BeforeClass
    public void beforeClass() throws DatabaseConnectionException, SQLException {
        try {
            DatabaseStructure.dropTables();
        } catch (SQLException ignored) { }
        DatabaseStructure.createTables();
        wagonDao = new WagonDao();
    }

    @Test
    public void testInsert() throws DaoException {
        wagon = new Wagon("My Wagon", 2);
        Long id = wagonDao.save(wagon);
        wagon.setId(id);
    }

    @Test(dependsOnMethods = "testInsert")
    public void testFindById() throws DaoException {
        Optional<Wagon> routeOptional = wagonDao.findById(wagon.getId());
        Assert.assertTrue(routeOptional.isPresent());
        Assert.assertEquals(wagon, routeOptional.get());
    }

    @Test(dependsOnMethods = "testInsert")
    public void testFindAll() throws DaoException {
        Object[] actual = wagonDao.findAll().toArray();
        Object[] expected = {wagon};
        Assert.assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = {"testInsert","testFindById","testFindAll"})
    public void testUpdate() throws DaoException {
        wagon.setTitle("New Title");
        wagonDao.update(wagon);
    }

    @Test(dependsOnMethods = {"testInsert","testUpdate"})
    public void testDelete() throws DaoException {
        wagonDao.delete(wagon.getId());
    }
}
