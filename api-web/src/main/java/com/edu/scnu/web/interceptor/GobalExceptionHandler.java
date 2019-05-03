package com.edu.scnu.web.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.alibaba.fastjson.JSON;
import com.edu.scnu.common.enums.ErrorCode;
import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.exception.ParamValidException;
import com.edu.scnu.common.utils.DataUtils;
import com.edu.scnu.common.vo.Result;


/**
 * 全局统一异常处理
 * 本类作为全局异常处理基类，如果有其它异常需要处理，请继承该类，尽量保持本类代码不动，提高代码可移植性。
 * @author Autom
 *
 */
@ControllerAdvice
public class GobalExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(GobalExceptionHandler.class);
	
	/**
	 * 参数校验异常
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Result<String> bindException(BindException e, HttpServletRequest request) {
		BindingResult bindingResult = e.getBindingResult();
		List<String> errorList = new ArrayList<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errorList.add(fieldError.getDefaultMessage());
		}
		logger.error(request.getRequestURL() + ": " + DataUtils.join(errorList, ","));
		logger.error("请求参数={}", JSON.toJSONString(request.getParameterMap()));
		return Result.error(ErrorCode.PARAM_INVALID.getCode(), errorList.get(0));
	}
	
	/**
	 * 普通参数校验异常处理
	 * @return
	 */
	@ExceptionHandler(ParamValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Result<String> paramValidException(ParamValidException e, HttpServletRequest request) {
		logger.error("参数校验异常: {}", e.getMessage());
		logger.error("请求url: {}", request.getRequestURL());
		logger.error("请求参数: {}", JSON.toJSONString(request.getParameterMap()));
		return Result.error(ErrorCode.PARAM_INVALID.getCode(), e.getMessage());
	}
	
	/**
	 * 请求接口不存在404异常返回
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public Result<String> InterfaceNotFound(NoHandlerFoundException e, HttpServletRequest request) {
		logger.error("请求不存在异常: {}", e.getMessage());
		logger.error("请求url: {}", request.getRequestURL());
		logger.error("请求参数: {}", JSON.toJSONString(request.getParameterMap()));
		return Result.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMsg());
	}
	
	/**
	 * 业务异常
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BizException.class)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody
	public Result<String> bizException(BizException e, HttpServletRequest request) {
		logger.error("业务异常: {}", e.getMessage());
		logger.error("请求url: {}", request.getRequestURL());
		logger.error("请求参数: {}", JSON.toJSONString(request.getParameterMap()));
		logger.error("异常具体信息: {}", DataUtils.printStackTrace(e));
		return Result.error(e.getCode(), e.getMessage());
	}
	
	/**
	 * 系统内部异常
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Result<String> bizException(Exception e, HttpServletRequest request) {
		logger.error("内部异常: {}", e.getMessage());
		logger.error("请求url: {}", request.getRequestURL());
		logger.error("请求参数: {}", JSON.toJSONString(request.getParameterMap()));
		logger.error("异常具体信息: {}", DataUtils.printStackTrace(e));
		return Result.error(ErrorCode.UNKNOWN_ERROR.getCode(), ErrorCode.UNKNOWN_ERROR.getMsg(), e.toString());
	}
}
