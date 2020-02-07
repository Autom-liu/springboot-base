package com.edu.scnu.common.enums;

/**
 * 异常类型枚举，一般用于定义错误码类枚举
 * 也就是说，错误码类枚举必须有code和msg字段，这是规范定义
 * @author Autom
 * @date 2020年2月4日
 * @version 1.0
 */
public interface IErrorEnum extends BaseStatusEnum {
	
	String DEFAULT_SUCCESS_CODE = "0000";
	
	boolean isSuccess();
	
	boolean isNotSuccess();
	
	/**
	 * 该方法可以用于创建不再本系统枚举范围内的错误码<br/>创建一个兼容可其他系统的错误码枚举
	 * @Title: getInstance
	 * @Description: 该方法可以用于创建不再本系统枚举范围内的错误码<br/>创建一个兼容可其他系统的错误码枚举
	 * @param code
	 * @param msg
	 * @return
	 */
	public static IErrorEnum getInstance(final String code, final String msg) {
		return getInstance(code, msg, DEFAULT_SUCCESS_CODE);
	}
	
	/**
	 * 该方法可以用于创建不再本系统枚举范围内的错误码<br/>创建一个兼容可其他系统的错误码枚举
	 * @Title: getInstance
	 * @Description: 该方法可以用于创建不再本系统枚举范围内的错误码<br/>创建一个兼容可其他系统的错误码枚举
	 * @param code
	 * @param msg
	 * @param successCode
	 * @return
	 */
	public static IErrorEnum getInstance(final String code, final String msg, final String successCode) {
		return new IErrorEnum() {

			@Override
			public String getCode() {
				return code;
			}

			@Override
			public String getMsg() {
				return msg;
			}

			@Override
			public boolean isSuccess() {
				return successCode.equals(code);
			}

			@Override
			public boolean isNotSuccess() {
				return !successCode.equals(code);
			}
		};
	}
}
