package ua.pp.lazin.javajet.entity;

/**
 * The Position entity represents User's position in the company.
 *
 * @author Ruslan Lazin
 */
public class Position {
    private Long id;
    private String title;

    private Position(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param roleId the role id
     */
    public void setId(Long roleId) {
        this.id = roleId;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (id != null ? !id.equals(position.id) : position.id != null) return false;
        if (title != null ? !title.equals(position.title) : position.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    /**
     * {@code Position} builder static inner class.
     */
    public static final class Builder {
        private Long id;
        private String title;

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
         * Sets the {@code title} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param title the {@code title} to set
         * @return a reference to this Builder
         */
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        /**
         * Returns a {@code Position} built from the parameters previously set.
         *
         * @return a {@code Position} built with parameters of this {@code Position.Builder}
         */
        public Position build() {
            return new Position(this);
        }
    }
}
