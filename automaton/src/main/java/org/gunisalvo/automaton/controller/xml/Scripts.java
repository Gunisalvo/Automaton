package org.gunisalvo.automaton.controller.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="scripts")
public class Scripts {
	
	private List<String> script;

	public Scripts() {
	}
	
	public Scripts(List<String> script) {
		this.script = script;
	}

	public List<String> getScript() {
		return script;
	}

	public void setScript(List<String> script) {
		this.script = script;
	}

	public Integer size() {
		if(this.script == null){
			return 0;
		}else{
			return this.script.size();
		}
	}

	@Override
	public String toString() {
		return "Scripts [script=" + script + "]";
	}

}
