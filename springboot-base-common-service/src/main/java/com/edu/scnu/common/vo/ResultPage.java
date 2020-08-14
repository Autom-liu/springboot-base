package com.edu.scnu.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公共VO对象之，分页查询API格式返回结果类型
 * @param <T>
 * @author Autom
 * @date 2020年2月7日
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ResultPage<T> extends Result<PageVO<T>> {

	private static final long serialVersionUID = 7270644349247497885L;
	
	public static<T> ResultPage<T> success(PageVO<T> data) {
		ResultPage<T> result = new ResultPage<>();
		result.setCode("0000");
		result.setMsg("success");
		result.setData(data);
		return result;
	}
}
