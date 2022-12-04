package com.javalab.javaconfig.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.ognl.ParseException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
// import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages = "com.javalab.javaconfig.dao")
//sqlSessionFactoryRef="dataSourceDBCP", 
//sqlSessionTemplateRef="sqlSessionFactoryBean")
@EnableTransactionManagement
@PropertySource("classpath:/database.properties")
public class DbConfig extends WebMvcConfigurationSupport {
	@Value("${jdbc.driver}")
	private String driver;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	

	/**
	* DataSource DBCP Connection Bean creation
	* id : "dataSourceDBCP"
	* @return
	*/
	@Bean
	public DataSource getDataSourceDBCP() {
		BasicDataSource bd = new BasicDataSource();
		bd.setDriverClassName(this.driver);
		bd.setUrl(this.url);
		bd.setUsername(this.username);
		bd.setPassword(this.password);
		bd.setMaxTotal(10);
		bd.setMaxIdle(5);
		bd.setMaxWaitMillis(-1);
		return bd; 
	}
	
	/**
	* getSqlSessionFactoryBean Bean creation from getDataSourceDBCP()
	* @return
	* @throws Exception
	*/
	@Bean
	public SqlSessionFactory getSqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		Properties props = new Properties();
		//mapUnderscoreToCamelCase setting
		props.setProperty("mapUnderscoreToCamelCase", "true");
		sqlSessionFactoryBean.setConfigurationProperties(props); 
		//TypeAliases Package route setting
		sqlSessionFactoryBean.setTypeAliasesPackage("com.javateam.springTransactionAnnotation.domain");
		//DataSource setting
		sqlSessionFactoryBean.setDataSource(this.getDataSourceDBCP());
		//mapper xml route setting 
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
		.getResources("classpath:com/javateam/springTransactionAnnotation/mapper/*.xml"));
		/*PathMatchingResourcePatternResolver resolver 
		= new PathMatchingResourcePatternResolver();
		Resource[] resources 
		= resolver.getResources("classpath:com/javateam/springTransactionAnnotation/mapper/*.xml");
		​ssfb.setMapperLocations(resources);*/
		// return (SqlSessionFactoryBean) ssfb.getObject();
		return sqlSessionFactoryBean.getObject();
	}

	/**
	* getSqlSessionTemplate Bean Creation from getSqlSessionFactoryBean()
	* @return
	* @throws Exception
	*/
	@Bean
	public SqlSessionTemplate getSqlSessionTemplate() throws Exception {
		SqlSessionTemplate sst = new SqlSessionTemplate(this.getSqlSessionFactoryBean());
		//= new SqlSessionTemplate((SqlSessionFactory) this.getSqlSessionFactoryBean());
		return sst;
	}

	/**
	* Transaction Manager Bean Creation from getDataSourceDBCP
	* [주의사항] @Transactional 사용시 PlatformTransactionManager 혹은
	* DataSourceTransactionManager 둘 중 하나의 트랜잭션 관리자만 사용할것. 오류 발생!
	* @return
	* @throws URISyntaxException
	* @throws GeneralSecurityException
	* @throws ParseException
	* @throws IOException
	*/
	@Bean
	public PlatformTransactionManager getTransactionManager() 
		throws URISyntaxException, GeneralSecurityException, ParseException, IOException {
		return new DataSourceTransactionManager(this.getDataSourceDBCP());
	}

	/**
	 * [주의사항] @Transactional 사용시 PlatformTransactionManager 혹은
	 * DataSourceTransactionManager 둘 중 하나의 트랜잭션 관리자만 사용할것. 오류 발생!
	 */

	/*
	 * @Bean
	 * public DataSourceTransactionManager getTransactionManager2() {
	 * DataSourceTransactionManager dstm = new
	 * DataSourceTransactionManager(this.getDataSourceDBCP());
	 * return dstm;
	 * }
	 */

	/**
	 * DataSource JDBC Connection
	 * @return
	 */

	/*
	 * @Bean
	 * public DataSource getDataSourceJDBC() {
	 * SimpleDriverDataSource sdds = new SimpleDriverDataSource();
	 * sdds.setDriverClass(oracle.jdbc.OracleDriver.class);
	 * sdds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
	 * sdds.setUsername("java");
	 * sdds.setPassword("1234");
	 * return sdds;
	 * }
	 * 
	 */

}
