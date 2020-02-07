package com.edu.scnu.common.exception;

import com.edu.scnu.common.enums.IErrorEnum;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private IErrorEnum exceptionEnum;

	public SystemException(IErrorEnum exceptionEnum) {
		super(exceptionEnum.getMsg());
		this.exceptionEnum = exceptionEnum;
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