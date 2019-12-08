package lab6.dao;

import lab6.DaoException;
import lab6.model.Route;
import lab5.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class RouteDao implements Dao<Route> {
    private static final String GET_BY_ID = "SELECT * FROM route WHERE r_id=?";
    private static final String GET_ALL_ROUTES = "SELECT * FROM route";
    private static final String INSERT_ROUTES = "INSERT INTO route(r_id, r_from, r_to) VALUES(?,?,?)";
    private static final String UPDATE_ROUTES = "UPDATE route SET r_from=?, r_to=? WHERE r_id=?";
    private static final String DELETE_ROUTES = "DELETE FROM route WHERE r_id=?";



    private Connection getConnection() throws DaoException {
        try{
            ConnectionManager connectionManager = new ConnectionManager();
            return connectionManager.createConnection();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<Route> findById(Long id) throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                Route route = new Route(resultSet.getString("r_from"),resultSet.getString("r_to"));
                route.setId(resultSet.getLong("r_id"));
                return Optional.of(route);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Route> findAll() throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL_ROUTES);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Route> routes = new ArrayList<>();
            while (resultSet.next()) {
                Route route = new Route(resultSet.getString("r_from"),resultSet.getString("r_to"));
                route.setId(resultSet.getLong("r_id"));

                routes.add(route);
            }
            return routes;
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Long save(Route route) throws DaoException {
        try {
            Long idResult = null;
            PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_ROUTES, new String[]{"r_id"});
            preparedStatement.setLong(1, route.getId());
            preparedStatement.setString(2, route.getFrom());
            preparedStatement.setString(3, route.getTo());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                idResult = rs.getLong("r_id");
            }
            rs.close();

            return idResult;
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void update(Route route) throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_ROUTES);
            preparedStatement.setString(1, route.getFrom());
            preparedStatement.setString(2, route.getTo());
            preparedStatement.setLong(3, route.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_ROUTES);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }

    }
}
