package com.edu.scnu.common.enums;

public enum ErrorCode {
	
	NOT_FOUND(404, "接口不存在"),
	NOT_SUPPORT(405, "接口请求方式暂不支持"),
	PARAM_INVALID(400, "参数校验异常"),
	
	/** 错误的排序字段码  **/
	BadOrderByField(400, "错误的排序字段码"),
	
	/** 该编号用户已存在 **/
	USER_EXIST(10001, "该编号用户已存在"),
	/** 该编号用户不存在 **/
	USER_NOT_EXIST(10002, "该编号用户不存在"),
	/** 密码不正确 **/
	PASSWORD_NOCORRECT(10003, "密码不正确"),
	
	
	UNKNOWN_ERROR(-9999, "系统内部错误！"),
	;
	
	private Integer code;
	
	private String msg;
	
	private ErrorCode(Integer code, String msg) {
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
