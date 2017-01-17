package ua.pp.lazin.javajet.jaas;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.entity.Role;
import ua.pp.lazin.javajet.entity.User;
import ua.pp.lazin.javajet.service.UserService;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * The type Java jet login module.
 *
 * @author Ruslan Lazin
 * @see LoginModule
 */
public class JavaJetLoginModule implements LoginModule {

    private static final Logger logger = Logger.getLogger(JavaJetLoginModule.class);
    private static final UserService userService = UserService.getINSTANCE();
    private CallbackHandler handler;
    private Subject subject;
    private String username;
    private List<String> userRoles;

    private boolean isAuthenticated = false;
    private boolean commitSucceeded = false;


    /**
     * Instantiates a new Java jet login module.
     */
    public JavaJetLoginModule() {
        super();
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {

        this.handler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {

        // If no handler is specified throw a exception
        if (handler == null) {
            throw new LoginException("Error: no CallbackHandler available to receive authentication information from the user");
        }

        // Declare the callbacks based on the JAAS spec
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);

        try {

            //Handle the callback and receive the sent information
            handler.handle(callbacks);
            username = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());

            // Debug the username / password
            logger.debug("Username: " + username);
            logger.debug("Password: " + password);

            // Empty strings not allowed
            if (username == null || username.isEmpty() || password.isEmpty()) {
                logger.info("Empty login or password have been passed to LoginModule");
                throw new LoginException("login or password had null values");
            }

            // Validate  if there is user with these username and password
            isAuthenticated = userService.check(username, password);
            if (!isAuthenticated) {
                throw new LoginException("Authentication failed");
            }

            // Assign the user roles to temporary field and save authentication result
            userRoles = this.getRoles(userService.findByUsernameWithRoles(username));
            return true;

        } catch (IOException | UnsupportedCallbackException e) {
            logger.info("An exception occurred during authentication" + e.getMessage());
            throw new LoginException(e.getMessage());
        }
    }

    /**
     * Commit results of <tt>login()</tt>, and clear temporary values.
     *
     * @return true if commit was successful
     */
    @Override
    public boolean commit() throws LoginException {

        if (!isAuthenticated) {
            cleanTemporaryVariables();
            return false;
        } else {
            UserPrincipal userPrincipal = new UserPrincipal(username);
            subject.getPrincipals().add(userPrincipal);

            if (userRoles != null && userRoles.size() > 0) {
                for (String groupName : userRoles) {
                    subject.getPrincipals().add(new RolePrincipal(groupName));
                }
            }
            cleanTemporaryVariables();
            commitSucceeded = true;
            return true;
        }
    }

    /**
     * Abort authentication and clean temporary values.
     */
    @Override
    public boolean abort() throws LoginException {
        if (!isAuthenticated) {
            return false;
        } else if (!commitSucceeded) {
            isAuthenticated = false;
            cleanTemporaryVariables();
        } else {
            logout();
        }
        return true;
    }

    /**
     * Clears subject from principal and credentials.
     *
     * @see javax.security.auth.spi.LoginModule#logout()
     */
    @Override
    public boolean logout() throws LoginException {
        isAuthenticated = false;
        commitSucceeded = false;
        subject.getPrincipals().clear();
        return true;
    }

    private void cleanTemporaryVariables() {
        username = null;
        userRoles = null;
    }

    private List<String> getRoles(User user) {

        List<String> roleTitles = new ArrayList<>();
        roleTitles.add("ROLE_AUTHENTICATED");
        Set<Role> roles = user.getRoles();
        if (roles != null) {
            roleTitles.addAll(roles.stream().map(Role::getTitle).collect(Collectors.toList()));
        }
        return roleTitles;
    }
}
