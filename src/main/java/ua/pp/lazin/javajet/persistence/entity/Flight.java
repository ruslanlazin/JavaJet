package ua.pp.lazin.javajet.persistence.entity;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Ruslan Lazin
 */
@Entity
public class Flight {
    private Long id;
    private Date departureTime;
    private String departureTimezone;
    private Aircraft aircraft;
    private Airport departure;
    private Airport destination;
    private List<User> crew;
    private Date lastModified;


    @Id
    @Column(name = "flight_id")
    public Long getId() {
        return id;
    }

    public void setId(Long flightId) {
        this.id = flightId;
    }

    @Basic
    @Column(name = "departure_time")
    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    @Basic
    @Column(name = "departure_timezone")
    public String getDepartureTimezone() {
        return departureTimezone;
    }

    public void setDepartureTimezone(String departureTimezone) {
        this.departureTimezone = departureTimezone;
    }

    @ManyToOne
    @JoinColumn(name = "aircraft_id", referencedColumnName = "aircraft_id", nullable = false)
    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    @ManyToOne
    @JoinColumn(name = "departure", referencedColumnName = "airport_id", nullable = false)
    public Airport getDeparture() {
        return departure;
    }

    public void setDeparture(Airport airportByFrom) {
        this.departure = airportByFrom;
    }

    @ManyToOne
    @JoinColumn(name = "destination", referencedColumnName = "airport_id", nullable = false)
    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport airportByTo) {
        this.destination = airportByTo;
    }

    @OneToMany(mappedBy = "flight_id")
    public List<User> getCrew() {
        return crew;
    }

    public void setCrew(List<User> crew) {
        this.crew = crew;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (id != null ? !id.equals(flight.id) : flight.id != null) return false;
        if (departureTime != null ? !departureTime.equals(flight.departureTime) : flight.departureTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        return result;
    }
}
