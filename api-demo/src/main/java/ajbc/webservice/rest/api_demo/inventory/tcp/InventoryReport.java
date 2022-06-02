package ajbc.webservice.rest.api_demo.inventory.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

import ajbc.webservice.rest.api_demo.inventory.models.Device;
import ajbc.webservice.rest.api_demo.inventory.models.IOTThing;
import ajbc.webservice.rest.api_demo.inventory.models.Type;

public class InventoryReport {
	private Socket clientSocket;
	private IOTThing iotThing;

	private final static String SERVER_NAME = "localhost";
	private final static int SERVER_PORT = 9090;

	public InventoryReport(IOTThing iotThing) {
		this.iotThing = iotThing;
	}

	public void transmitReportsPeriodically() {
		PrintWriter writer = null;
		BufferedReader bufferReader = null;

		try {
			clientSocket = new Socket(SERVER_NAME, SERVER_PORT);
			System.out.println("Connected to server");

			iotThing.simulateInventoryChange();

			// sending data
			Gson gson = new Gson();
			String thingJson = gson.toJson(iotThing);
			System.out.println("thing updated: " + thingJson);

			writer = new PrintWriter(clientSocket.getOutputStream(), true);
			writer.println(thingJson);

		} catch (UnknownHostException e) {
			System.err.println("Server is not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Socket failed");
			e.printStackTrace();

		} finally {
			if (clientSocket != null)
				try {
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (writer != null)
				writer.close();
			if (bufferReader != null)
				writer.close();
		}
	}

	public static void main(String[] args) throws IOException {
		IOTThing thing = createThing();
		InventoryReport report = new InventoryReport(thing);
		report.transmitReportsPeriodically();
		report.transmitReportsPeriodically();
	}

	private static IOTThing createThing() {
		Random rand = new Random();

		int numOfDevices = rand.nextInt(10) + 1;

		List<Device> devicesList = new ArrayList<Device>(numOfDevices);

		for (int i = 0; i < numOfDevices; i++) {
			int typeIndex = rand.nextInt(Type.values().length - 1);
			devicesList.add(new Device(Type.values()[typeIndex], i * 100 + "", "TOYOTA", 5.5));

		}

		return new IOTThing(Type.CONTROLLER, "2022", "TOYOTA", devicesList);
	}

}
