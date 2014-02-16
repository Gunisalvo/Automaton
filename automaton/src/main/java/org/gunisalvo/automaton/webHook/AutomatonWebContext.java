package org.gunisalvo.automaton.webHook;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.gunisalvo.automaton.controller.web.WebSimulationController;

@WebListener
public class AutomatonWebContext implements ServletContextListener{
	
	@Inject
	private WebSimulationController controller;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		this.controller.warn("AutomatonWebContext initialiazed");
		this.controller.configure(event.getServletContext().getResourceAsStream("/WEB-INF/automaton.xml"));
		this.controller.warn("SLA defined as: " + this.controller.getConfiguration().getServiceLevelAgreement());
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		this.controller.warn("AutomatonWebContext destroyed");
	}

}
