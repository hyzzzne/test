package com.javalab.javaconfig.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@Data
public class Restaurant {

	//세터주입
	//@Setter(onMethod_ = @Autowired)
	//private Chef chef;
	
	//주입될 객체를 변동 불가능하도록 설정(immutable:불변객체)
	private final Chef chef;
	
	//생성자 주입
//	public Restaurant(Chef chef) {
//		this.chef = chef;
//	}
	
	//세터 주입은 이뤄지지 않음(Chef를 final로 선언했기 때문에)

}
