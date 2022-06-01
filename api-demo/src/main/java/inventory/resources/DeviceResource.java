package inventory.resources;

import java.util.List;
import java.util.UUID;

import inventory.DBService.DBService;
import inventory.models.Device;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("devices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeviceResource {
	DBService deviceDB = new DBService();
	
	@GET
	public List<Device> getAllDevices() {
		return deviceDB.getAllDevices();
	}
	
	@GET
	@Path("/{id}")
	public Device getDeviceById(@PathParam("id") UUID id) {
		return deviceDB.getDeviceById(id);
	}
	
}
