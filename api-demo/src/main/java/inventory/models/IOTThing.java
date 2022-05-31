package inventory.models;

import java.net.Socket;
import java.util.List;

public class IOTThing extends Hardware {
	private Socket clientSocket;
	private List<Device> devices;

	public IOTThing() {
		super();
	}

	public IOTThing(Socket clientSocket, List<Device> devices) {
		super();
		this.clientSocket = clientSocket; // TODO
		this.devices = devices;
	}
	
//	public Report createReport() {
//		// TODO
//		return null;
//	}
	
	public List<Device> simulateInventoryChange(){
		// TODO
		return null;
	}
	
	public void transmitReportsPeriodically(){
		// TODO
	}
}
