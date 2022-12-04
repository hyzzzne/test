package com.javalab.javaconfig.service;


import java.util.List;

import com.javalab.javaconfig.domain.QnaBbs;


public interface IQnaBbsService
{
	public List<QnaBbs> getQnaBbsList(QnaBbs vo);
	public QnaBbs getBbsByNo(int no);
	public void insertBbs(QnaBbs vo);
	public void updateBbs(QnaBbs vo);
	public void deleteBbs(int no);

}
