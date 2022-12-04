package com.javalab.javaconfig.domain;
import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/****************************************************************
 * 
 * This is a Qna Bbs
 * Created by magicoh
 * 2020.11.27
 *   
 ****************************************************************/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaBbs{

	private int no;			
	private String title;
	private String content;        	
	private String writer;  	
	private int hit;	   	
	// 자바 객체 -> json type으로 변환 될 때 yyyy-mm-dd 형태로 바뀌도록 설정
	// 이 설정 없으면 정수 형태로 jsp로 전송됨
	@JsonFormat(pattern = "yyyy-MM-dd")	
	private Date regdate;	
	
}
