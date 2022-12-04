package com.javalab.javaconfig.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString//(exclude={"product_name"})
public class InvoiceHeader {

	private Integer invoice_id;      	
	private Integer client_id;       		
	private Date invoice_date;       		
	private String shipping_address;	
	private double total_amt;       	
	private String description;  
	// 자바 객체 -> json type으로 변환 될 때 yyyy-mm-dd 형태로 바뀌도록 설정
	// 이 설정 없으면 정수 형태로 jsp로 전송됨
	@JsonFormat(pattern = "yyyy-MM-dd")	
	private Date created_date;   
	// 자바 객체 -> json type으로 변환 될 때 yyyy-mm-dd 형태로 바뀌도록 설정
	// 이 설정 없으면 정수 형태로 jsp로 전송됨
	@JsonFormat(pattern = "yyyy-MM-dd")	
	private Date modified_date;	
	
	private List<InvoiceDetail> invoiceDetails;
	
}
