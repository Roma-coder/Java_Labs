package lab6.dao;

import lab6.DaoException;
import lab6.model.Race;
import lab5.ConnectionManager;
import lab6.model.Wagon;


import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.*;


public class RaceDao implements Dao<Race> {
    private static final String GET_BY_ID = "SELECT * FROM race WHERE race_id=?";
    private static final String GET_ALL_RACES = "SELECT * FROM race";
    private static final String INSERT_RACES = "INSERT INTO race(race_departurepoint, race_number, race_start, race_finish, race_route_id, race_periodicity) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE_RACES = "UPDATE race SET race_departurepoint=?, race_number=?, race_start=?, race_finish=?, race_route_id=?, race_periodicity=? WHERE race_id=?";
    private static final String DELETE_RACES = "DELETE FROM race WHERE race_id=?";
    private static final String SORT_WAGONS_BY_NUMBERFREE = "SELECT w_id, w_title, w_numberfree FROM wagon WHERE race_id = ? ORDER BY w_numberfree";
    private static final String SEARCH_WAGONS_BY_TITLE = "SELECT w_id, w_title, w_numberfree FROM wagon WHERE race_id = ? AND w_title LIKE ?";
    private static final String SEARCH_WAGONS_BY_NUMBERFREE = "SELECT w_id, w_title, w_numberfree FROM wagon WHERE race_id = ? WHERE w_numberfree = ?";
    private static final String GET_ALL_WAGONS_TITLES = "SELECT w_title FROM wagon WHERE race_id = ?";
    private static final String SEARCH_FREE_WAGONS = "SELECT w_id, w_title, w_numberfree FROM wagon WHERE race_id = ? AND w_numberfree > 0";

    private Connection getConnection() throws DaoException {
        try{
            ConnectionManager connectionManager = new ConnectionManager();
            return connectionManager.createConnection();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Optional<Race> findById(Long id) throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                Race race = new Race.Builder()
                        .setId(resultSet.getLong("race_id"))
                        .setDeparturePoint(resultSet.getString("race_departurepoint"))
                        .setNumberTrain(resultSet.getInt("race_number"))
                        .setStartTime(LocalTime.parse(resultSet.getString("race_start")))
                        .setFinishTime(LocalTime.parse(resultSet.getString("race_finish")))
                        .setPeriodicity(Race.Periodicity.valueOf(resultSet.getString("race_periodicity")))
                        .build();
                return Optional.of(race);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Race> findAll() throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL_RACES);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Race> races = new ArrayList<>();
            while (resultSet.next()) {
                Race race = new Race.Builder()
                        .setId(resultSet.getLong("race_id"))
                        .setDeparturePoint(resultSet.getString("race_departurepoint"))
                        .setNumberTrain(resultSet.getInt("race_number"))
                        .setStartTime(LocalTime.parse(resultSet.getString("race_start")))
                        .setFinishTime(LocalTime.parse(resultSet.getString("race_finish")))
                        .setPeriodicity(Race.Periodicity.valueOf(resultSet.getString("race_periodicity")))
                        .build();

                races.add(race);
            }
            return races;
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Long save(Race race) throws DaoException {
        try {
            Long idResult = null;
            PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_RACES, new String[]{"race_id"});
            preparedStatement.setString(1, race.getDeparturePoint());
            preparedStatement.setInt(2, race.getNumberTrain());
            preparedStatement.setString(3, race.getStartTime().toString());
            preparedStatement.setString(4, race.getFinishTime().toString());
            preparedStatement.setLong(5, race.getRoute().getId());
            preparedStatement.setString(6, race.getPeriodicity().toString());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                idResult = rs.getLong("race_id");
            }
            rs.close();

            return idResult;
        } catch (Exception e) {
            throw new DaoException(e.toString());
        }
    }

    @Override
    public void update(Race race) throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_RACES);
            preparedStatement.setString(1, race.getDeparturePoint());
            preparedStatement.setInt(2, race.getNumberTrain());
            preparedStatement.setString(3, race.getStartTime().toString());
            preparedStatement.setString(4, race.getFinishTime().toString());
            preparedStatement.setLong(5, race.getRoute().getId());
            preparedStatement.setString(6, race.getPeriodicity().toString());
            preparedStatement.setLong(7, race.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_RACES);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    public List<Wagon> getWagonsSortedByNumberFree(Race race) throws DaoException {
        List<Wagon> result = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(SORT_WAGONS_BY_NUMBERFREE);
            ps.setLong(1, race.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Wagon wagon = new Wagon(rs.getString("w_title"), rs.getInt("w_numberfree"));
                wagon.setId(rs.getLong("w_id"));
                result.add(wagon);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return result;
    }

    public Set<Wagon> searchWagonsByTitle(Race race, String title) throws DaoException {
        Set<Wagon> result = new HashSet<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(SEARCH_WAGONS_BY_TITLE);
            ps.setLong(1, race.getId());
            ps.setString(2, title);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Wagon wagon = new Wagon(rs.getString("w_title"), rs.getInt("w_numberfree"));
                wagon.setId(rs.getLong("w_id"));
                result.add(wagon);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return result;
    }

    public Set<Wagon> searchWagonsByTitles(Race race, String ... titles) throws DaoException {
        Set<Wagon> result = new HashSet<>();
        for (String title : titles) {
            Set<Wagon> searchByTitleResult = searchWagonsByTitle(race, title);
            result.addAll(searchByTitleResult);
        }
        return result;
    }

    public Set<Wagon> searchWagonsWithNumberFree(Race race, Integer numberFree) throws DaoException {
        Set<Wagon> result = new HashSet<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(SEARCH_WAGONS_BY_NUMBERFREE);
            ps.setLong(1, race.getId());
            ps.setInt(2, numberFree);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Wagon wagon = new Wagon(rs.getString("w_title"), rs.getInt("w_numberfree"));
                wagon.setId(rs.getLong("w_id"));
                result.add(wagon);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return result;
    }

    public Set<String> getAllWagonsTitles(Race race) throws DaoException {
        Set<String> result = new HashSet<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(GET_ALL_WAGONS_TITLES);
            ps.setLong(1, race.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("w_title"));
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return result;
    }

    public Set<Wagon> searchFreeWagons(Race race) throws DaoException {
        Set<Wagon> result = new HashSet<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(SEARCH_FREE_WAGONS);
            ps.setLong(1, race.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Wagon wagon = new Wagon(rs.getString("w_title"), rs.getInt("w_numberfree"));
                wagon.setId(rs.getLong("w_id"));
                result.add(wagon);
            }
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return result;
    }

}
