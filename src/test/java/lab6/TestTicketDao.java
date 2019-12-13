package lab6;

import lab5.DatabaseConnectionException;
import lab5.DatabaseStructure;
import lab6.dao.TicketDao;
import lab6.model.Ticket;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class TestTicketDao {

    Ticket ticket;
    TicketDao ticketDao;

    @BeforeClass
    public void beforeClass() throws DatabaseConnectionException, SQLException {
        try {
            DatabaseStructure.dropTables();
        } catch (SQLException ignored) { }
        DatabaseStructure.createTables();
        ticketDao = new TicketDao();
    }

    @Test
    public void testInsert() throws DaoException {
        ticket = new Ticket(LocalDate.of(2008,4,23), 20);
        Long id = ticketDao.save(ticket);
        ticket.setId(id);
    }

    @Test(dependsOnMethods = "testInsert")
    public void testFindById() throws DaoException {
        Optional<Ticket> routeOptional = ticketDao.findById(ticket.getId());
        Assert.assertTrue(routeOptional.isPresent());
        Assert.assertEquals(ticket, routeOptional.get());
    }

    @Test(dependsOnMethods = "testInsert")
    public void testFindAll() throws DaoException {
        Object[] actual = ticketDao.findAll().toArray();
        Object[] expected = { ticket };
        Assert.assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = {"testInsert","testFindById","testFindAll"})
    public void testUpdate() throws DaoException {
        ticket.setDate(LocalDate.of(2009,4,13));
        ticketDao.update(ticket);
        ticket.setTicketPrice(40);
    }

    @Test(dependsOnMethods = {"testInsert","testUpdate"})
    public void testDelete() throws DaoException {
        ticketDao.delete(ticket.getId());
    }
}

