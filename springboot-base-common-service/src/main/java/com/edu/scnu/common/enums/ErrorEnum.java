package com.edu.scnu.common.enums;

public enum ErrorEnum implements IErrorEnum {

	
	ERRCODE_0000("0000", "success"),
	
	
	;

	private String code;
	
	private String msg;
	
	private ErrorEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMsg() {
		return this.msg;
	}

	@Override
	public boolean isSuccess() {
		return this == ERRCODE_0000;
	}

	@Override
	public boolean isNotSuccess() {
		return this != ERRCODE_0000;
	}
	
}
