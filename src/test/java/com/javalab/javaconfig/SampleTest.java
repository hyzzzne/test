package com.javalab.javaconfig;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javalab.javaconfig.config.RootConfig;
import com.javalab.javaconfig.domain.Restaurant;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)	//스프링을 실행하는 역할
@ContextConfiguration(classes= {RootConfig.class})	//해당 설정파일 안에 객체들을 Bean으로 등록
@Log4j // @Slf4j 대신 사용 가능
public class SampleTest {

	@Autowired
	private Restaurant restaurant;
	
	public SampleTest() {
	}
	
	@Test
	public void testExist() {
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("----------------------------");
		log.info(restaurant.getChef());
	}

}
