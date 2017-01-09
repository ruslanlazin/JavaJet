package ua.pp.lazin.javajet.filters;

import ua.pp.lazin.javajet.persistence.entity.User;

/**
 * Manages authorization to the system.
 *
 * @author Ruslan Lazin
 */
public interface AuthorizationManager {
    public boolean isUserAuthorized(User user, String uri);
}
