package com.javalab.javaconfig.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import lombok.extern.log4j.Log4j;

/**
 * @Configuration : XML 설정에서 servlet-context.xml이 하는 디스패처 서블릿 환경 설정 정보 제공 역할. 
 * @EnableWebMvc : 어노테이션 기반의 SpringMvc를 구성 <mvc:annotation-driven/> 태그와 동일.
 *                 이 어노테이션은 뷰리졸버, HandlerMapping, HandlerAdapter 등을 빈등록한다. 
 *                 이를 통해서 등록될 빈들을 커스터마이징할 때는 WebMvcConfigurer를 사용한다.
 * @ComponentScan : controller 패키지 안에 있는 빈들을 등록할 수 있도록 패키지 경로 제공 
 * extends WebMvcConfigurationSupport 방식으로 설정할 수도 있음.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.javalab.javaconfig.controller")
public class ServletConfig implements WebMvcConfigurer {  
	
	public ServletConfig() {
    	System.out.println("ServletConfig : >>>>>>>>>>> ServletConfig() 생성자");
	}
	
	/*
	 * 뷰 리졸버 설정(default 메소드)
	 */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
		registry.viewResolver(bean);
    }

    /*
     * 정적자원(CSS, JS, Image)
     * web.xml의 <resources mapping="/resources/**" location="/resources/" />
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	System.out.println("ServletConfig 정적 자원관리: >>>>>>>>>>> addResourceHandlers");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    
    /*
    @Bean(name="multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException{
    	CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    	//10MB
    	resolver.setMaxUploadSize(1024 * 1024 * 10);
    	//2MB
    	resolver.setMaxUploadSizePerFile(1024 * 1024 * 2);
    	//1MB
    	resolver.setMaxInMemorySize(1024 * 1024);
    	//temp upload
    	resolver.setUploadTempDir(new FileSystemResource("c:\\upload\\tmp"));
    	resolver.setDefaultEncoding("UTF-8");
    	return resolver;
    }
    */
    
    /**
     * 파일 업로드
     */
    /*
    @Bean
    public MultipartResolver multipartResolver() {
    	StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
    	return resolver;
    }
    */
} 