package com.edu.scnu.common.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 基础单条数据返回类型
 * @param <T>
 * @author Autom
 * @date 2020年2月7日
 * @version 1.0
 */
@Data
public class Result<T> implements Serializable, IResult {
	
	private static final long serialVersionUID = 1L;

	private String code;
	
	private String msg;
	
	private T data;
	@JsonIgnore
	private Throwable t;
	
	public static<T> Result<T> success(T data) {
		Result<T> result = new Result<>();
		result.setCode("0000");
		result.setMsg("success");
		result.setData(data);
		return result;
	}
	
}
