package com.edu.scnu.common.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.edu.scnu.common.enums.DefaultSysErrorEnum;
import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.exception.SystemException;
import com.edu.scnu.common.util.JsonUtils;
import com.edu.scnu.common.vo.IResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 * @author Autom
 * @date 2020年2月4日
 * @version 1.0
 *
 */
@ControllerAdvice
@Slf4j
public class GobalExceptionHandler {
	
	/**
	 * 参数校验异常
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public IResult bindException(BindException e, HttpServletRequest request) {
		BindingResult bindingResult = e.getBindingResult();
		List<String> errorList = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
		if(log.isWarnEnabled()) {
			log.warn(request.getRequestURL() + e.getMessage());
			log.warn("请求参数={}", request.getParameterMap());
		}
		return IResult.error(HttpStatus.BAD_REQUEST.toString(), errorList.get(0), e);
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
	public IResult interfaceNotFound(NoHandlerFoundException e, HttpServletRequest request) {
		if(log.isErrorEnabled()) {
			log.error("请求不存在异常: {}", e.getMessage());
			log.error("请求url: {}", request.getRequestURL());
			log.error("请求参数: {}", JsonUtils.serialize(request.getParameterMap()));			
		}
		return IResult.error(DefaultSysErrorEnum.INTERFACE_NOT_FOUND, e);
	}
	
	/**
	 * 业务异常
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BizException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public IResult bizException(BizException e, HttpServletRequest request) {
		if(log.isWarnEnabled()) {
			log.error("业务异常: {}", e.getMessage());
			log.error("请求url: {}", request.getRequestURL());
			log.error("请求参数: {}", request.getParameterMap());
		}
		return IResult.error(e.getCode(), e.getMsg());
	}
	
	/**
	 * 系统业务异常
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(SystemException.class)
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ResponseBody
	public IResult systemException(SystemException e, HttpServletRequest request) {
		if(log.isWarnEnabled()) {
			log.error("业务异常: {}", e.getMessage());
			log.error("请求url: {}", request.getRequestURL());
			log.error("请求参数: {}", request.getParameterMap());
			log.error("异常具体信息: ", e);
		}
		return IResult.error(e.getCode(), e.getMsg());
	}
	
	/**
	 * 内部异常
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public IResult internalException(Exception e, HttpServletRequest request) {
		if(log.isErrorEnabled()) {
			log.error("内部异常: {}", e.getMessage());
			log.error("请求url: {}", request.getRequestURL());
			log.error("请求参数: {}", request.getParameterMap());
			log.error("异常具体信息: ", e);
		}
		return IResult.error(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "系统内部异常");
	}
	
}
