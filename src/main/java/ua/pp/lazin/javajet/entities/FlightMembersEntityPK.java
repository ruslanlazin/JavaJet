package ua.pp.lazin.javajet.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Ruslan Lazin
 */
public class FlightMembersEntityPK implements Serializable {
    private Long flightId;
    private Long memberId;

    @Column(name = "flight_id")
    @Id
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Column(name = "member_id")
    @Id
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightMembersEntityPK that = (FlightMembersEntityPK) o;

        if (flightId != null ? !flightId.equals(that.flightId) : that.flightId != null) return false;
        if (memberId != null ? !memberId.equals(that.memberId) : that.memberId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flightId != null ? flightId.hashCode() : 0;
        result = 31 * result + (memberId != null ? memberId.hashCode() : 0);
        return result;
    }
}
