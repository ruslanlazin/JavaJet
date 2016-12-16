package ua.pp.lazin.javajet.entities;

import javax.persistence.*;

/**
 * @author Ruslan Lazin
 */
@Entity
@Table(name = "airports", schema = "public", catalog = "javajet")
public class AirportsEntity {
    private Long airportId;
    private String iataCode;
    private String nameRus;
    private String nameEng;
    private String cityRus;
    private String cityEng;
    private String countryRus;
    private String countryEng;

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

        AirportsEntity that = (AirportsEntity) o;

        if (airportId != null ? !airportId.equals(that.airportId) : that.airportId != null) return false;
        if (iataCode != null ? !iataCode.equals(that.iataCode) : that.iataCode != null) return false;
        if (nameRus != null ? !nameRus.equals(that.nameRus) : that.nameRus != null) return false;
        if (nameEng != null ? !nameEng.equals(that.nameEng) : that.nameEng != null) return false;
        if (cityRus != null ? !cityRus.equals(that.cityRus) : that.cityRus != null) return false;
        if (cityEng != null ? !cityEng.equals(that.cityEng) : that.cityEng != null) return false;
        if (countryRus != null ? !countryRus.equals(that.countryRus) : that.countryRus != null) return false;
        if (countryEng != null ? !countryEng.equals(that.countryEng) : that.countryEng != null) return false;

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
}
