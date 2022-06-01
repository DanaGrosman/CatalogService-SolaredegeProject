package inventory.models;

import java.net.Socket;
import java.util.List;

public class IOTThing extends Hardware {
	private List<Device> devices;

	public IOTThing() {
		super();
	}

	public IOTThing(Socket clientSocket, List<Device> devices) {
		super();
		this.devices = devices;
	}
	
	public List<Device> simulateInventoryChange(){
		// TODO
		return null;
	}
	

}
