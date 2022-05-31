package inventory.DBService;

import java.util.Map;
import inventory.models.IOTThing;

public class IOTThingDBService {
	private DBService db;
	private Map<Long, IOTThing> things;

	public IOTThingDBService() {
		// this.db = db; TODO: db = MyDB.getInstance();
		// this.things = things; TODO: students = db.getStudents();
	}

}
