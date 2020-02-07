package com.edu.scnu.common.vo;

import com.edu.scnu.common.enums.IErrorEnum;

/**
 * 全局统一通用返回接口
 * @author Autom
 * @date 2020年2月7日
 * @version 1.0
 */
public interface IResult {
	
	String getCode();
	
	String getMsg();
	
	void setCode(String code);
	
	void setMsg(String msg);
	
	void setT(Throwable t);
	
	Throwable getT();
	
	public static IResult error(String code, String msg) {
		IResult result = new Result<>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	public static IResult error(String code, String msg, Throwable t) {
		IResult result = new Result<>();
		result.setCode(code);
		result.setMsg(msg);
		result.setT(t);
		return result;
	}
	
	public static IResult error(IErrorEnum errorEnum) {
		IResult result = new Result<>();
		result.setCode(errorEnum.getCode());
		result.setMsg(errorEnum.getMsg());
		return result;
	}
	
	public static IResult error(IErrorEnum errorEnum, Throwable t) {
		IResult result = new Result<>();
		result.setCode(errorEnum.getCode());
		result.setMsg(errorEnum.getMsg());
		result.setT(t);
		return result;
	}
}
