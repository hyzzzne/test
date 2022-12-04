package com.javalab.javaconfig.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.javalab.javaconfig.config.RootConfig;
import com.javalab.javaconfig.config.ServletConfig;
import com.javalab.javaconfig.config.WebConfig;
import com.javalab.javaconfig.dao.BbsMapperTest;
import com.javalab.javaconfig.domain.Car;
import com.javalab.javaconfig.domain.Person;
import com.javalab.javaconfig.domain.Ticket;

import lombok.Setter;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

/**
 * 컨트롤러 테스트 : MockMvc
 *  - 사용자로부터 특정 요청이 온걸로 가정하고 테스트(Mock : 거짓된, 가짜의, 모의)
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration	//Test for Controller
//@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
//						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
//단위테스트를 스프링과 연동
@RunWith(SpringJUnit4ClassRunner.class)	//스프링을 실행하는 역할
@ContextConfiguration(classes= {RootConfig.class, ServletConfig.class})	//해당 설정파일 안에 객체들을 Bean으로 등록
@Log4j // @Slf4j 대신 사용 가능

@Slf4j
public class ControllerMockTest {

	@Autowired
	private WebApplicationContext ctx;
	
	/*
	 * MockMvc
	 *  - 테스트를 위한 모의 객체 생성
	 *  - Service 계층이나 Mapper 계층을 테스트 할 때는 WAS를 구동할 필요가 없다.
	 *  - Controller 를 테스트 할 때는 WAS를 구동하고 URL로 테스트를 해야 하는데 이는
	 * 	   여간 불편한게 아니다. 그래서  WAS 구동없이 컨트롤러를 테스트하기 위해서 사용  

	 */
	private MockMvc mockMvc;
	
	// 테스트 전에 실행되어서 환경정보를 미리 갖고 옴
	@Before
	public void setup(){
		log.info("Mock 테스트 전 컨텍스트 환경정보 생성");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test 
	public void testGetText() throws Exception{
		String tempString = "";
		mockMvc.perform(get("/bbs/getQnaBbsList")		// 가상의 요청 생성(get 방식)
				.contentType(MediaType.APPLICATION_JSON)	// 보내는 데이터의 타입
				.content(tempString))	// 서버로 보내는 데이터
				.andExpect(status().is(200));	// 서버로 부터 받은 처리 결과(status)가 200번인가?(성공인가?)
	}
	
	@Test @Ignore
	public void testConvert1() throws Exception{

		// [1] 테스트에 사용할 객체 생성
		Ticket ticket = new Ticket();
		ticket.setTno(100);
		ticket.setOwner("홍길동");
		ticket.setGrade("A등급");
		
		// [2] 자바 객체 -> JSON문자열로 변환(Gson 라이브러리 사용)
		String jsonStr = new Gson().toJson(ticket);
		log.info("티켓 Java Object ----> JSON 문자열로 변환된 형태 : " + jsonStr);
		
		// [3] 사용자로부터 요청이 온 것처럼 가상의 요청 생성
		mockMvc.perform(
			post("/sample/ticket")		// 가상의 요청 생성(post 방식)
			.contentType(MediaType.APPLICATION_JSON)	// 보내는 데이터의 타입
			.content(jsonStr))	// 사용자에게 처리 결과 전송(post 요청이므로 메시지 바디에 저장되 서버로 전송)
			.andExpect(status().is(200));	// 서버로 부터 받은 처리 결과(status)가 200번인가?(성공인가?)
	}
	
	@Test @Ignore
	public void testConvert2() throws Exception{
		
		// 사용자에게 전송할 객체 생성
		Ticket ticket = new Ticket();
		ticket.setTno(123);
		ticket.setOwner("홍길동");
		ticket.setGrade("A등급");
		
		// [1] 방법. 자바 객체 -> JSON문자열로 변환(Gson 라이브러리 사용)
		String jsonStr = new Gson().toJson(ticket);
		log.info("티켓 Java Object ----> JSON 문자열로 변환된 형태 : " + jsonStr);
		
		// [2] 방법. 자바 객체 -> JSON문자열로 변환(Gson 라이브러리 사용)
		Person person = new Person("홍길동", 24);
		Gson gson = new GsonBuilder().create();
		jsonStr = gson.toJson(person); 
		log.info("Person Java Object ----> JSON 문자열로 변환 : " + jsonStr);
		
		// 사용자로부터 요청이 온 것처림 가상의 요청 생성
		mockMvc.perform(post("/sample/personInfo")	// 이런 형태의 URL 요청이 왔다.
				.contentType(MediaType.APPLICATION_JSON)	// 처리 결과 전송 형태를 이렇게 하겠다.
				.content(jsonStr))	// 사용자에게 보낼 데이터는 이거다.
				.andExpect(status().is(200));	// 서버로 부터 받은 처리 결과(status)가 200번인가?(성공인가?)

		// ObjectMapper 객체를 사용해서 자바객체 <---> JSON문자열 변환 가능(Gson 라이브러리 사용없이)
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		map.put("name", "홍길동");
		map.put("age", "25");
		map.put("job", "baker");
		String jsonStr2 = mapper.writeValueAsString(map);
		log.info(jsonStr2);
	}
	
	@Test @Ignore
	public void testObjectMapper1()  {
		
		// jackson의 ObjectMapper 이용해서 자바객체 <--> JSON 타입 간의 변환
		//자바객체를 생성해서 새로운 파일을 만들고 거기다 JSON 타입으로 써줌.
		ObjectMapper mapper = new ObjectMapper();
		Car car = new Car("소나타", "현대", 200L);
		try {
			mapper.writeValue(new File("target/car.json"), car);
			log.info("target/car.json 경로의 car.json 파일을 읽어보세요. car : " + car);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		car = new Car("아이오닉5", "현대", 300L);
		try {
			//자바객체를 생성해서 JSON 타입 문자열로 만들어줌.
			String jsonCar = mapper.writeValueAsString(car);
			log.info("자바 객체를 JSON 문자열로 변환 : " + jsonCar);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//ObjectMapper 이용해서 파일의 JSON 타입을 자바 객체로 읽어옴[바인딩]
		ObjectMapper om = new ObjectMapper(); // can reuse, share globally		
		try {
			Car car1 = om.readValue(new File("target/car.json"), Car.class);
			log.info("파일에 JSON 형태로 저장되어 있는 내용을 읽어서 Car 객체 생성 : " + car1);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@Test @Ignore
	public void testGsonObject()  {
//		FileWriter fileWriter = null;
//		String jsonStr = "{\"name\":\"소나타\",\"maker\":\"현대\",\"price\":200}";
//		JsonObject jsonObject = JsonParser.parseString(jsonStr).getAsJsonObject();
//		log.info("jsonObject : " + jsonObject);
		
	}
}

