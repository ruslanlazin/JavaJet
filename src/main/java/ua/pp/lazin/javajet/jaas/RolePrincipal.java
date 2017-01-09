package ua.pp.lazin.javajet.jaas;

import java.security.Principal;

/**
 * @author Ruslan Lazin
 */
public class RolePrincipal implements Principal {

    private String name;

    /**
     * Initializer
     *
     * @param name
     */
    public RolePrincipal(String name) {
        super();
        this.name = name;
    }

    /**
     * Set the role name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the role name
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolePrincipal that = (RolePrincipal) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RolePrincipal{" +
                "name='" + name + '\'' +
                '}';
    }
}
