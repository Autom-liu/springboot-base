package com.edu.scnu.common.enums;

/**
 * 	用于定义排序字段的枚举常量
 * @author Autom
 * @see user模块的 com.edu.scnu.user.enums.UserOrderField
 */
public interface BaseOrderByEnum extends BaseStatusEnum<Integer> {
	
	String getOrderFiled();
	
}
