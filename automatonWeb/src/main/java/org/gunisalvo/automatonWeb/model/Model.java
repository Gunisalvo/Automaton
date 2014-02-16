package org.gunisalvo.automatonWeb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Model {
	
	private Integer code;
	
	private String name;

	public Model() {
	}
	
	public Model(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Model [code=" + code + ", name=" + name + "]";
	}

}
