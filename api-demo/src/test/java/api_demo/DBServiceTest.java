package api_demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ajbc.webservice.rest.api_demo.inventory.DBService.DBService;
import ajbc.webservice.rest.api_demo.inventory.db.DBMock;
import ajbc.webservice.rest.api_demo.inventory.models.Device;
import ajbc.webservice.rest.api_demo.inventory.models.IOTThing;
import ajbc.webservice.rest.api_demo.inventory.models.Type;

class DBServiceTest {
	private DBService dbService = new DBService();

	@Test
	void getAllDevicesTest() {
		DBMock dbMock = DBMock.getInstance();
		List<Device> devices = dbService.getAllDevices();
		List<Device> devicesExepted = new ArrayList<Device>(dbMock.getDevices().values());
		assertEquals(devicesExepted, devices);
	}
	
	@Test
	void getAllThingssTest() {
		DBMock dbMock = DBMock.getInstance();
		List<IOTThing> things = dbService.getAllIotThings();
		List<IOTThing> thingsExepted = new ArrayList<IOTThing>(dbMock.getThings().values());
		assertEquals(thingsExepted, things);
	}


	@Test
	void getDeviceByIdTest() {
		Device device = dbService.getAllDevices().get(0);
		Device deviceExepted = dbService.getDeviceById(device.getId());
		assertEquals(deviceExepted, device);
	}

	@Test
	void getThingByIdTest() {
		IOTThing thing = dbService.getAllIotThings().get(0);
		IOTThing thingExepted = dbService.getThingById(thing.getId());
		assertEquals(thingExepted, thing);
	}
	
	@Test
	void addThingTest() {
		IOTThing thing = new IOTThing(Type.CONTROLLER,"1993", "OPEL", dbService.getAllDevices());
		dbService.addThing(thing);
		assertTrue(dbService.getAllIotThings().contains(thing));
	}
	
	@Test
	void addDeviceTest() {
		Device device = new Device(Type.CONTROLLER,"1993", "OPEL", 6.3);
		dbService.addDevice(device);
		assertTrue(dbService.getAllDevices().contains(device));
	}
	
	@Test
	void updateThingTest() {
		IOTThing thing = new IOTThing(Type.SENSOR,"2020", "OPEL_Pro", dbService.getAllDevices()); 
		dbService.addThing(thing);
		thing.getDevices().remove(0);
		assertEquals(thing.getDevices(), dbService.getThingById(thing.getId()).getDevices());
	}

	@Test
	void deleteIOTThingTest() {
		IOTThing thing = new IOTThing(Type.SENSOR,"2020", "OPEL_Pro", dbService.getAllDevices()); 
		dbService.addThing(thing);
		dbService.deleteIOTThing(thing.getId());
		assertFalse(dbService.getAllIotThings().contains(thing));
	}
	
	@Test
	void deleteDeviceTest() {
		Device device = new Device(Type.ACTUATOR,"2000", "BIBS", 8.3); 
		dbService.addDevice(device);
		dbService.deleteDevice(device.getId());
		assertFalse(dbService.getAllDevices().contains(device));
	}
	
	@Test
	void getThingByTypeTest() {
		IOTThing thing = new IOTThing(Type.SENSOR,"1999", "TOYOTA", dbService.getAllDevices()); 
		dbService.addThing(thing);
		assertTrue(dbService.getThingByType(Type.SENSOR).contains(thing));
	}
	
	@Test
	void getDeviceByTypeTest() {
		Device device = new Device(Type.ACTUATOR,"2000", "BIBS", 8.3); 
		dbService.addDevice(device);
		assertTrue(dbService.getDeviceByType(Type.ACTUATOR).contains(device));
	}
	
	@Test
	void getThingByModelTest() {
		IOTThing thing = new IOTThing(Type.SENSOR,"1999", "TOYOTA", dbService.getAllDevices()); 
		dbService.addThing(thing);
		assertTrue(dbService.getThingByModel("1999").contains(thing));
	}
	
	@Test
	void getDeviceByModelTest() {
		Device device = new Device(Type.ACTUATOR,"2000", "BIBS", 8.3); 
		dbService.addDevice(device);
		assertTrue(dbService.getDeviceByModel("2000").contains(device));
	}
	
	@Test
	void getThingByManufacturerTest() {
		IOTThing thing = new IOTThing(Type.SENSOR,"1999", "TOYOTA", dbService.getAllDevices()); 
		dbService.addThing(thing);
		assertTrue(dbService.getThingByManufacturer("TOYOTA").contains(thing));
	}
	
	@Test
	void getDeviecByManufacturerTest() {
		Device device = new Device(Type.ACTUATOR,"2000", "BIBS", 8.3); 
		dbService.addDevice(device);
		assertTrue(dbService.getDeviceByManufacturer("BIBS").contains(device));
	}
	
	@Test
	void getDevicesByThingIdTest() {
		IOTThing thing = new IOTThing(Type.SENSOR,"1999", "TOYOTA", dbService.getAllDevices()); 
		List<Device> devices = thing.getDevices();
		dbService.addThing(thing);
		List<Device> devicesExepted = dbService.getDevicesByThingId(thing.getId());
		assertEquals(devicesExepted, devices);
	}
}
