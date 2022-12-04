package com.javalab.javaconfig.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString	//(exclude={"제외할 값"})
public class Client {

	private int client_id;  		
	private String client_name;	
	private String address;    	
	private String postal_code;	
	private String phone;      	
	private String fax;        	
	private String email;			
	private char is_active;			
	
}
