package ua.pp.lazin.javajet.entity;

/**
 * @author Ruslan Lazin
 */

public class Role {
    private Long id;
    private String title;

    private Role(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long roleId) {
        this.id = roleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        return title != null ? title.equals(role.title) : role.title == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    /**
     * {@code Role} builder static inner class.
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
         * Returns a {@code Role} built from the parameters previously set.
         *
         * @return a {@code Role} built with parameters of this {@code Role.Builder}
         */
        public Role build() {
            return new Role(this);
        }
    }
}
