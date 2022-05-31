package inventory.DBService;

import java.util.Map;

import inventory.models.Device;

public class DeviceDBService {
	private DBService db;
	private Map<Long, Device> devices;

	public DeviceDBService() {
		// this.db = db; TODO: db = MyDB.getInstance();
		// this.things = things; TODO: students = db.getStudents();
	}
}
