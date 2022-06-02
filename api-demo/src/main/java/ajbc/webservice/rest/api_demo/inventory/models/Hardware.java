package ajbc.webservice.rest.api_demo.inventory.models;

import java.util.UUID;

public class Hardware {
	private UUID id;
	private Type type;
	private String model;
	private String manufacturer;

	public Hardware() {
	}
	
	public Hardware(Type type, String model, String manufacturer) {
		this.id = UUID.randomUUID();
		this.type = type;
		this.model = model;
		this.manufacturer = manufacturer;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "Hardware [id=" + id + ", type=" + type + ", model=" + model + ", manufacturer=" + manufacturer + "]";
	}

}
