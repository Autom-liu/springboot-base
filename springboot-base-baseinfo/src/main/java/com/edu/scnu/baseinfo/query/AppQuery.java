package com.edu.scnu.baseinfo.query;

import com.edu.scnu.common.query.PageQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=true)
public class AppQuery extends PageQuery {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String simpleName;

	private String fullName;

	private String description;

	private String manager;

	private String managerMobile;

	private String domain;

	private String company;

	private Date startDate;

	private Date endDate;

}
