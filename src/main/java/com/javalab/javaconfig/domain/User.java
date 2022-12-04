package com.javalab.javaconfig.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private int user_no;   
	private String user_id;   
	private String user_pw;
	private String user_name; 
	private String email;     
	private String phone;     
	private String fax;      
	private String zip_num;      
	private String address1;      
	private String address2;      
	private String dept_id;   
	private String role_id;   
	private String image_path;
	private int enabled;   
	private Date regdate;   
	
	
}
