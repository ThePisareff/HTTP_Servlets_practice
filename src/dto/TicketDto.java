package dto;

import java.util.Objects;

public record TicketDto(Long id, Long flightId, String seatNo) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto ticketDto = (TicketDto) o;
        return Objects.equals(id, ticketDto.id) && Objects.equals(flightId, ticketDto.flightId) && Objects.equals(seatNo, ticketDto.seatNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightId, seatNo);
    }

    @Override
    public String toString() {
        return "TicketDto{" +
               "id=" + id +
               ", flightId=" + flightId +
               ", seatNo='" + seatNo + '\'' +
               '}';
    }
}
