package ua.pp.lazin.javajet.persistence.entity;

import java.util.Set;

/**
 * @author Ruslan Lazin
 */

public class User {
    private Long id;
    private Position position;
    private String firstName;
    private String secondName;
    private String username;
    private String password;
    private String email;
    private Boolean airCrew;
    private Boolean works;
    private Set<Flight> flights;
    private Integer version;

    private User(Builder builder) {
        setId(builder.id);
        setPosition(builder.position);
        setFirstName(builder.firstName);
        setSecondName(builder.secondName);
        setUsername(builder.username);
        setPassword(builder.password);
        setEmail(builder.email);
        setAirCrew(builder.airCrew);
        setWorks(builder.works);
        setFlights(builder.flights);
        setVersion(builder.version);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long userId) {
        this.id = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position positionByPositionId) {
        this.position = positionByPositionId;
    }

    public Boolean isAirCrew() {
        return airCrew;
    }

    public void setAirCrew(Boolean isAirCrew) {
        this.airCrew = isAirCrew;
    }

        public Boolean isWorks() {
        return works;
    }

    public void setWorks(Boolean isWork) {
        this.works = isWork;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (secondName != null ? !secondName.equals(user.secondName) : user.secondName != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", position=" + position +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * {@code User} builder static inner class.
     */
    public static final class Builder {
        private Long id;
        private Position position;
        private String firstName;
        private String secondName;
        private String username;
        private String password;
        private String email;
        private Boolean airCrew;
        private Boolean works;
        private Set<Flight> flights;
        private Integer version;

        private Builder() {
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

        /**
         * Sets the {@code position} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param position the {@code position} to set
         * @return a reference to this Builder
         */
        public Builder position(Position position) {
            this.position = position;
            return this;
        }

        /**
         * Sets the {@code firstName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param firstName the {@code firstName} to set
         * @return a reference to this Builder
         */
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Sets the {@code secondName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param secondName the {@code secondName} to set
         * @return a reference to this Builder
         */
        public Builder secondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        /**
         * Sets the {@code username} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param username the {@code username} to set
         * @return a reference to this Builder
         */
        public Builder username(String username) {
            this.username = username;
            return this;
        }

        /**
         * Sets the {@code password} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param password the {@code password} to set
         * @return a reference to this Builder
         */
        public Builder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * Sets the {@code email} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param email the {@code email} to set
         * @return a reference to this Builder
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the {@code airCrew} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param airCrew the {@code airCrew} to set
         * @return a reference to this Builder
         */
        public Builder airCrew(Boolean airCrew) {
            this.airCrew = airCrew;
            return this;
        }

        /**
         * Sets the {@code works} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param works the {@code works} to set
         * @return a reference to this Builder
         */
        public Builder works(Boolean works) {
            this.works = works;
            return this;
        }

        /**
         * Sets the {@code flights} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param flights the {@code flights} to set
         * @return a reference to this Builder
         */
        public Builder flights(Set<Flight> flights) {
            this.flights = flights;
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
         * Returns a {@code User} built from the parameters previously set.
         *
         * @return a {@code User} built with parameters of this {@code User.Builder}
         */
        public User build() {
            return new User(this);
        }
    }
}
