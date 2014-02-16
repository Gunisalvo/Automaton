package org.gunisalvo.automatonWeb.resource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.gunisalvo.automaton.webHook.exception.JaxRsRequestFailedHandler;
import org.gunisalvo.automaton.webHook.exception.JaxRsServiceUnavailableHandler;


@ApplicationPath("/v0")
public class ApplicationRoot extends Application {

	@SuppressWarnings("unchecked")
	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>(Arrays.asList(	ResourceInterface.class,
													JaxRsRequestFailedHandler.class,
													JaxRsServiceUnavailableHandler.class));
	}
}
