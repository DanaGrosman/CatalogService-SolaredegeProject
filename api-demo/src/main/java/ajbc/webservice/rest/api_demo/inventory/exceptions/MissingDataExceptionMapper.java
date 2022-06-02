package ajbc.webservice.rest.api_demo.inventory.exceptions;

import ajbc.webservice.rest.api_demo.inventory.models.ErrorMessage;
import ajbc.webservice.rest.api_demo.inventory.models.InternalErrorCode;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MissingDataExceptionMapper implements ExceptionMapper<MissingDataException> {

	@Override
	public Response toResponse(MissingDataException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), "www.ask.guy.com",
				InternalErrorCode.INVALID_ID);
		return Response.status(InternalErrorCode.INVALID_ID.getCodeNum()).entity(errorMessage).build();
	}

}
