package service;

import dao.FlightDao;
import dto.FlightDto;

import java.util.List;
import java.util.Locale;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService(){};

    public static FlightService getInstance(){
        return INSTANCE;
    }

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(flight -> new FlightDto(
                        flight.getId(),
                        """
                            %s - %s - %s
                        """.formatted(flight.getArrivalAirportCode(),
                                flight.getDepartureAirportCode(),
                                flight.getStatus())))
                .toList();
    }

}
