package com.javalab.javaconfig.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString	//(exclude={"제외할 값"})
public class Category {

	private int category_id;	
	private String category_name;
	private String description;
	private int is_active;		
	
}
