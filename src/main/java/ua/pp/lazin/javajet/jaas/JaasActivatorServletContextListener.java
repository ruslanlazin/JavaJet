package ua.pp.lazin.javajet.jaas;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;

/**
 * The Jaas activator if's a servlet context listener
 * that adds named LoginModule to javax.security.auth.login.Configuration
 *
 * @link META-INF/context.xml
 * @see javax.servlet.ServletContextListener
 * @see javax.security.auth.login.Configuration
 *
 * @author Ruslan Lazin
 */
@WebListener
public class JaasActivatorServletContextListener implements ServletContextListener {
    private static final String LOGIN_MODULE_NAME = "JavaJetLoginModule";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Configuration loginConfiguration = new Configuration() {
            @Override
            public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
                if (name.equals(LOGIN_MODULE_NAME)) {
                    AppConfigurationEntry entry =
                            new AppConfigurationEntry(JavaJetLoginModule.class.getName(),
                                    AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
                                    new HashMap<String, Object>());

                    return new AppConfigurationEntry[]{entry};
                } else {
                    return null;
                }
            }
        };
        Configuration.setConfiguration(loginConfiguration);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
