package ua.pp.lazin.javajet.persistence.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Ruslan Lazin
 */
@Entity
public class Airport {
    private String iataCode;
    private String nameEng;
    private String cityEng;
    private String countryEng;


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
    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
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

        if (iataCode != null ? !iataCode.equals(airport.iataCode) : airport.iataCode != null) return false;
        if (nameEng != null ? !nameEng.equals(airport.nameEng) : airport.nameEng != null) return false;
        if (cityEng != null ? !cityEng.equals(airport.cityEng) : airport.cityEng != null) return false;
        if (countryEng != null ? !countryEng.equals(airport.countryEng) : airport.countryEng != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (iataCode != null ? iataCode.hashCode() : 0);
        result = 31 * result + (nameEng != null ? nameEng.hashCode() : 0);
        result = 31 * result + (cityEng != null ? cityEng.hashCode() : 0);
        result = 31 * result + (countryEng != null ? countryEng.hashCode() : 0);
        return result;
    }
}
