package com.edu.scnu.idnt.query;

import com.edu.scnu.common.query.PageQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=true)
public class UserRoleQuery extends PageQuery {

	private static final long serialVersionUID = 1L;

}
