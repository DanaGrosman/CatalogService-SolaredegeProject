package ajbc.webservice.rest.api_demo.inventory.exceptions;

public class MissingDataException extends RuntimeException {
	private static final long serialVersionUID = 2226226832634160791L;

	public MissingDataException(String msg) {
		super(msg);
	}

}
