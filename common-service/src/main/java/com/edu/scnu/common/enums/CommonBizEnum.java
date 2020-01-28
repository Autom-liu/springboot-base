package com.edu.scnu.common.enums;

public enum CommonBizEnum implements BizEnum {
	/** 无效的排序字段  **/
	BAD_ORDER_FIELD(400000, "无效的排序字段"),
	;
	
	private Integer code;
	
	private String msg;

	private CommonBizEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}
