package inventory.models;

import java.util.UUID;

public class Device extends Hardware {

	private double reading;

	public Device() {
		super();
	}

	public Device(UUID id, Type type, String model, String manufacturer, double reading) {
		super(id, type, model, manufacturer);
		this.reading = reading;
	}

	public Device(double reading) {
		super();
		this.reading = reading;
	}

	public void simulateReading() {
		// TODO
	}
}
