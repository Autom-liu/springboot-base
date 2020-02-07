package com.edu.scnu.common.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.edu.scnu.common.enums.NotFoundEnum;
import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.exception.NotFoundException;
import com.edu.scnu.common.util.JsonUtils;
import com.edu.scnu.common.vo.IResult;

import lombok.extern.slf4j.Slf4j;

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
	public IResult<String> bindException(BindException e, HttpServletRequest request) {
		BindingResult bindingResult = e.getBindingResult();
		List<String> errorList = bindingResult.getFieldErrors().stream().map((fieldError) -> {
			return fieldError.getDefaultMessage();
		}).collect(Collectors.toList());
		
		log.error(request.getRequestURL() + e.getMessage());
		log.error("请求参数={}", JsonUtils.serialize(request.getParameterMap()));
		return IResult.error(HttpStatus.BAD_REQUEST.value(), errorList.get(0), e);
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
	public IResult<String> InterfaceNotFound(NoHandlerFoundException e, HttpServletRequest request) {
		log.error("请求不存在异常: {}", e.getMessage());
		log.error("请求url: {}", request.getRequestURL());
		log.error("请求参数: {}", JsonUtils.serialize(request.getParameterMap()));
		return IResult.error(HttpStatus.NOT_FOUND.value(), NotFoundEnum.INTERFACE_NOT_FOUND, e);
	}
	
	/**
	 * 业务异常之没有数据
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public IResult<String> notFoundException(NotFoundException e, HttpServletRequest request) {
		log.error("业务异常: {}", e.getMessage());
		log.error("请求url: {}", request.getRequestURL());
		log.error("请求参数: {}", JsonUtils.serialize(request.getParameterMap()));
		return IResult.error(e.getCode(), e.getMsg(), e);
	}
	
	/**
	 * 业务异常
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BizException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public IResult<String> bizException(BizException e, HttpServletRequest request) {
		log.error("业务异常: {}", e.getMessage());
		log.error("请求url: {}", request.getRequestURL());
		log.error("请求参数: {}", JsonUtils.serialize(request.getParameterMap()));
		return IResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e);
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
	public IResult<String> bizException(Exception e, HttpServletRequest request) {
		log.error("内部异常: {}", e.getMessage());
		log.error("请求url: {}", request.getRequestURL());
		log.error("请求参数: {}", JsonUtils.serialize(request.getParameterMap()));
		log.error("异常具体信息: {}", printStackTrace(e));
		return IResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统内部异常");
	}
	
	/**
	 * 输出异常堆栈信息
	 * @param t
	 * @return
	 */
	private String printStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
	    t.printStackTrace(new PrintWriter(sw, true));
	    return sw.getBuffer().toString();
	}
	
}
