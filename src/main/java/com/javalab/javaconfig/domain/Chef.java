package com.javalab.javaconfig.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Chef {

	private String name;
	
	public Chef() {
		this.name = "이연복";
	}

}
