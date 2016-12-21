package ua.pp.lazin.javajet.persistence.entity;

import javax.persistence.*;

/**
 * @author Ruslan Lazin
 */
@Entity
@Table(name = "flight_users", schema = "public", catalog = "javajet")
public class FlightUsers {
    private Long id;
    private Boolean confirmed;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "confirmed")
    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightUsers that = (FlightUsers) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (confirmed != null ? !confirmed.equals(that.confirmed) : that.confirmed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (confirmed != null ? confirmed.hashCode() : 0);
        return result;
    }
}
