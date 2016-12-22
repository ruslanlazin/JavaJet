package ua.pp.lazin.javajet.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * @author Ruslan Lazin
 */
@Entity
public class Flight {
    private Long flightId;
    private Timestamp departureTime;
    private Aircraft aircraftByAircraftId;
    private Airport airportByFrom;
    private Airport airportByTo;
    private Collection<FlightUsers> flightUsersesByFlightId;
    private Long aircraftId;
    private Long from;
    private Long to;

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

        Flight flight = (Flight) o;

        if (flightId != null ? !flightId.equals(flight.flightId) : flight.flightId != null) return false;
        if (departureTime != null ? !departureTime.equals(flight.departureTime) : flight.departureTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flightId != null ? flightId.hashCode() : 0;
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "aircraft_id", referencedColumnName = "aircraft_id", nullable = false)
    public Aircraft getAircraftByAircraftId() {
        return aircraftByAircraftId;
    }

    public void setAircraftByAircraftId(Aircraft aircraftByAircraftId) {
        this.aircraftByAircraftId = aircraftByAircraftId;
    }

    @ManyToOne
    @JoinColumn(name = "from", referencedColumnName = "airport_id", nullable = false)
    public Airport getAirportByFrom() {
        return airportByFrom;
    }

    public void setAirportByFrom(Airport airportByFrom) {
        this.airportByFrom = airportByFrom;
    }

    @ManyToOne
    @JoinColumn(name = "to", referencedColumnName = "airport_id", nullable = false)
    public Airport getAirportByTo() {
        return airportByTo;
    }

    public void setAirportByTo(Airport airportByTo) {
        this.airportByTo = airportByTo;
    }

    @OneToMany(mappedBy = "flightByFlightId")
    public Collection<FlightUsers> getFlightUsersesByFlightId() {
        return flightUsersesByFlightId;
    }

    public void setFlightUsersesByFlightId(Collection<FlightUsers> flightUsersesByFlightId) {
        this.flightUsersesByFlightId = flightUsersesByFlightId;
    }

    @Basic
    @Column(name = "aircraft_id")
    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    @Basic
    @Column(name = "from")
    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    @Basic
    @Column(name = "to")
    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }
}
