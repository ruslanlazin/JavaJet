package ua.pp.lazin.javajet.jaas;

import org.apache.log4j.Logger;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;

/**
 * The Jaas activator it's a servlet context listener
 * that adds named LoginModule to javax.security.auth.login.Configuration
 * see also META-INF/context.xml
 *
 * @author Ruslan Lazin
 * @see javax.servlet.ServletContextListener
 * @see javax.security.auth.login.Configuration
 * @see javax.security.auth.spi.LoginModule
 */
@WebListener
public class JaasActivatorServletContextListener implements ServletContextListener {
    private static final String LOGIN_MODULE_NAME = "JavaJetLoginModule";
    private static final Logger logger = Logger.getLogger(JaasActivatorServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        logger.debug("Configuring JAAS " + LOGIN_MODULE_NAME + "...");
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
