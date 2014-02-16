package org.gunisalvo.automaton.webHook;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.gunisalvo.automaton.controller.web.WebSimulationController;
import org.gunisalvo.automaton.monitor.SystemUnavailable;
import org.gunisalvo.automaton.webHook.exception.RequestFaliedException;
import org.gunisalvo.automaton.webHook.exception.ServiceUnavailableException;

@SlaHook
@Interceptor
public class SlaEnforcer implements Serializable{

	private static final long serialVersionUID = -3312492311628056730L;
	
	@Inject
    private WebSimulationController controller;

	@AroundInvoke
	public Object enforceSla(InvocationContext context) throws Exception {
		Long timestamp = System.currentTimeMillis();
		
		if(this.controller.isSlaComplient(timestamp) && this.controller.isAvailable()){
			SystemUnavailable unavailability = this.controller.buildUnavailability(timestamp);
			if(unavailability != null){
				new Thread(unavailability).start();
			}
		}
		
		if(this.controller.isSlaComplient(timestamp)){
			if(this.controller.isAvailable()){
				this.controller.registerRequest(true);
				throw new RequestFaliedException(timestamp);
			}else{
				this.controller.registerRequestOnDowntime();
				throw new ServiceUnavailableException(timestamp);
			}
		}else{
			if(this.controller.isAvailable()){
				this.controller.registerRequest(false);
				return context.proceed();
			}else{
				this.controller.registerRequestOnDowntime();
				throw new ServiceUnavailableException(timestamp);
			}
		}
		
	}
	

}
