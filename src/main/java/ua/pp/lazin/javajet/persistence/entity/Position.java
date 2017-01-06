package ua.pp.lazin.javajet.persistence.entity;

/**
 * The Position entity represents User's position in the company.
 *
 * @author Ruslan Lazin
 */
public class Position {
    private Long id;
    private String title;

    /**
     * Instantiates a new Position.
     */
    public Position() {
    }

    /**
     * Instantiates a new Position.
     *
     * @param title the title
     */
    public Position(String title) {
        this.title = title;
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
}
