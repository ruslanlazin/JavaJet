package ua.pp.lazin.javajet.persistence.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Ruslan Lazin
 */
@Entity
public class Airport {
    private Long airportId;
    private String iataCode;
    private String nameRus;
    private String nameEng;
    private String cityRus;
    private String cityEng;
    private String countryRus;
    private String countryEng;
    private Collection<Flight> flightsByAirportId;
    private Collection<Flight> flightsByAirportId_0;

    @Id
    @Column(name = "airport_id")
    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    @Basic
    @Column(name = "iata_code")
    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    @Basic
    @Column(name = "name_rus")
    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

    @Basic
    @Column(name = "name_eng")
    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    @Basic
    @Column(name = "city_rus")
    public String getCityRus() {
        return cityRus;
    }

    public void setCityRus(String cityRus) {
        this.cityRus = cityRus;
    }

    @Basic
    @Column(name = "city_eng")
    public String getCityEng() {
        return cityEng;
    }

    public void setCityEng(String cityEng) {
        this.cityEng = cityEng;
    }

    @Basic
    @Column(name = "country_rus")
    public String getCountryRus() {
        return countryRus;
    }

    public void setCountryRus(String countryRus) {
        this.countryRus = countryRus;
    }

    @Basic
    @Column(name = "country_eng")
    public String getCountryEng() {
        return countryEng;
    }

    public void setCountryEng(String countryEng) {
        this.countryEng = countryEng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (airportId != null ? !airportId.equals(airport.airportId) : airport.airportId != null) return false;
        if (iataCode != null ? !iataCode.equals(airport.iataCode) : airport.iataCode != null) return false;
        if (nameRus != null ? !nameRus.equals(airport.nameRus) : airport.nameRus != null) return false;
        if (nameEng != null ? !nameEng.equals(airport.nameEng) : airport.nameEng != null) return false;
        if (cityRus != null ? !cityRus.equals(airport.cityRus) : airport.cityRus != null) return false;
        if (cityEng != null ? !cityEng.equals(airport.cityEng) : airport.cityEng != null) return false;
        if (countryRus != null ? !countryRus.equals(airport.countryRus) : airport.countryRus != null) return false;
        if (countryEng != null ? !countryEng.equals(airport.countryEng) : airport.countryEng != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = airportId != null ? airportId.hashCode() : 0;
        result = 31 * result + (iataCode != null ? iataCode.hashCode() : 0);
        result = 31 * result + (nameRus != null ? nameRus.hashCode() : 0);
        result = 31 * result + (nameEng != null ? nameEng.hashCode() : 0);
        result = 31 * result + (cityRus != null ? cityRus.hashCode() : 0);
        result = 31 * result + (cityEng != null ? cityEng.hashCode() : 0);
        result = 31 * result + (countryRus != null ? countryRus.hashCode() : 0);
        result = 31 * result + (countryEng != null ? countryEng.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "airportByFrom")
    public Collection<Flight> getFlightsByAirportId() {
        return flightsByAirportId;
    }

    public void setFlightsByAirportId(Collection<Flight> flightsByAirportId) {
        this.flightsByAirportId = flightsByAirportId;
    }

    @OneToMany(mappedBy = "airportByTo")
    public Collection<Flight> getFlightsByAirportId_0() {
        return flightsByAirportId_0;
    }

    public void setFlightsByAirportId_0(Collection<Flight> flightsByAirportId_0) {
        this.flightsByAirportId_0 = flightsByAirportId_0;
    }
}
