package ajbc.webservice.rest.api_demo.inventory.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InventoryServer extends Thread {
	private ExecutorService executorService;
	private int port;

	public InventoryServer(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		executorService = Executors.newCachedThreadPool();

		try (ServerSocket serverSocket = new ServerSocket(port);) {

			System.out.println("Server started on port " + port);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				executorService.execute(new ServerThread(clientSocket));
			}
		} catch (IOException e) {
			System.err.println("Failed to start server on port " + port);
			e.printStackTrace();
		}
//		finally {
//			try {
//				executorService.shutdown();
//				executorService.awaitTermination(2, TimeUnit.SECONDS);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

//	public static void main(String[] args) {
//		InventoryServer server = new InventoryServer(PORT);
//		server.run();
//	}

	public void kill() {
		try {
			executorService.shutdown();
			executorService.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
