package com.edu.scnu.common.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.edu.scnu.common.enums.BaseStatusEnum;
import com.edu.scnu.common.enums.IErrorEnum;
import com.edu.scnu.common.exception.SystemException;

public class ConverterUtils {
	
	/**
	 * 类似BeanUtils的copyProperties功能，类型转换器
	 * 对BeanUtils的copyProperties功能进行二次封装
	 * @param source
	 * @param clazz
	 * @return
	 */
	public static<R, T> T copyBean(R source, Class<T> clazz) {
		if(source == null) 
			return null;
		try {
			T target= clazz.newInstance();
			BeanUtils.copyProperties(source, target);
			return target;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new SystemException(IErrorEnum.getInstance("", ""), e);
		}
	}
	
	/**
	 * 列表数据对象转换工具
	 * @param sources 源列表数据
	 * @param clazz   目标列表数据类型
	 * @return  目标列表数据
	 */
	public static<R, T> List<T> copyList(List<R> sources, Class<T> clazz) {
		List<T> targetList = sources.stream().map((source) -> {
			return copyBean(source, clazz);
		}).collect(Collectors.toList());
		return targetList;
	}
	
	/**
	 * 列表数据对象转换工具，并支持lamba表达式调用
	 * @param sources 源列表数据
	 * @param clazz   目标列表数据类型
	 * @return  目标列表数据
	 */
	public static<R, T> List<T> copyList(List<R> sources, Class<T> clazz, Executor<? super R, ? super T> action) {
		List<T> targetList = sources.stream().map((source) -> {
			T targetBean = copyBean(source, clazz);
			action.runAction(source, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		return targetList;
	}
	
	/**
	 * 根据code获取枚举，翻译字段方法
	 * @param code
	 * @param codeEnum
	 * @return
	 */
	public static<T extends BaseStatusEnum> T getEnumByCode(String code, Class<T> codeEnum) {
		if (code != null) {
			for (T each : codeEnum.getEnumConstants()) {
				if(code.equals(each.getCode())) {
					return each;
				}
			}
		}
		return null;
	}
	
	public interface Executor<R, T> {
		
		void runAction(R r, T t);
	}
}
