package com.javalab.javaconfig.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.List;

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
import com.javalab.javaconfig.domain.QnaBbs;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)	//스프링을 실행하는 역할
@ContextConfiguration(classes= {RootConfig.class})	//해당 설정파일 안에 객체들을 Bean으로 등록
@Log4j // @Slf4j 대신 사용 가능
public class BbsMapperTest {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private IQnaBbsMapperDao qnaBbsMapperDao;
	
	public BbsMapperTest() {
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
	
	@Test @Ignore
	public void testConnection() {	
		try(SqlSession session = sqlSessionFactory.openSession();
			Connection conn = session.getConnection()){
			log.info("session 객체 : " + session);
			log.info("커넥션 객체 : " + conn);	// HikariProxyConnection@1608633989
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}	
	
	@Test @Ignore
	public void testGetBbsList() {	
		assertNotNull(qnaBbsMapperDao);
		log.info("qnaBbsMapperDao : " + qnaBbsMapperDao);	// MapperProxy@58fb7731		
		
		QnaBbs bbs = new QnaBbs();
		bbs.setTitle("제목");
		bbs.setWriter("writer");
		
		List<QnaBbs> bbsList = qnaBbsMapperDao.getQnaBbsList(bbs);
		bbsList.stream().forEach(b -> log.info("QnaBbs : " + b.toString()));
	}	
	
	@Test @Ignore
	public void testGetBbs() {	
		int no = 21;
		QnaBbs bbs = qnaBbsMapperDao.getBbsByNo(no); 
		log.info("QnaBbs : " + bbs.toString());
	}		
	
	@Test @Ignore
	public void testInsertBbs() {	
		// 저장할 객체 생성
		QnaBbs bbs = new QnaBbs();
		bbs.setTitle("제목12");
		bbs.setContent("내용12");
		bbs.setWriter("writer12");
		// 저장
		qnaBbsMapperDao.insertBbs(bbs);
		
		// 위에서 저장한 데이터 조회
		bbs = new QnaBbs();
		bbs.setTitle("제목10");
		bbs.setWriter("writer10");
		
		List<QnaBbs> bbsList = qnaBbsMapperDao.getQnaBbsList(bbs);
		bbsList.stream().forEach(b -> log.info("QnaBbs : " + b.toString()));
	}	
	
	@Test  @Ignore
	public void testUpdateBbs() {	
		// 수정할 객체 생성
		QnaBbs bbs = new QnaBbs();
		bbs.setNo(21);
		bbs.setTitle("제목21-1");
		bbs.setContent("내용21-1");
		bbs.setWriter("writer21-1");
		
		// 업데이트
		qnaBbsMapperDao.updateBbs(bbs);
		
		// 위에서 저장한 데이터 조회
		QnaBbs bbs2 = qnaBbsMapperDao.getBbsByNo(bbs.getNo()); 
		log.info("업데이트한 QnaBbs : " + bbs2.toString());
	}	

	@Test 
	public void testDeleteBbs() {	
		// 삭제
		int no = 2;
		qnaBbsMapperDao.deleteBbs(no);
		
		// 위에서 저장한 데이터 조회
		assertEquals(null, qnaBbsMapperDao.getBbsByNo(no));
	}	
	
}
