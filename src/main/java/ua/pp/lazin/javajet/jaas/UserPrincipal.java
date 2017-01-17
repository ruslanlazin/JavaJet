package ua.pp.lazin.javajet.jaas;
import java.security.Principal;

/**
 * Holds the logged in users name
 *
 * @author Ruslan Lazin
 */

public class UserPrincipal implements Principal {

    private String name;

    /**
     * Initializer
     *
     * @param name meaning User's username
     */
    public UserPrincipal(String name) {
        super();
        this.name = name;
    }

    /**
     * Set the name of the user
     *
     * @param name meaning User's username
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the user
     *
     * @return Name meaning User's username
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrincipal that = (UserPrincipal) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "name='" + name + '\'' +
                '}';
    }
}