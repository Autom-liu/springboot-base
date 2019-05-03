package com.edu.scnu.user.enums;

import com.edu.scnu.common.enums.BaseStatusEnum;

import lombok.Getter;

@Getter
public enum UserState implements BaseStatusEnum<Integer> {
	
	LOCK(1, true, "冻结"),
	UNLOCK(0, false, "正常"),
	DEL(1, true, "删除"),
	UNDEL(0, false, "未删除"),
	;
	


	private UserState(Integer code, Boolean value, String desc) {
		this.code = code;
		this.value = value;
		this.desc = desc;
	}

	private Integer code;
	
	private Boolean value;
	
	private String desc;
	

}
