package ua.pp.lazin.javajet.persistence.entity;

import javax.persistence.*;

/**
 * @author Ruslan Lazin
 */
@Entity
public class Airport {
    private String iataCode;
    private String name;
    private String city;
    private String country;
    private Double longitude;
    private Double latitude;


    @Id
    @Column(name = "iata_code")
    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    @Basic
    @Column(name = "name_eng")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "city_eng")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "country_eng")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (iataCode != null ? !iataCode.equals(airport.iataCode) : airport.iataCode != null) return false;
        if (name != null ? !name.equals(airport.name) : airport.name != null) return false;
        if (city != null ? !city.equals(airport.city) : airport.city != null) return false;
        if (country != null ? !country.equals(airport.country) : airport.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (iataCode != null ? iataCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
