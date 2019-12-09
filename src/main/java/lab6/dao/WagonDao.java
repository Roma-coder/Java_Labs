package lab6.dao;

import lab6.DaoException;
import lab6.model.Ticket;
import lab6.model.Wagon;
import lab5.ConnectionManager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.*;

public class WagonDao implements Dao<Wagon>{
    private static final String GET_BY_ID = "SELECT * FROM wagon WHERE w_id=?";
    private static final String GET_ALL_WAGONS = "SELECT * FROM wagon";
    private static final String INSERT_WAGONS = "INSERT INTO wagon(w_id, w_title, race_id, w_numberfree) VALUES(?,?,?,?)";
    private static final String UPDATE_WAGONS = "UPDATE wagon SET w_title=?, race_id=?, w_numberfree=? WHERE w_id=?";
    private static final String DELETE_WAGONS = "DELETE FROM wagon WHERE w_id=?";
    private static final String GET_TICKETS_CHEAPER_THAN_PRICE = "SELECT t_id, t_date, t_price FROM ticket WHERE w_id = ? AND t_price <= ?";

    private Connection getConnection() throws DaoException {
        try{
            ConnectionManager connectionManager = new ConnectionManager();
            return connectionManager.createConnection();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<Wagon> findById(Long id) throws DaoException {
        try (
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                Wagon wagon = new Wagon(resultSet.getString("w_title"),resultSet.getInt("w_numberfree"));
                wagon.setId(resultSet.getLong("w_id"));
                return Optional.of(wagon);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Wagon> findAll() throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL_WAGONS);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Wagon> wagons = new ArrayList<>();
            while (resultSet.next()) {
                Wagon wagon = new Wagon(resultSet.getString("w_title"),resultSet.getInt("w_numberfree"));
                wagon.setId(resultSet.getLong("w_id"));

                wagons.add(wagon);
            }
            return wagons;
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Long save(Wagon wagon) throws DaoException {
        try {
            Long idResult = null;
            PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_WAGONS, new String[]{"w_id"});
            preparedStatement.setLong(1, wagon.getId());
            preparedStatement.setString(2, wagon.getTitle());
            preparedStatement.setInt(3, wagon.getNumberFree());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                idResult = rs.getLong("w_id");
            }
            rs.close();

            return idResult;
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void update(Wagon wagon) throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_WAGONS);
            preparedStatement.setString(2, wagon.getTitle());
            preparedStatement.setInt(2, wagon.getNumberFree());
            preparedStatement.setLong(3, wagon.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_WAGONS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    public Set<Ticket> getTicketsCheaperThanPrice(Wagon wagon, Integer price) throws DaoException {
        Set<Ticket> result = new HashSet<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_TICKETS_CHEAPER_THAN_PRICE);
            preparedStatement.setLong(1, wagon.getId());
            preparedStatement.setInt(2, price);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getDate("t_date").toLocalDate(), rs.getInt("t_price"));
                ticket.setId(rs.getLong("t_id"));
                result.add(ticket);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return result;
    }
}
