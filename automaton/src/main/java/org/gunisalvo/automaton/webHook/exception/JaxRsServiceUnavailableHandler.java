package org.gunisalvo.automaton.webHook.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JaxRsServiceUnavailableHandler implements ExceptionMapper<ServiceUnavailableException>{
		
		@Override
		public Response toResponse(ServiceUnavailableException ex) {
			return Response.status(Status.OK).type(MediaType.TEXT_PLAIN_TYPE).entity(ex.getMessage()).build();
		}
		
}