package ua.pp.lazin.javajet.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Ruslan Lazin
 */
@Entity
@Table(name = "flights", schema = "public", catalog = "javajet")
public class FlightsEntity {
    private Long flightId;
    private Timestamp departureTime;

    @Id
    @Column(name = "flight_id")
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Basic
    @Column(name = "departure_time")
    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightsEntity that = (FlightsEntity) o;

        if (flightId != null ? !flightId.equals(that.flightId) : that.flightId != null) return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flightId != null ? flightId.hashCode() : 0;
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        return result;
    }
}
