package inventory.db;

import java.util.Map;
import java.util.UUID;

import inventory.models.Device;
import inventory.models.IOTThing;

public class DBMock {
	private Map<UUID, IOTThing> iotThings;
	private Map<UUID, Device> devices;

}
