package com.javalab.javaconfig.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javalab.javaconfig.config.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)	//스프링을 실행하는 역할
@ContextConfiguration(classes= {RootConfig.class})	//해당 설정파일 안에 객체들을 Bean으로 등록
@Log4j // @Slf4j 대신 사용 가능
public class DatasourceTest {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	public DatasourceTest() {
	}

	
	@Test @Ignore
	public void testDataSource() {
		assertNotNull(dataSource);
		
		log.info("----------------------------------------------------------");
		log.info("DataSource : " + dataSource);
		log.info("----------------------------------------------------------");
	}

	@Test @Ignore
	public void testSqlSession() {
		assertNotNull(sqlSessionFactory);
		
		log.info("----------------------------------------------------------");
		log.info("sqlSessionFactory : " + sqlSessionFactory);
		log.info("----------------------------------------------------------");
	}	
	
	@Test 
	public void testMyBatis() {	
		try(SqlSession session = sqlSessionFactory.openSession();
			Connection conn = session.getConnection()){
			log.info("session 객체 : " + session);
			log.info("커넥션 객체 : " + conn);	// HikariProxyConnection@1608633989
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}	
}
