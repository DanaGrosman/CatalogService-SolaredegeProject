package ajbc.webservice.rest.api_demo.inventory.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import ajbc.webservice.rest.api_demo.inventory.models.Device;
import ajbc.webservice.rest.api_demo.inventory.models.IOTThing;
import ajbc.webservice.rest.api_demo.inventory.models.Type;

public class DBMock {
	private static DBMock instance = null;
	private Map<UUID, IOTThing> iotThings;
	private Map<UUID, Device> devices;

	public static synchronized DBMock getInstance() {
		if (instance == null)
			instance = new DBMock();
		return instance;
	}

	private DBMock() {
		iotThings = new HashMap<UUID, IOTThing>();
		devices = new HashMap<UUID, Device>();
		// seeding the db
		//seed();
	}

	private void seed() {
		List<Device> devicesList = Arrays.asList(new Device(Type.CONTROLLER, "Moses", "OOfnik", 9.2),
				new Device(Type.CONTROLLER, "Rachel", "Palace", 4.8),
				new Device(Type.ACTUATOR, "Happy", "Roller", 5.5));
		
		devicesList = new ArrayList<Device>(devicesList);
		
		devices = devicesList.stream().collect(Collectors.toMap(Device::getId, Function.identity()));
		
		List<IOTThing> thingsList = Arrays.asList(new IOTThing(Type.CONTROLLER, "Moses", "OOfnik", devicesList),
				new IOTThing(Type.ACTUATOR, "Happy", "Roller", devicesList), new IOTThing(Type.SENSOR, "Gabby", "Dice", devicesList),
				new IOTThing(Type.SENSOR, "Charles", "Samson", devicesList), new IOTThing(Type.CONTROLLER, "Rachel", "Palace", devicesList));

		iotThings = thingsList.stream().collect(Collectors.toMap(IOTThing::getId, Function.identity()));

	}

	public Map<UUID, IOTThing> getThings() {
		return iotThings;
	}

	public Map<UUID, Device> getDevices() {
		return devices;
	}
}
