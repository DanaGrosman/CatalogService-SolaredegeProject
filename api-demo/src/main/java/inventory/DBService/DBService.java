package inventory.DBService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import inventory.db.DBMock;
import inventory.models.IOTThing;

public class DBService {
	private DBMock db;
	private Map<UUID, IOTThing> things;

	public DBService() {
		db = DBMock.getInstance();
		things = db.getThings();
	}

	// returns all the iotthings from the DB as a list
	public List<IOTThing> getAllIotThings() {
		return new ArrayList<IOTThing>(things.values());
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

	// update existing things
	public IOTThing updateThing(UUID id, IOTThing iotThing) {
		
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
	
//	// delete a course from map
//	public Course deleteCourse(long id) {
//		return courses.remove(id);
//	}
}
