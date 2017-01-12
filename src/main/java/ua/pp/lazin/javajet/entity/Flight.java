package ua.pp.lazin.javajet.entity;

import java.util.Date;
import java.util.Set;

/**
 * The type Flight.
 *
 * @author Ruslan Lazin
 */
public class Flight {
    private Long id;
    private Date departureTime;
    private String departureTimezone;
    private Aircraft aircraft;
    private Airport departure;
    private Airport destination;
    private Set<User> crew;
    private Integer version;

    private Flight(Builder builder) {
        setId(builder.id);
        setDepartureTime(builder.departureTime);
        setDepartureTimezone(builder.departureTimezone);
        setAircraft(builder.aircraft);
        setDeparture(builder.departure);
        setDestination(builder.destination);
        setCrew(builder.crew);
        version = builder.version;
    }

    /**
     * New builder builder.
     *
     * @return the builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param flightId the flight id
     */
    public void setId(Long flightId) {
        this.id = flightId;
    }

    /**
     * Gets departure time.
     *
     * @return the departure time
     */
    public Date getDepartureTime() {
        return departureTime;
    }

    /**
     * Sets departure time.
     *
     * @param departureTime the departure time
     */
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Gets departure timezone.
     *
     * @return the departure timezone
     */
    public String getDepartureTimezone() {
        return departureTimezone;
    }

    /**
     * Sets departure timezone.
     *
     * @param departureTimezone the departure timezone
     */
    public void setDepartureTimezone(String departureTimezone) {
        this.departureTimezone = departureTimezone;
    }

    /**
     * Gets aircraft.
     *
     * @return the aircraft
     */
    public Aircraft getAircraft() {
        return aircraft;
    }

    /**
     * Sets aircraft.
     *
     * @param aircraft the aircraft
     */
    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    /**
     * Gets departure.
     *
     * @return the departure
     */
    public Airport getDeparture() {
        return departure;
    }

    /**
     * Sets departure.
     *
     * @param airportByFrom the airport by from
     */
    public void setDeparture(Airport airportByFrom) {
        this.departure = airportByFrom;
    }

    /**
     * Gets destination.
     *
     * @return the destination
     */
    public Airport getDestination() {
        return destination;
    }

    /**
     * Sets destination.
     *
     * @param airportByTo the airport by to
     */
    public void setDestination(Airport airportByTo) {
        this.destination = airportByTo;
    }

    /**
     * Gets crew.
     *
     * @return the crew
     */
    public Set<User> getCrew() {
        return crew;
    }

    /**
     * Sets crew.
     *
     * @param crew the crew
     */
    public void setCrew(Set<User> crew) {
        this.crew = crew;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(Integer version) {
        this.version = version;
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

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", departureTime=" + departureTime +
                ", departureTimezone='" + departureTimezone + '\'' +
                ", aircraft=" + aircraft +
                ", departure=" + departure +
                ", destination=" + destination +
                ", crew=" + crew +
                ", version=" + version +
                '}';
    }

    /**
     * {@code Flight} builder static inner class.
     */
    public static final class Builder {
        private Long id;
        private Date departureTime;
        private String departureTimezone;
        private Aircraft aircraft;
        private Airport departure;
        private Airport destination;
        private Set<User> crew;
        private Integer version;

        private Builder() {
        }

        /**
         * Sets the {@code departureTime} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param departureTime the {@code departureTime} to set
         * @return a reference to this Builder
         */
        public Builder departureTime(Date departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        /**
         * Sets the {@code departureTimezone} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param departureTimezone the {@code departureTimezone} to set
         * @return a reference to this Builder
         */
        public Builder departureTimezone(String departureTimezone) {
            this.departureTimezone = departureTimezone;
            return this;
        }

        /**
         * Sets the {@code aircraft} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param aircraft the {@code aircraft} to set
         * @return a reference to this Builder
         */
        public Builder aircraft(Aircraft aircraft) {
            this.aircraft = aircraft;
            return this;
        }

        /**
         * Sets the {@code departure} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param departure the {@code departure} to set
         * @return a reference to this Builder
         */
        public Builder departure(Airport departure) {
            this.departure = departure;
            return this;
        }

        /**
         * Sets the {@code destination} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param destination the {@code destination} to set
         * @return a reference to this Builder
         */
        public Builder destination(Airport destination) {
            this.destination = destination;
            return this;
        }

        /**
         * Sets the {@code crew} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param crew the {@code crew} to set
         * @return a reference to this Builder
         */
        public Builder crew(Set<User> crew) {
            this.crew = crew;
            return this;
        }

        /**
         * Sets the {@code version} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param version the {@code version} to set
         * @return a reference to this Builder
         */
        public Builder version(Integer version) {
            this.version = version;
            return this;
        }

        /**
         * Returns a {@code Flight} built from the parameters previously set.
         *
         * @return a {@code Flight} built with parameters of this {@code Flight.Builder}
         */
        public Flight build() {
            return new Flight(this);
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param id the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
    }
}
