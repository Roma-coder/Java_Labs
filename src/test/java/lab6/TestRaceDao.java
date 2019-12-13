package lab6;

import lab5.DatabaseConnectionException;
import lab5.DatabaseStructure;
import lab6.dao.RaceDao;
import lab6.dao.RouteDao;
import lab6.model.Race;
import lab6.model.Route;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Optional;

public class TestRaceDao {

    private Race race;
    private RaceDao raceDao;

    @BeforeClass
    public void beforeClass() throws DatabaseConnectionException, SQLException {
        try {
            DatabaseStructure.dropTables();
        } catch (SQLException ignored) { }
        DatabaseStructure.createTables();
        raceDao = new RaceDao();
    }

    @Test
    public void testInsert() throws DaoException {
        Route route = new Route("1", "2");
        RouteDao routeDao = new RouteDao();
        route.setId(routeDao.save(route));

        race = new Race.Builder()
                .setNumberTrain(1)
                .setDeparturePoint("DPoint")
                .setFinishTime(LocalTime.of(1, 20))
                .setPeriodicity(Race.Periodicity.Friday)
                .setStartTime(LocalTime.of(5, 6))
                .setRoute(route)
                .build();
        Long id = raceDao.save(race);
        race.setId(id);
    }

    @Test(dependsOnMethods = "testInsert")
    public void testFindById() throws DaoException {
        Optional<Race> routeOptional = raceDao.findById(race.getId());
        Assert.assertTrue(routeOptional.isPresent());
        Assert.assertEquals(race, routeOptional.get());
    }

    @Test(dependsOnMethods = "testInsert")
    public void testFindAll() throws DaoException {
        Object[] actual = raceDao.findAll().toArray();
        Object[] expected = {race};
        Assert.assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = {"testInsert","testFindById","testFindAll"})
    public void testUpdate() throws DaoException {
        race.setNumberTrain(2);
        raceDao.update(race);
    }

    @Test(dependsOnMethods = {"testInsert","testUpdate"})
    public void testDelete() throws DaoException {
        raceDao.delete(race.getId());
    }

}
