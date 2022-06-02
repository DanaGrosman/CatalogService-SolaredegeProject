package ajbc.webservice.rest.api_demo.inventory.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;

import ajbc.webservice.rest.api_demo.inventory.DBService.DBService;
import ajbc.webservice.rest.api_demo.inventory.models.IOTThing;

public class ServerThread implements Runnable {
	private Socket clientSocket;
	private DBService dbService;

	ServerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.dbService = new DBService();
	}
	
	@Override
	public void run() {
		try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);) {

			System.out
					.println("Thing is connected " + clientSocket.getInetAddress() + " port " + clientSocket.getPort());

			// reading data
			String thing = bufferReader.readLine();
			System.out.println("Thing: " + thing);

			// sending data
			Gson gson = new Gson();
			IOTThing iotThing = gson.fromJson(thing, IOTThing.class);
			updateDB(iotThing);
			System.out.println("processing result done ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateDB(IOTThing iotThing) {
		IOTThing newThing = dbService.updateThing(iotThing.getId(), iotThing);
		
		if(newThing == null)
			dbService.addThing(iotThing);
		
		System.out.println("Updated thing in DB: " + iotThing);
	}

}
