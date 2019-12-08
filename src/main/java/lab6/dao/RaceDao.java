package lab6.dao;

import lab6.DaoException;
import lab6.model.Race;
import lab5.ConnectionManager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class RaceDao implements Dao<Race> {
    private static final String GET_BY_ID = "SELECT * FROM race WHERE race_id=?";
    private static final String GET_ALL_RACES = "SELECT * FROM race";
    private static final String INSERT_RACES = "INSERT INTO race(race_id, race_departurepoint, race_number, race_start, race_finish, race_route_id, race_periodicity) VALUES(?,?,?,?,?,?,?)";
    private static final String UPDATE_RACES = "UPDATE race SET race_departurepoint=?, race_number=?, race_start=?, race_finish=?, race_route_id=?, race_periodicity=? WHERE race_id=?";
    private static final String DELETE_RACES = "DELETE FROM race WHERE race_id=?";

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
            preparedStatement.setLong(1, race.getId());
            preparedStatement.setString(2, race.getDeparturePoint());
            preparedStatement.setInt(3, race.getNumberTrain());
            preparedStatement.setString(4, race.getStartTime().toString());
            preparedStatement.setString(5, race.getFinishTime().toString());
            preparedStatement.setString(6, race.getPeriodicity().toString());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                idResult = rs.getLong("race_id");
            }
            rs.close();

            return idResult;
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
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
            preparedStatement.setString(5, race.getPeriodicity().toString());
            preparedStatement.setLong(6, race.getId());

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
}
