package dao;

import entity.Ticket;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, Ticket> {

    private static final TicketDao INSTANCE = new TicketDao();

    private static final String FIND_ALL_BY_FLIGHT_ID_SQL = """
            SELECT * FROM ticket
            WHERE flight_id = ?
            """;

    private TicketDao() {
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    public List<Ticket> findAllByFlightId(Long flightId) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(FIND_ALL_BY_FLIGHT_ID_SQL)) {

            List<Ticket> ticketList = new ArrayList<>();

            prepareStatement.setLong(1, flightId);
            var resultSet = prepareStatement.executeQuery();
            while (resultSet.next()){
                ticketList.add(buildTicket(resultSet));
            }

            return ticketList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ticket save(Ticket entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }


    @Override
    public void update(Ticket entity) {

    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Ticket> findAll() {
        return List.of();
    }

    private Ticket buildTicket(ResultSet resultSet) throws SQLException {
        return new Ticket(resultSet.getLong("id"),
                resultSet.getString("passenger_no"),
                resultSet.getString("passenger_name"),
                resultSet.getLong("flight_id"),
                resultSet.getString("seat_no"),
                resultSet.getBigDecimal("cost"));
    }
}
