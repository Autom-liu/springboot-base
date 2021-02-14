package com.edu.scnu.idnt.service.impl;

import java.util.List;

import com.edu.scnu.common.util.ConverterUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.scnu.common.proxy.CriteriaProxy;
import com.edu.scnu.common.service.CommonService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.idnt.bean.Role;
import com.edu.scnu.idnt.bean.RoleExample;
import com.edu.scnu.idnt.dto.RoleDTO;
import com.edu.scnu.idnt.mapper.RoleMapper;
import com.edu.scnu.idnt.query.RoleQuery;
import com.edu.scnu.idnt.service.RoleService;
import com.edu.scnu.idnt.vo.RoleVO;
import tk.mybatis.mapper.common.BaseMapper;

@Service
public class RoleServiceImpl extends CommonService<Role, RoleDTO, RoleVO> implements RoleService {


    private RoleMapper roleMapper;

	public RoleServiceImpl(RoleMapper roleMapper) {
		super(Role.class, RoleDTO.class, RoleVO.class, roleMapper);
		this.roleMapper = roleMapper;
	}

	@Override
	public PageVO<RoleVO> queryPage(RoleQuery query) {
		
		RoleExample example = new ExampleProxy();
		
		super.handlePageOrder(query, false, example);
		
		// TODO Here build the condition you want
		
		List<Role> resultList = roleMapper.selectByExample(example);
		
		return super.handlePageResult(resultList);
	}
	

	@Override
	public List<RoleVO> queryList(RoleQuery query) {
		query.setPageFlag(false);
		PageVO<RoleVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

	@Override
	public String createRole(RoleDTO roleDTO) {
		Role role = ConverterUtils.copyBean(roleDTO, Role.class);
		String roleId = RandomStringUtils.randomNumeric(6);
		role.setRoleId(roleId);
		role.setStatus(0);
		roleMapper.insertSelective(role);
		return roleId;
	}

	private class ExampleProxy extends RoleExample {
		// 静态代理一下
		public Criteria createCriteria() {
			return (Criteria) CriteriaProxy.getInstance(super.createCriteria());
		}
		
		public Criteria or() {
			return (Criteria) CriteriaProxy.getInstance(super.or());
		}
		
	}
}
