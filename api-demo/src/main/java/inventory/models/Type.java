package inventory.models;

public enum Type {
	CONTROLLER(0), SENSOR(1), ACTUATOR(2);

	int index;

	private Type(int index) {
		this.index = index;
	}

}
