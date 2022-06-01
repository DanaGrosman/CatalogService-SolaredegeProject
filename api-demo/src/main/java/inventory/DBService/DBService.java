package inventory.DBService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import inventory.db.DBMock;
import inventory.models.Device;
import inventory.models.IOTThing;

public class DBService {
	private DBMock db;
	private Map<UUID, IOTThing> things;
	private Map<UUID, Device> devices;

	public DBService() {
		db = DBMock.getInstance();
		things = db.getThings();
	}

	// returns all the iotthings from the DB as a list
	public List<IOTThing> getAllIotThings() {
		return new ArrayList<IOTThing>(things.values());
	}

	// returns all the devices from the DB as a list
	public List<Device> getAllDevices() {
		return new ArrayList<Device>(devices.values());
	}

//	// get course by id
//	public Course getCourseById(Long id) {
//		return courses.get(id);
//	}
//
	// add course to DB
	public IOTThing addThing(IOTThing thing) {
		things.put(thing.getId(), thing);
		return thing;
	}

	// add Device to DB
	public Device addDevice(Device device) {
		devices.put(device.getId(), device);
		return device;
	}

	// update existing things
	public IOTThing updateThing(UUID id, IOTThing iotThing) {
		updateMapOfDevices(iotThing);

		if (things.containsKey(id)) {
			IOTThing currThing = things.get(id);
			currThing.setDevices(iotThing.getDevices());
			currThing.setId(iotThing.getId());
			currThing.setManufacturer(iotThing.getManufacturer());
			currThing.setModel(iotThing.getModel());
			currThing.setType(iotThing.getType());

			things.put(id, currThing);
			return currThing;
		}
		return null;
	}

	private void updateMapOfDevices(IOTThing iotThing) {
		if (things.containsKey(iotThing.getId())) {
			List<Device> oldListDevices = things.get(iotThing.getId()).getDevices();
			if (devices != null)
				for (Device device : oldListDevices) {
					if (!iotThing.getDevices().contains(device))
						deleteDevice(device.getId());
				}
		}

		for (Device device : iotThing.getDevices()) {
			if (devices != null)
				if (!devices.containsKey(device.getId()))
					addDevice(device);
		}
	}

	// delete a thing from map
	public IOTThing deleteIOTThing(UUID id) {
		return things.remove(id);
	}

	// delete a device from map
	public Device deleteDevice(UUID id) {
		return devices.remove(id);
	}
}
