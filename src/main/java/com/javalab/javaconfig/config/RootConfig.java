package com.javalab.javaconfig.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.ognl.ParseException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * root-context.xml 환경설정파일 대체 클래스
 */
@Configuration
@ComponentScan(basePackages = {"com.javalab.javaconfig.service", 
								"com.javalab.javaconfig.dao"})
@MapperScan(basePackages={"com.javalab.javaconfig.dao"})
//@PropertySource(value="classpath:/database.properties")
public class RootConfig {

	public RootConfig() {
    	System.out.println("RootConfig : >>>>>>>>>>> RootConfig() 생성자");
	}

	/*
	 * @Bean
	 *  - XML에서 <bean> 태그로 등록했던 것과 유사함
	 *  - dataSource() 메소드를 통해서 반환되는 DataSoruce를 빈으로 등록한다는 의미
	 */
	@Bean  
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		//hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		//hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy"); // log4jdbc.log42j 설정후
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:orcl"); // log4jdbc.log42j 설정후
		hikariConfig.setUsername("ums");
		hikariConfig.setPassword("1234");

		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
	
	@Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource());
//        return (SqlSessionFactory)sqlSessionFactory.getObject();
		
		System.out.println("RootConfig : >>>>>>>>>>> sqlSessionFactory()");
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		Properties props = new Properties();
		//mapUnderscoreToCamelCase setting
		props.setProperty("mapUnderscoreToCamelCase", "true");
		sqlSessionFactoryBean.setConfigurationProperties(props);		
		//TypeAliases Package route setting
		sqlSessionFactoryBean.setTypeAliasesPackage("com.javalab.javaconfig.domain");
		//DataSource setting
		sqlSessionFactoryBean.setDataSource(this.dataSource());
		//mapper xml route setting 
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
			.getResources("classpath:/mapper/oracle/*Mapper.xml"));
		return sqlSessionFactoryBean.getObject();		
    }
	
	/**
	 * getSqlSessionTemplate Bean Creation from getSqlSessionFactoryBean()
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionTemplate getSqlSessionTemplate() throws Exception {
		SqlSessionTemplate sst = new SqlSessionTemplate(this.sqlSessionFactory());
		return sst;
	} 	
	
	/**
	 * Transaction Manager Bean Creation from dataSource()
	 * [주의사항] @Transactional 사용시 PlatformTransactionManager 혹은
	 *          DataSourceTransactionManager 둘 중 하나의 트랜잭션 관리자만 사용할것. 오류 발생!
	 */
	@Bean
    public PlatformTransactionManager getTransactionManager() 
    			throws URISyntaxException, GeneralSecurityException, ParseException, IOException {
		return new DataSourceTransactionManager(this.dataSource());
    } 	
	
}
