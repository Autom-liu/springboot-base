package com.edu.scnu.common.exception;

import com.edu.scnu.common.enums.IErrorEnum;

/**
 * 统一业务异常
 * @author Autom
 * @date 2020年2月4日
 * @version 1.0
 *
 */
public class BizException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private IErrorEnum exceptionEnum;

	private String otherInfo;

	public BizException(IErrorEnum exceptionEnum) {
		super(exceptionEnum.getMsg());
		this.exceptionEnum = exceptionEnum;
	}

	public BizException(IErrorEnum exceptionEnum, String otherInfo) {
		super(exceptionEnum.getMsg() + otherInfo);
		this.exceptionEnum = exceptionEnum;
		this.otherInfo = otherInfo;
	}

	public IErrorEnum getExceptionEnum() {
		return exceptionEnum;
	}
	
	public String getCode() {
		return exceptionEnum.getCode();
	}
	
	public String getMsg() {
		return exceptionEnum.getMsg();
	}
}
