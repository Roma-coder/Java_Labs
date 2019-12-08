package lab6;

import lab6.dao.RouteDao;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestRouteDao {


    @Test
    public void testfindById() throws DaoException {
        RouteDao routeDao = new RouteDao();
        routeDao.findById(1L);
    }
}
