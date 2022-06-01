package inventory.resources;

import java.util.List;
import java.util.UUID;

import inventory.DBService.DBService;
import inventory.models.IOTThing;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("things")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IOTThingResource {
	DBService dbService = new DBService();
	
	@GET
	public List<IOTThing> getAllThings() {
		return dbService.getAllIotThings();
	}
	
	@GET
	@Path("/{id}")
	public IOTThing getThingById(@PathParam("id") UUID id) {
		return dbService.getThingById(id);
	}
}
