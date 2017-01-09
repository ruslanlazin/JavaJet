package ua.pp.lazin.javajet.jaas;

import org.apache.log4j.Logger;
import ua.pp.lazin.javajet.persistence.entity.Role;
import ua.pp.lazin.javajet.persistence.entity.User;
import ua.pp.lazin.javajet.service.AuthService;

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



public class JavaJetLoginModule implements LoginModule {

    private CallbackHandler handler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private List<String> userGroups;

    private static final Logger logger = Logger.getLogger(JavaJetLoginModule.class);
    private static final AuthService authService = AuthService.getINSTANCE();

    // User credentials
    private String username = null;
    private String password = null;

    private boolean isAuthenticated = false;
    private boolean commitSucceeded = false;

    public JavaJetLoginModule() {
        super();
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {

        this.handler = callbackHandler;

        // Subject reference holds the principals
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
            password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());

            // Debug the username / password
            if (logger.isDebugEnabled()) {
                logger.debug("Username: " + username);
                logger.debug("Password: " + password);
            }

            // Empty strings not allowed
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                throw new LoginException("login or password had null values");
            }

            // Validate against our database if there is user with
            User user = authService.login(username, password);
            if (user != null) {

                // Assign the user roles
                userGroups = this.getRoles(user);
                isAuthenticated = true;
                return true;
            }
            throw new LoginException("Authentication failed");

        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage());
        }

    }

    @Override
    public boolean commit() throws LoginException {

        if (!isAuthenticated) {
            return false;
        } else {

            userPrincipal = new UserPrincipal(username);
            subject.getPrincipals().add(userPrincipal);

            if (userGroups != null && userGroups.size() > 0) {
                for (String groupName : userGroups) {
                    subject.getPrincipals().add(new RolePrincipal(groupName));
                }
            }
            commitSucceeded = true;

            return true;
        }
    }

    @Override
    public boolean abort() throws LoginException {
        if (!isAuthenticated) {
            return false;
        } else if (isAuthenticated && !commitSucceeded) {
            isAuthenticated = false;
            username = null;
            password = null;
            userPrincipal = null;
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
        isAuthenticated = commitSucceeded;
        subject.getPrincipals().clear();
        return true;
    }

    private List<String> getRoles(User user) {

        List<String> roleList = new ArrayList<>();
        roleList.add("ROLE_AUTHENTICATED");
        Set<Role> roles = user.getRoles();
        if (roles != null) {
            roleList.addAll(roles.stream().map(Role::getTitle).collect(Collectors.toList()));
        }
        return roleList;
    }
}
