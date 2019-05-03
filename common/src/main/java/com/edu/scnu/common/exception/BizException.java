package com.edu.scnu.common.exception;

import java.io.Serializable;

import com.edu.scnu.common.enums.ErrorCode;

/**
 * 业务异常类
 * @author Autom
 *
 */
public class BizException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -5900746749005212141L;
	
	private Integer code;

	public BizException(ErrorCode errorCode) {
		super(errorCode.getMsg());
		this.code = errorCode.getCode();
	}

	public Integer getCode() {
		return code;
	}
}
