package lab6.dao;

import lab6.DaoException;
import lab6.model.Ticket;
import lab5.ConnectionManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Ticket>{
    private static final String GET_BY_ID = "SELECT * FROM ticket WHERE t_id=?";
    private static final String GET_ALL_TICKETS = "SELECT * FROM ticket";
    private static final String INSERT_TICKETS = "INSERT INTO ticket(t_date, t_price) VALUES(?,?)";
    private static final String UPDATE_TICKETS = "UPDATE ticket SET t_date=?, t_price=? WHERE t_id=?";
    private static final String DELETE_TICKETS = "DELETE FROM ticket WHERE t_id=?";

    private Connection getConnection() throws DaoException {
        try{
            ConnectionManager connectionManager = new ConnectionManager();
            return connectionManager.createConnection();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<Ticket> findById(Long id) throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                Ticket ticket = new Ticket(resultSet.getDate("t_date").toLocalDate(),resultSet.getInt("t_price"));
                ticket.setId(resultSet.getLong("t_id"));
                return Optional.of(ticket);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Ticket> findAll() throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL_TICKETS);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                Ticket ticket = new Ticket(resultSet.getDate("t_date").toLocalDate(),resultSet.getInt("t_price"));
                ticket.setId(resultSet.getLong("t_id"));

                tickets.add(ticket);
            }
            return tickets;
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Long save(Ticket ticket) throws DaoException {
        try {
            Long idResult = null;
            PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_TICKETS, new String[]{"t_id"});
            preparedStatement.setDate(1, Date.valueOf(ticket.getDate()));
            preparedStatement.setInt(2, ticket.getTicketPrice());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                idResult = rs.getLong("t_id");
            }
            rs.close();

            return idResult;
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void update(Ticket ticket) throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_TICKETS);
            preparedStatement.setDate(1, Date.valueOf(ticket.getDate()));
            preparedStatement.setInt(2, ticket.getTicketPrice());
            preparedStatement.setLong(3, ticket.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_TICKETS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }

    }
}
