package ua.pp.lazin.javajet.entities;

import javax.persistence.*;

/**
 * @author Ruslan Lazin
 */
@Entity
@Table(name = "flight_members", schema = "public", catalog = "javajet")
@IdClass(FlightMembersEntityPK.class)
public class FlightMembersEntity {
    private Long flightId;
    private Long memberId;
    private Boolean confirmed;

    @Id
    @Column(name = "flight_id")
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Id
    @Column(name = "member_id")
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

        FlightMembersEntity that = (FlightMembersEntity) o;

        if (flightId != null ? !flightId.equals(that.flightId) : that.flightId != null) return false;
        if (memberId != null ? !memberId.equals(that.memberId) : that.memberId != null) return false;
        if (confirmed != null ? !confirmed.equals(that.confirmed) : that.confirmed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flightId != null ? flightId.hashCode() : 0;
        result = 31 * result + (memberId != null ? memberId.hashCode() : 0);
        result = 31 * result + (confirmed != null ? confirmed.hashCode() : 0);
        return result;
    }
}
