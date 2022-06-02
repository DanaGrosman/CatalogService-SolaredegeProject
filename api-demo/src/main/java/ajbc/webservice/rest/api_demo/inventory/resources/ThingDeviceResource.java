package ajbc.webservice.rest.api_demo.inventory.resources;

import java.util.List;
import java.util.UUID;

import ajbc.webservice.rest.api_demo.inventory.DBService.DBService;
import ajbc.webservice.rest.api_demo.inventory.models.Device;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ThingDeviceResource {
	private DBService dbService = new DBService();
	
	@GET
	@Path("/{thingId}")
	public Response getDevicesByThingId(@PathParam("thingId") UUID thingId) {
		List<Device> devices = dbService.getDevicesByThingId(thingId);
		return Response.ok().entity(devices).build();
	}
}
