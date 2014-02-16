package org.gunisalvo.automaton.webHook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.gunisalvo.automaton.controller.web.WebSimulationController;
import org.gunisalvo.automaton.webHook.exception.RequestFaliedException;
import org.gunisalvo.automaton.webHook.exception.ServiceUnavailableException;

@WebFilter
public class AutomatonFilter implements Filter{

	@Inject
	private WebSimulationController controller;
	
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.controller.warn("AutomatonFilter initialized");
	}
	
	@Override
	public void destroy() {
		this.controller.warn("AutomatonFilter destroyed");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			chain.doFilter(request, response);
		}catch(RequestFaliedException ex){
			processException(ex,response);
		}catch(ServiceUnavailableException ex){
			processException(ex,response);
		}
		
	}
	
	private void processException(Exception ex, ServletResponse response) throws IOException{
		this.controller.warn(ex);
		PrintWriter exit = (( HttpServletResponse ) response).getWriter();
		exit.print("[Automaton]"+ex.getMessage());
		exit.flush();
		exit.close();
	}

}
