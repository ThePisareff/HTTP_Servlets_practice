package dao;

import entity.Flight;
import entity.FlightStatus;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {

    private static final FlightDao INSTANCE = new FlightDao();

    private static final String SAVE_SQL = """
            INSERT INTO flight(flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code, aircraft_id, status) 
            VALUES (?,?,?,?,?,?,?);
            """;

    private static final String FIND_ALL_SQL = """
            SELECT * FROM flight
            """;

    private FlightDao(){};

    public static FlightDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Flight save(Flight entity) {
//        try (Connection connection = ConnectionManager.open();
//             PreparedStatement prepareStatement = connection.prepareStatement(SAVE_SQL)) {
//
//            prepareStatement.setString(1, );
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Flight> findAll() {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            List<Flight> result = new ArrayList<>();
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                result.add(buildFlight(resultSet));
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Flight buildFlight(ResultSet resultSet) {
        try {
            return new Flight(resultSet.getLong("id"),
                    resultSet.getString("flight_no"),
                    resultSet.getTimestamp("departure_date").toLocalDateTime(),
                    resultSet.getString("departure_airport_code"),
                    resultSet.getTimestamp("arrival_date").toLocalDateTime(),
                    resultSet.getString("arrival_airport_code"),
                    resultSet.getInt("aircraft_id"),
                    FlightStatus.valueOf(resultSet.getString("status")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
