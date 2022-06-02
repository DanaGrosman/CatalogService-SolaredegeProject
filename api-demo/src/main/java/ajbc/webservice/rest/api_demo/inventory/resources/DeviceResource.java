package ajbc.webservice.rest.api_demo.inventory.resources;

import java.util.List;
import java.util.UUID;

import ajbc.webservice.rest.api_demo.inventory.DBService.DBService;
import ajbc.webservice.rest.api_demo.inventory.beans.ThingDeviceFilterBean;
import ajbc.webservice.rest.api_demo.inventory.models.Device;
import ajbc.webservice.rest.api_demo.inventory.models.Type;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("devices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeviceResource {
	DBService dbService = new DBService();
	
	@GET
	@Path("/{id}")
	public Device getDeviceById(@PathParam("id") UUID id) {
		return dbService.getDeviceById(id);
	}
	
	@GET
	public List<Device> getDeviceByTypeModelManufacturer(@BeanParam ThingDeviceFilterBean filterBean) {
		if (filterBean.getType() != null)
			return dbService.getDeviceByType(Type.valueOf(filterBean.getType().toUpperCase()));
		if (filterBean.getModel() != null)
			return dbService.getDeviceByModel(filterBean.getModel());
		if (filterBean.getManufacturer() != null)
			return dbService.getDeviceByManufacturer(filterBean.getManufacturer());
		return dbService.getAllDevices();
	}
	
}
