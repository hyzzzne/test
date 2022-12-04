package com.javalab.javaconfig.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.javalab.javaconfig.domain.QnaBbs;

/**
 * BBS 인터페이스 매퍼(또는 매퍼 인터페이스)
 *  - 서비스 Layer와 매퍼XML을 연결시켜주는 역할
 */
@Mapper
public interface IQnaBbsMapperDao
{
	public List<QnaBbs> getQnaBbsList(QnaBbs vo);
	public QnaBbs getBbsByNo(int no);
	public void insertBbs(QnaBbs vo);
	public void updateBbs(QnaBbs vo);
	public void deleteBbs(int no);
}
