package com.edu.scnu.common.exception;

import java.io.Serializable;

import com.edu.scnu.common.enums.ErrorCode;

/**
 * 参数校验异常类
 * @author Autom
 *
 */
public class ParamValidException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 4988160802888290521L;
	private Integer code;

	public ParamValidException(String msg) {
		super(msg);
		this.code = ErrorCode.PARAM_INVALID.getCode();
	}

	public Integer getCode() {
		return code;
	}
}
