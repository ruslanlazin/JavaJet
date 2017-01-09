package ua.pp.lazin.javajet.filters.JAAS;

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
}
