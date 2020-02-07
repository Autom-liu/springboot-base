package com.edu.scnu.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 	公共VO对象之，分页查询API格式返回结果VO对象
 * 	其相当于 Result<PageVO<T>>
 * @author Administrator
 *
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ResultPage<T> extends Result<PageVO<T>> {

	private static final long serialVersionUID = 7270644349247497885L;
	
	public static<T> ResultPage<T> success(PageVO<T> data) {
		ResultPage<T> result = new ResultPage<>();
		result.setCode(200);
		result.setMsg("success");
		result.setData(data);
		return result;
	}
}