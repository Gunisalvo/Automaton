package org.gunisalvo.automatonWeb.resource;

import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.gunisalvo.automaton.webHook.SlaHook;
import org.gunisalvo.automatonWeb.model.Model;

@Path("/")
public class ResourceInterface {
	
	@GET
	@Path("model")
	@Produces({MediaType.APPLICATION_XML})
	@SlaHook
	public Model getModel(){
		Integer code = new Random().nextInt(100);
		String name = "Test " + new Random().nextInt(100);
		return new Model(code,name);
	}
	

}
