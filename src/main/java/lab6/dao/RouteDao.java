package lab6.dao;

import lab6.DaoException;
import lab6.model.Race;
import lab6.model.Route;
import lab5.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.time.LocalTime;
import java.util.*;


public class RouteDao implements Dao<Route> {
    private static final String GET_BY_ID = "SELECT * FROM route WHERE r_id=?";
    private static final String GET_ALL_ROUTES = "SELECT * FROM route";
    private static final String INSERT_ROUTES = "INSERT INTO route(r_from, r_to) VALUES(?,?)";
    private static final String UPDATE_ROUTES = "UPDATE route SET r_from=?, r_to=? WHERE r_id=?";
    private static final String DELETE_ROUTES = "DELETE FROM route WHERE r_id=?";
    private static final String FIND_RACES_OF_ROUTE = "SELECT race_id, race_departurePoint, race_number, race_start, race_finish, race_periodicity, r_id, r_from, r_to FROM route WHERE r_from = ? AND r_to = ? JOIN race ON race_route_id = r_id";


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
            preparedStatement.setString(1, route.getFrom());
            preparedStatement.setString(2, route.getTo());
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

    public Set<Race> findRacesOfRoute(Route route) throws DaoException {
        Set<Race> result = new HashSet<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_RACES_OF_ROUTE);
            preparedStatement.setString(1, route.getFrom());
            preparedStatement.setString(2, route.getTo());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Route newRoute = new Route(rs.getString("r_from"),rs.getString("r_to"));
                newRoute.setId(rs.getLong("r_id"));

                Race race = new Race.Builder()
                        .setId(rs.getLong("race_id"))
                        .setDeparturePoint(rs.getString("race_departurePoint"))
                        .setNumberTrain(rs.getInt("race_number"))
                        .setStartTime(LocalTime.parse(rs.getString("race_start")))
                        .setFinishTime(LocalTime.parse(rs.getString("race_finish")))
                        .setPeriodicity(Race.Periodicity.valueOf(rs.getString("race_periodicity")))
                        .setRoute(newRoute)
                        .build();

                result.add(race);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return result;
    }
}
