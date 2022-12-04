package com.javalab.javaconfig.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import lombok.extern.log4j.Log4j;

/**
 * web.xml 대체 클래스
 */
@Log4j
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
							   
	public WebConfig() {
	}
	
	/*
	 * Root Application Context
	 * @Service @Repository 관련 설정을 하는 클래스를 지정하는 메소드
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.info("WebConfig : >>>>>>>>>>> getRootConfigClasses()");
		return new Class<?>[]{RootConfig.class};
	}
	
	/*
	 * Servlet Application Context
	 * @Controller 관련 설정을 하는 클래스를 지정하는 메소드
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		log.info("WebConfig : >>>>>>>>>>> getServletConfigClasses()");
		return new Class<?>[] {ServletConfig.class};
	}
	
	/*
	 * [getServletMappings] 브라우저가 요청한 주소 패턴을 보고 
	 * Spring에서 처리할지 말지를 결정할 수 있도록 해주는 메서드. 
	 * return new String[] {"/"}; 라고 해주면 모든 요청에 대해 
	 * Spring에서 처리해주겠다는 의미임. 만약 특정 패턴의 주소만 
	 * 처리해주겠다 하면 그 패턴의 주소 문자열을 셋팅하면 됨.
	 */
	@Override
    protected String[] getServletMappings() {
		log.info("WebConfig : >>>>>>>>>>> getServletMappings()");
		return new String[]{ "/" };
        // ex) return new String[] { "*.html", "*.json", "*.do" };
    }

	/**
	 * 오류 처리할 핸들러가 없을 경우 noHandlerException을 던질지 여부 결정(true-던짐)
	 */
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		log.info("WebConfig : >>>>>>>>>>> customizeRegistration()");
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}

	// 한글 인코딩 필터
	@Override
	protected Filter[] getServletFilters() { 	// Filter[] :javax.servlet;
		log.info("WebConfig : >>>>>>>>>>> getServletFilters()");
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter};      
	}	
	
}
