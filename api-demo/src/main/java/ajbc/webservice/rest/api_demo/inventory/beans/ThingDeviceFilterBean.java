package ajbc.webservice.rest.api_demo.inventory.beans;

import jakarta.ws.rs.QueryParam;

public class ThingDeviceFilterBean {

	@QueryParam("typeStr")
	String typeStr;
	@QueryParam("modelStr")
	String modelStr;
	@QueryParam("manufacturerStr")
	String manufacturerStr;

	public String getType() {
		return typeStr;
	}

	public void setType(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getModel() {
		return modelStr;
	}

	public void setModel(String modelStr) {
		this.modelStr = modelStr;
	}

	public String getManufacturer() {
		return manufacturerStr;
	}

	public void setManufacturer(String manufacturerStr) {
		this.manufacturerStr = manufacturerStr;
	}
}
