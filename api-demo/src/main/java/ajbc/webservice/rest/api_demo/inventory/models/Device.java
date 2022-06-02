package ajbc.webservice.rest.api_demo.inventory.models;

public class Device extends Hardware {

	private double reading;

	public Device() {
		super();
	}

	public Device(Type type, String model, String manufacturer, double reading) {
		super(type, model, manufacturer);
		this.reading = reading;
	}

	public void simulateReading() {
		reading++;
	}
}
