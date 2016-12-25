package ua.pp.lazin.javajet.persistence.entity;

import javax.persistence.*;

/**
 * @author Ruslan Lazin
 */
@Entity
public class Role {
    private Long Id;
    private String title;

    public Role() {
    }

    public Role(String title) {
        this.title = title;
    }

    @Id
    @Column(name = "role_id")
    public Long getId() {
        return Id;
    }

    public void setId(Long roleId) {
        this.Id = roleId;
    }

    @Basic
    @Column(name = "title")
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

        if (Id != null ? !Id.equals(role.Id) : role.Id != null) return false;
        if (title != null ? !title.equals(role.title) : role.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                '}';
    }
}
