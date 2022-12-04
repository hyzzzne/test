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
public class AddressVo {
	private String zip_num;
	private String sido;
	private String gugun;
	private String dong;
	private String zip_code;
	private String bunji;

}

