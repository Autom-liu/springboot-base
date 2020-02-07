package com.edu.scnu.common.enums;

/**
 * 系统默认错误码
 * @author Autom
 * @date 2020年2月4日
 * @version 1.0
 *
 */
public enum DefaultSysErrorEnum implements IErrorEnum {
	
	/** 请求参数格式暂不支持 **/
	PARAM_FORMAT_ERROR("0403", "请求参数格式暂不支持"),

	/** 接口不存在 **/
	INTERFACE_NOT_FOUND("0404", "接口不存在"),

	/** 请求方式暂不支持**/
	HTTP_METHOD_ERROR("0405", "请求方式暂不支持"),
	
	/** 自定义解析器无法解析该参数 **/
	PARAM_PARSE_ERROR("0406", "自定义解析器无法解析该参数"),
	
	;
	
	private String code;
	
	private String msg;

	private DefaultSysErrorEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public boolean isSuccess() {
		return false;
	}

	@Override
	public boolean isNotSuccess() {
		return true;
	}
	
}
