package ua.pp.lazin.javajet.entity;

/**
 * @author Ruslan Lazin
 */

public class Aircraft {
    private Long id;
    private String model;
    private String regNumber;

    private Aircraft(Builder builder) {
        setId(builder.id);
        setModel(builder.model);
        setRegNumber(builder.regNumber);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long aircraftId) {
        this.id = aircraftId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

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

        if (id != null ? !id.equals(aircraft.id) : aircraft.id != null) return false;
        if (model != null ? !model.equals(aircraft.model) : aircraft.model != null) return false;
        if (regNumber != null ? !regNumber.equals(aircraft.regNumber) : aircraft.regNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (regNumber != null ? regNumber.hashCode() : 0);
        return result;
    }

    /**
     * {@code Aircraft} builder static inner class.
     */
    public static final class Builder {
        private Long id;
        private String model;
        private String regNumber;

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
         * Sets the {@code model} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param model the {@code model} to set
         * @return a reference to this Builder
         */
        public Builder model(String model) {
            this.model = model;
            return this;
        }

        /**
         * Sets the {@code regNumber} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param regNumber the {@code regNumber} to set
         * @return a reference to this Builder
         */
        public Builder regNumber(String regNumber) {
            this.regNumber = regNumber;
            return this;
        }

        /**
         * Returns a {@code Aircraft} built from the parameters previously set.
         *
         * @return a {@code Aircraft} built with parameters of this {@code Aircraft.Builder}
         */
        public Aircraft build() {
            return new Aircraft(this);
        }
    }
}
