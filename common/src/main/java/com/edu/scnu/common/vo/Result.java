package com.edu.scnu.common.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 	公共Vo对象之，通用API格式返回结果Vo对象
 * @author Administrator
 *
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {
	
	private static final long serialVersionUID = 1568487577131526477L;

	private Integer code;
	
	private String msg;
	
	private T data;
	
	public static<T> Result<T> success(T data) {
		Result<T> result = new Result<T>();
		result.setCode(0);
		result.setMsg("success");
		result.setData(data);
		return result;
	}
	
	public static<T> Result<T> error(Integer code, String message) {
		Result<T> result = new Result<T>();
		result.setCode(code);
		result.setMsg(message);
		result.setData(null);
		return result;
	}
	
	public static Result<String> error(Integer code, String message, String hint) {
		Result<String> result = new Result<String>();
		result.setCode(code);
		result.setMsg(message);
		result.setData(hint);
		return result;
	}
}
