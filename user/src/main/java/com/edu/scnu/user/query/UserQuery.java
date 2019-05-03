package com.edu.scnu.user.query;

import java.io.Serializable;
import java.util.Date;

import com.edu.scnu.user.query.common.PageQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserQuery extends PageQuery implements Serializable {
	
	private static final long serialVersionUID = 4558023917885767653L;

	private String bzId;
	
	private String username;
	
	private String nickname;
	
	private Integer isLock;
	
	private Date startTime;
	
	private Date endTime;
	
}
