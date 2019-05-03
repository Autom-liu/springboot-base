package com.edu.scnu.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.edu.scnu.common.enums.BaseStatusEnum;


/**
 * 数据处理工具类
 * @author Autom
 *
 */
public class DataUtils extends BeanUtils {
	
	private static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
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
			throw new RuntimeException("DataUtils.copyBean 出错");
		}
	}
	
	/**
	 * 列表数据对象转换工具
	 * @param sources 源列表数据
	 * @param clazz   目标列表数据类型
	 * @return  目标列表数据
	 */
	public static<R, T> List<T> copyList(List<R> sources, Class<T> clazz) {
		List<T> targetList = new ArrayList<>();
		try {
			for(R source : sources) {
				T target = clazz.newInstance();
				BeanUtils.copyProperties(source, target);
				targetList.add(target);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("DataUtils.copyList 出错");
		}
		return targetList;
	}
	
	/**
	 * 列表数据对象转换工具，并支持lamba表达式调用
	 * @param sources 源列表数据
	 * @param clazz   目标列表数据类型
	 * @return  目标列表数据
	 */
	public static<R, T> List<T> copyList(List<R> sources, Class<T> clazz, Executor<? super R, ? super T> action) {
		List<T> targetList = new ArrayList<>();
		try {
			for(R source : sources) {
				T target = clazz.newInstance();
				BeanUtils.copyProperties(source, target);
				action.runAction(source, target);
				targetList.add(target);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("DataUtils.copyList 出错");
		}
		return targetList;
	}
	
	/**
	 * 字符串列表转字符串分割工具
	 */
	public static String join(List<String> strList, String seperator) {
		StringBuilder sb=new StringBuilder();
	   for(String item : strList){
	         sb.append(item + seperator);
	   }
	   return sb.substring(0, sb.length() - seperator.length());
	}
	
	/**
	 * 输出异常堆栈信息
	 */
	public static String printStackTrace(Throwable t) {
	    StringWriter sw = new StringWriter();
	    t.printStackTrace(new PrintWriter(sw, true));
	    return sw.getBuffer().substring(0, 1000).toString();
	}
	
	/**
	 * 根据code获取枚举，翻译字段方法
	 * @param code
	 * @param codeEnum
	 * @return
	 */
	public static<T extends BaseStatusEnum<P>, P> T getEnumByCode(P code, Class<T> codeEnum) {
		if (code != null) {
			for (T each : codeEnum.getEnumConstants()) {
				if(code.equals(each.getCode())) {
					return each;
				}
			}
		}
		return null;
	}
	
	/**
	 * Date 时间日期转字符串工具
	 */
	public static String date2Str(Date date) {
		return sdf.format(date);
	}

}

interface Executor<R, T> {
	
	void runAction(R r, T t);
}
