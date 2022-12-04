package com.javalab.javaconfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.javaconfig.dao.IQnaBbsMapperDao;
import com.javalab.javaconfig.domain.QnaBbs;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaBbsServiceImpl implements IQnaBbsService
{
	@Autowired
	private IQnaBbsMapperDao mapperDao;

	@Override
	public List<QnaBbs> getQnaBbsList(QnaBbs vo) {
		List<QnaBbs> qnaBbsList = mapperDao.getQnaBbsList(vo);
		return qnaBbsList;
	}

	@Override
	public void insertBbs(QnaBbs vo) {
		this.mapperDao.insertBbs(vo);
	}

	@Override
	public QnaBbs getBbsByNo(int no) {
		QnaBbs vo = this.mapperDao.getBbsByNo(no); 
		return vo;
	}

	@Override
	public void updateBbs(QnaBbs vo) {
		this.mapperDao.updateBbs(vo);
		
	}

	@Override
	public void deleteBbs(int no) {
		this.mapperDao.deleteBbs(no);
		
	}


}
