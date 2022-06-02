package ajbc.webservice.rest.api_demo.inventory.tcp;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MultiThreadedServerRunner implements ServletContextListener {
	private final int PORT = 9090;
	InventoryServer inventoryServer;
	
	public void contextInitialized(ServletContextEvent event) {
		// start TCP server
		inventoryServer = new InventoryServer(PORT);
		inventoryServer.start();
	}

	public void contextDestroyed(ServletContextEvent event) {
		inventoryServer.kill();
	}

}
