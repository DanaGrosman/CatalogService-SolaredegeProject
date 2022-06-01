package inventory.models;

import java.util.List;
import java.util.Random;

public class IOTThing extends Hardware {
	private List<Device> devices;

	public IOTThing(Type type, String model, String manufacturer, List<Device> devices) {
		super(type, model, manufacturer);
		this.devices = devices;
	}

	public IOTThing() {
		super();
		this.devices = null;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public List<Device> simulateInventoryChange() {
		Random rand = new Random();
		int action = rand.nextInt(1);
		int numOfDevices;
		String msg = "";

		switch (action) {
		case 0: // remove devices
			numOfDevices = rand.nextInt(devices.size() + 1);
			for (int i = 0; i < numOfDevices; i++) {
				devices.remove(0);
			}
			msg = numOfDevices + " devices removed";
			break;
		case 1: // add devices
			numOfDevices = rand.nextInt(10) + 1;
			for (int i = 0; i < numOfDevices; i++) {
				int typeIndex = rand.nextInt(Type.values().length - 1);
				devices.add(new Device(Type.values()[typeIndex], i * 100 + "", "TOYOTA", 5.5));
			}
			msg = numOfDevices + " devices added";
			break;
		}
		System.out.println("updated: " + msg);
		return devices;
	}

	@Override
	public String toString() {
		return super.toString() + "IOTThing [devices=" + devices + "]";
	}

}
