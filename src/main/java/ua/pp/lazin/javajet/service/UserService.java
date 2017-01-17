package ua.pp.lazin.javajet.service;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.dao.RoleDao;
import ua.pp.lazin.javajet.persistence.dao.UserDao;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.persistence.factory.DaoFactoryCreator;
import ua.pp.lazin.javajet.util.PasswordEncoder;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type User service.
 *
 * @author Ruslan Lazin
 */
public class UserService {
    private final static String CURRENT_USER_ATTRIBUTE = "currentUser";
    private final static Logger logger = Logger.getLogger(UserService.class);
    private final static UserDao userDao = DaoFactoryCreator.getFactory().getUserDao();
    private final static RoleDao roleDao = DaoFactoryCreator.getFactory().getRoleDao();
    private static UserService INSTANCE = new UserService();

    private UserService() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserService getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Check if given loin/password pair  matches login/password pair in db.
     *
     * @param login    the login
     * @param password the password
     * @return the boolean
     */
    public boolean check(String login, String password) {
        User user = userDao.findByUsername(login);
        return user != null && PasswordEncoder.check(password, user.getPassword());
    }


    /**
     * Cache user in session.
     *
     * @param username the username
     * @param session  the session
     */
    public void cacheUserInSession(String username, HttpSession session) {
        session.setAttribute(CURRENT_USER_ATTRIBUTE, findByUsernameWithRoles(username));
    }

    /**
     * Is user cached in session boolean.
     *
     * @param session the session
     * @return the boolean
     */
    public boolean isUserCachedInSession(HttpSession session) {
        return session.getAttribute(CURRENT_USER_ATTRIBUTE) != null;
    }

    /**
     * Gets current user.
     *
     * @param session the session
     * @return the current user
     */
    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute(CURRENT_USER_ATTRIBUTE);
    }


    /**
     * Remove current user from session.
     *
     * @param session the session
     */
    public void removeCurrentUserFromSession(HttpSession session) {
        session.removeAttribute(CURRENT_USER_ATTRIBUTE);
    }


    /**
     * Create
     *
     * @param user the user
     * @return the long - user id
     */
    public Long create(User user) {
        user.setPassword(PasswordEncoder.getSaltedHash(user.getPassword()));
        return userDao.create(user);
    }

    /**
     * Update with roles boolean.
     * Make update only if version of instance matches version in db.

     *
     * @param user the user
     * @return the boolean
     */
    public boolean updateWithRoles(User user) {
//        check if password was changed
        if (!user.getPassword().equals(userDao.findByID(user.getId()).getPassword())) {
            user.setPassword(PasswordEncoder.getSaltedHash(user.getPassword()));
        }
        return userDao.updateWithRoles(user);
    }

    /**
     * Find user by username with roles.
     *
     * @param username the username
     * @return the user
     */
    public User findByUsernameWithRoles(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return null;
        }
        user.setRoles(roleDao.findRolesOfUser(user));
        return user;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * Is username available boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean isUsernameAvailable(String username) {
        return userDao.findByUsername(username) == null;
    }

    /**
     * Is email available boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean isEmailAvailable(String email) {
        return userDao.findByEmail(email) == null;
    }

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    public User findById(Long id) {
        return userDao.findByID(id);
    }

    /**
     * Find user by id with roles.
     *
     * @param id the id
     * @return the user
     */
    public User findByIdWithRoles(Long id) {
        User user = userDao.findByID(id);
        user.setRoles(roleDao.findRolesOfUser(user));
        return user;
    }

    /**
     * Find all working air crew members list.
     *
     * @return the list of User
     */
    public List<User> findAllWorkingAirCrewMembers() {
        return userDao.findAllWorkingAirCrewMembers();
    }
}
