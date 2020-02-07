package com.edu.scnu.common.vo;

public interface IResult<T> {
	
	Integer getCode();
	
	String getMsg();
	
	void setCode(Integer code);
	
	void setMsg(String msg);
	
	void setData(T data);
	
	void setT(Throwable t);
	
	Throwable getT();
	
	T getData();
	
	public static<T> IResult<T> error(Integer code, String msg) {
		IResult<T> result = new Result<>();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(null);
		return result;
	}
	
	public static<T> IResult<T> error(Integer code, String msg, Throwable t) {
		IResult<T> result = new Result<>();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(null);
		result.setT(t);
		return result;
	}
}
