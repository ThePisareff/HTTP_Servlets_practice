package dto;

import java.util.Objects;

public record FlightDto (Long id,
                         String description) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDto flightDto = (FlightDto) o;
        return Objects.equals(id, flightDto.id) && Objects.equals(description, flightDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "FlightDto{" +
               "id=" + id +
               ", description='" + description + '\'' +
               '}';
    }
}
