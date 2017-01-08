package ua.pp.lazin.javajet.persistence.entity;


/**
 * @author Ruslan Lazin
 */

public class Airport {
    private String iataCode;
    private String name;
    private String city;
    private String country;
    private Double longitude;
    private Double latitude;

    private Airport(Builder builder) {
        setIataCode(builder.iataCode);
        setName(builder.name);
        setCity(builder.city);
        setCountry(builder.country);
        setLongitude(builder.longitude);
        setLatitude(builder.latitude);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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

    /**
     * {@code Airport} builder static inner class.
     */
    public static final class Builder {
        private String iataCode;
        private String name;
        private String city;
        private String country;
        private Double longitude;
        private Double latitude;

        private Builder() {
        }

        /**
         * Sets the {@code iataCode} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param iataCode the {@code iataCode} to set
         * @return a reference to this Builder
         */
        public Builder iataCode(String iataCode) {
            this.iataCode = iataCode;
            return this;
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code city} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param city the {@code city} to set
         * @return a reference to this Builder
         */
        public Builder city(String city) {
            this.city = city;
            return this;
        }

        /**
         * Sets the {@code country} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param country the {@code country} to set
         * @return a reference to this Builder
         */
        public Builder country(String country) {
            this.country = country;
            return this;
        }

        /**
         * Sets the {@code longitude} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param longitude the {@code longitude} to set
         * @return a reference to this Builder
         */
        public Builder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        /**
         * Sets the {@code latitude} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param latitude the {@code latitude} to set
         * @return a reference to this Builder
         */
        public Builder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        /**
         * Returns a {@code Airport} built from the parameters previously set.
         *
         * @return a {@code Airport} built with parameters of this {@code Airport.Builder}
         */
        public Airport build() {
            return new Airport(this);
        }
    }
}
