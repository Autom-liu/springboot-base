package com.edu.scnu.common.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResultList<T> extends Result<List<T>> {

	private static final long serialVersionUID = 7515902808012245770L;

	public static<T> ResultList<T> success(List<T> data) {
		ResultList<T> result = new ResultList<>();
		result.setCode(200);
		result.setMsg("success");
		result.setData(data);
		return result;
	}
}
