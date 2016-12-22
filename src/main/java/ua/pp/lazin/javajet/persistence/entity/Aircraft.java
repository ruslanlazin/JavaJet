package ua.pp.lazin.javajet.persistence.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Ruslan Lazin
 */
@Entity
public class Aircraft {
    private Long aircraftId;
    private String model;
    private String regNumber;
    private Collection<Flight> flightsByAircraftId;

    @Id
    @Column(name = "aircraft_id")
    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "reg_number")
    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aircraft aircraft = (Aircraft) o;

        if (aircraftId != null ? !aircraftId.equals(aircraft.aircraftId) : aircraft.aircraftId != null) return false;
        if (model != null ? !model.equals(aircraft.model) : aircraft.model != null) return false;
        if (regNumber != null ? !regNumber.equals(aircraft.regNumber) : aircraft.regNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aircraftId != null ? aircraftId.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (regNumber != null ? regNumber.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "aircraftByAircraftId")
    public Collection<Flight> getFlightsByAircraftId() {
        return flightsByAircraftId;
    }

    public void setFlightsByAircraftId(Collection<Flight> flightsByAircraftId) {
        this.flightsByAircraftId = flightsByAircraftId;
    }
}
