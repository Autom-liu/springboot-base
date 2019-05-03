package com.edu.scnu.user.enums;

import com.edu.scnu.common.enums.BaseOrderByEnum;

import lombok.Getter;

@Getter
public enum UserOrderField implements BaseOrderByEnum {
	
	CREATE_TIME(10001, "create_time"),
	;
	
	private UserOrderField(Integer code, String orderFiled) {
		this.code = code;
		this.orderFiled = orderFiled;
	}

	private Integer code;
	
	private String orderFiled;

}
