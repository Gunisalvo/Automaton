package org.gunisalvo.automaton.controller;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.gunisalvo.automaton.controller.xml.AutomatonConfiguration;
import org.gunisalvo.automaton.exception.AutomatonException;
import org.gunisalvo.automaton.monitor.SimulationMonitor;

public abstract class SimulationController {
	
	protected SimulationMonitor monitor;
	
	protected AutomatonConfiguration configuration;
	
	public void configure(String configurationPath) {
		try{
			InputStream is = this.getClass().getResourceAsStream(configurationPath);
			JAXBContext jaxbContext = JAXBContext.newInstance(AutomatonConfiguration.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			this.configuration = (AutomatonConfiguration) jaxbUnmarshaller.unmarshal(is);
			if(this.monitor == null){
				this.monitor = new SimulationMonitor();
			}
			this.monitor.reset();
			if(this.configuration.getServiceLevelAgreement() == null){
				this.monitor.loadSla(this.configuration.getServiceLevelAgreementFile());
			}else{
				this.monitor.loadSla(this.configuration.getServiceLevelAgreement());
			}
		}catch(Exception ex){
			throw new AutomatonException(ex);
		}
	}

	public void configure(InputStream resourceAsStream) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AutomatonConfiguration.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			this.configuration = (AutomatonConfiguration) jaxbUnmarshaller
					.unmarshal(resourceAsStream);
			if (this.monitor == null) {
				this.monitor = new SimulationMonitor();
			}
			this.monitor.reset();
			if (this.configuration.getServiceLevelAgreement() == null) {
				this.monitor.loadSla(this.configuration.getServiceLevelAgreementFile());
			} else {
				this.monitor.loadSla(this.configuration.getServiceLevelAgreement());
			}
		} catch (Exception ex) {
			throw new AutomatonException(ex);
		}
	}

}
