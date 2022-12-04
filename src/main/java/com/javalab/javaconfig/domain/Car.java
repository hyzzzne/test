package com.javalab.javaconfig.domain;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Car {
	private String name;
	private String maker;
	private Long price;
	
	public Car(String name, String maker, Long price) {
		this.name = name;
		this.maker = maker;
		this.price = price;
	}
}
