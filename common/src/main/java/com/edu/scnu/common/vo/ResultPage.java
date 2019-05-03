package com.edu.scnu.common.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 	公共Vo对象之，分页查询API格式返回结果Vo对象
 * 	其相当于 Result<PageVo<T>>
 * @author Administrator
 *
 * @param <T>
 */
@Data
public class ResultPage<T> implements Serializable {

	private static final long serialVersionUID = 7270644349247497885L;

	private Integer code;
	
	private String msg;
	
	private PageVo<T> data;
	
	public static<T> ResultPage<T> success(PageVo<T> data) {
		ResultPage<T> result = new ResultPage<T>();
		result.setCode(0);
		result.setMsg("success");
		result.setData(data);
		return result;
	}
	
	public static ResultPage<String> error(Integer code, String message) {
		ResultPage<String> result = new ResultPage<String>();
		result.setCode(code);
		result.setMsg(message);
		result.setData(null);
		return result;
	}
}
