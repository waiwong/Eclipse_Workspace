package onlineBookstrore.util.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * @author B1340 add context listener for Log4J
 */
@WebListener("application context listener")
public class ContextListener4Log implements ServletContextListener {

	/**
	 * Initialize log4j when the application is being started
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();

		String log4jConfigFile = context
				.getInitParameter("log4j2-config-location");
		String fullPath = context.getRealPath("") + File.separator
				+ log4jConfigFile;

		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(fullPath));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do nothing
	}
}