package com.edu.scnu.common.enums;

/**
 * 枚举基础类，所有枚举类型均实现该接口，也就是说所有枚举类型均有一个code字段，这是一个规范定义
 * @author Autom
 * @date 2020年2月4日
 * @version 1.0
 */
public interface BaseStatusEnum {
	
	String getCode();
	
	String getMsg();
	
}
