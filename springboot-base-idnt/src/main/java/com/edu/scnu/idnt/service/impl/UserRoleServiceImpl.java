package com.edu.scnu.idnt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.scnu.common.proxy.CriteriaProxy;
import com.edu.scnu.common.service.CommonService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.idnt.bean.UserRole;
import com.edu.scnu.idnt.bean.UserRoleExample;
import com.edu.scnu.idnt.dto.UserRoleDTO;
import com.edu.scnu.idnt.mapper.UserRoleMapper;
import com.edu.scnu.idnt.query.UserRoleQuery;
import com.edu.scnu.idnt.service.UserRoleService;
import com.edu.scnu.idnt.vo.UserRoleVO;
import tk.mybatis.mapper.common.BaseMapper;

@Service
public class UserRoleServiceImpl extends CommonService<UserRole, UserRoleDTO, UserRoleVO> implements UserRoleService {

    private UserRoleMapper userRoleMapper;

	public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
		super(UserRole.class, UserRoleDTO.class, UserRoleVO.class, userRoleMapper);
		this.userRoleMapper = userRoleMapper;
	}

	@Override
	public PageVO<UserRoleVO> queryPage(UserRoleQuery query) {
		
		UserRoleExample example = new ExampleProxy();
		
		super.handlePageOrder(query, false, example);
		
		// TODO Here build the condition you want
		
		List<UserRole> resultList = userRoleMapper.selectByExample(example);
		
		return super.handlePageResult(resultList);
	}
	

	@Override
	public List<UserRoleVO> queryList(UserRoleQuery query) {
		query.setPageFlag(false);
		PageVO<UserRoleVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

	private class ExampleProxy extends UserRoleExample {
		// 静态代理一下
		public Criteria createCriteria() {
			return (Criteria) CriteriaProxy.getInstance(super.createCriteria());
		}
		
		public Criteria or() {
			return (Criteria) CriteriaProxy.getInstance(super.or());
		}
		
	}
}
