package ajbc.webservice.rest.api_demo.inventory.DBService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ajbc.webservice.rest.api_demo.inventory.db.DBMock;
import ajbc.webservice.rest.api_demo.inventory.exceptions.MissingDataException;
import ajbc.webservice.rest.api_demo.inventory.models.Device;
import ajbc.webservice.rest.api_demo.inventory.models.IOTThing;
import ajbc.webservice.rest.api_demo.inventory.models.Type;
import jakarta.ws.rs.client.Entity;

public class DBService {
	private DBMock db;
	private Map<UUID, IOTThing> things;
	private Map<UUID, Device> devices;

	public DBService() {
		db = DBMock.getInstance();
		things = db.getThings();
		devices = db.getDevices();
	}

	// returns all the things from the DB as a list
	public List<IOTThing> getAllIotThings() {
		return new ArrayList<IOTThing>(things.values());
	}

	// returns all the devices from the DB as a list
	public List<Device> getAllDevices() {
		return new ArrayList<Device>(devices.values());
	}

	// get device by id
	public Device getDeviceById(UUID id) {
		Device device = devices.get(id);
		if (device == null)
			throw new MissingDataException("id " + id + " is not a valid device ID");
		return device;
	}

	// get thing by id
	public IOTThing getThingById(UUID id) {
		IOTThing thing = things.get(id);
		if (thing == null)
			throw new MissingDataException("id " + id + " is not a valid thing ID");
		return thing;
	}

	// add thing to DB
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

	// get a thing by type
	public List<IOTThing> getThingByType(Type type) {
		List<IOTThing> thingsByType = new ArrayList<IOTThing>();
		for (Map.Entry<UUID, IOTThing> entry : things.entrySet()) {
			IOTThing thing = entry.getValue();
			if (thing.getType() == type)
				thingsByType.add(thing);
		}

		return thingsByType;
	}

	// get a thing by model
	public List<IOTThing> getThingByModel(String model) {
		List<IOTThing> thingsByModel = new ArrayList<IOTThing>();
		for (Map.Entry<UUID, IOTThing> entry : things.entrySet()) {
			IOTThing thing = entry.getValue();
			if (thing.getModel().equals(model))
				thingsByModel.add(thing);
		}

		return thingsByModel;
	}

	// get a thing by manufacturer
	public List<IOTThing> getThingByManufacturer(String manufacturer) {
		List<IOTThing> thingsByManufacturer = new ArrayList<IOTThing>();
		for (Map.Entry<UUID, IOTThing> entry : things.entrySet()) {
			IOTThing thing = entry.getValue();
			if (thing.getManufacturer().equals(manufacturer))
				thingsByManufacturer.add(thing);
		}

		return thingsByManufacturer;
	}

	// get a device by type
	public List<Device> getDeviceByType(Type type) {
		List<Device> devicesByType = new ArrayList<Device>();
		for (Map.Entry<UUID, Device> entry : devices.entrySet()) {
			Device device = entry.getValue();
			if (device.getType() == type)
				devicesByType.add(device);
		}

		return devicesByType;
	}
	
	// get a device by model
	public List<Device> getDeviceByModel(String model) {
		List<Device> devicesByModel = new ArrayList<Device>();
		for (Map.Entry<UUID, Device> entry : devices.entrySet()) {
			Device device = entry.getValue();
			if (device.getModel().equals(model))
				devicesByModel.add(device);
		}

		return devicesByModel;
	}

	// get a device by manufacturer
	public List<Device> getDeviceByManufacturer(String manufacturer) {
		List<Device> devicesByManufacturer = new ArrayList<Device>();
		for (Map.Entry<UUID, Device> entry : devices.entrySet()) {
			Device device = entry.getValue();
			if (device.getManufacturer().equals(manufacturer))
				devicesByManufacturer.add(device);
		}

		return devicesByManufacturer;
	}

	// get devices by thingId
	public List<Device> getDevicesByThingId(UUID thingId) {
		IOTThing iotThing = getThingById(thingId);

		if (iotThing == null)
			throw new MissingDataException("Thing with id %d is not in DB".formatted(thingId));

		return iotThing.getDevices();
	}

}
