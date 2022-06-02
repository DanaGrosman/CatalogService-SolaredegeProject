package ajbc.webservice.rest.api_demo.inventory.resources;

import java.util.List;
import java.util.UUID;

import ajbc.webservice.rest.api_demo.inventory.DBService.DBService;
import ajbc.webservice.rest.api_demo.inventory.beans.ThingDeviceFilterBean;
import ajbc.webservice.rest.api_demo.inventory.models.IOTThing;
import ajbc.webservice.rest.api_demo.inventory.models.Type;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("things")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IOTThingResource {
	DBService dbService = new DBService();

//	@GET
//	public List<IOTThing> getAllThings() {
//		return dbService.getAllIotThings();
//	}

	@GET
	@Path("/{id}")
	public IOTThing getThingById(@PathParam("id") UUID id) {
		return dbService.getThingById(id);
	}

//	@GET
//	@Path("/{typeStr}")
//	public List<IOTThing> getThingByType(@PathParam("typeStr") String typeStr) {
//		Type type = Type.valueOf(typeStr);
//		return dbService.getThingByType(type);
//	}

	@GET
	public List<IOTThing> getThingByTypeModelManufacturer(@BeanParam ThingDeviceFilterBean filterBean) {
		if (filterBean.getType() != null)
			return dbService.getThingByType(Type.valueOf(filterBean.getType().toUpperCase()));
		if (filterBean.getModel() != null)
			return dbService.getThingByModel(filterBean.getModel());
		if (filterBean.getManufacturer() != null)
			return dbService.getThingByManufacturer(filterBean.getManufacturer());
		return dbService.getAllIotThings();
	}

}
