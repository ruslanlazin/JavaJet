package ua.pp.lazin.javajet.entities;

import javax.persistence.*;

/**
 * @author Ruslan Lazin
 */
@Entity
@Table(name = "aircrafts", schema = "public", catalog = "javajet")
public class AircraftsEntity {
    private Long aircraftId;
    private String model;
    private String regNumber;

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

        AircraftsEntity that = (AircraftsEntity) o;

        if (aircraftId != null ? !aircraftId.equals(that.aircraftId) : that.aircraftId != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (regNumber != null ? !regNumber.equals(that.regNumber) : that.regNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aircraftId != null ? aircraftId.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (regNumber != null ? regNumber.hashCode() : 0);
        return result;
    }
}
