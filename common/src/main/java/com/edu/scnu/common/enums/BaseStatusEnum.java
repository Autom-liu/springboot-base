package com.edu.scnu.common.enums;

/**
 * 	用于定义数据状态枚举常量
 * @author Autom
 *
 * @param <T>
 * @see user模块 com.edu.scnu.user.enums.UserState
 */
public interface BaseStatusEnum<T> {
	T getCode();
}
