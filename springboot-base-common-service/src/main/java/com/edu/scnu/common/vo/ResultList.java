package com.edu.scnu.common.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 列表数据返回类型
 * @param <T>
 * @author Autom
 * @date 2020年2月7日
 * @version 1.0
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ResultList<T> extends Result<List<T>> {

	private static final long serialVersionUID = 1L;

	public static<T> ResultList<T> success(List<T> data) {
		ResultList<T> result = new ResultList<>();
		result.setCode("0000");
		result.setMsg("success");
		result.setData(data);
		return result;
	}
}
