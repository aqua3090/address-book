package au.com.reece.addressbook.exception;

import io.dropwizard.jersey.errors.ErrorMessage;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author anjali
 */
public class ApplicationExceptionMapper implements ExceptionMapper<AddressBookApplicationException> {

        @Override
	public Response toResponse(AddressBookApplicationException ex) {
		return Response.status(ex.getStatus())
				.entity(new ErrorMessage(ex.getStatus(),ex.getDeveloperMessage()))
				.type(MediaType.APPLICATION_JSON).
				build();
	}

}    

