package com.edu.scnu.idnt.service.impl;

import java.util.List;

import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.util.ConverterUtils;
import com.edu.scnu.common.vo.IResult;
import com.edu.scnu.common.vo.Result;
import com.edu.scnu.idnt.bean.UserRole;
import com.edu.scnu.idnt.mapper.UserRoleMapper;
import com.edu.scnu.idnt.service.DepartmentService;
import com.edu.scnu.idnt.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.edu.scnu.common.proxy.CriteriaProxy;
import com.edu.scnu.common.service.CommonService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.idnt.bean.User;
import com.edu.scnu.idnt.bean.UserExample;
import com.edu.scnu.idnt.dto.UserDTO;
import com.edu.scnu.idnt.mapper.UserMapper;
import com.edu.scnu.idnt.query.UserQuery;
import com.edu.scnu.idnt.service.UserService;
import com.edu.scnu.idnt.vo.UserVO;

@Service
@Slf4j
public class UserServiceImpl extends CommonService<User, UserDTO, UserVO> implements UserService {

    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RoleService roleService;

	public UserServiceImpl(UserMapper userMapper) {
		super(User.class, UserDTO.class, UserVO.class, userMapper);
		this.userMapper = userMapper;
	}

	@Override
	public PageVO<UserVO> queryPage(UserQuery query) {
		
		UserExample example = new ExampleProxy();
		
		super.handlePageOrder(query, false, example);
		
		// TODO Here build the condition you want
		
		List<User> resultList = userMapper.selectByExample(example);
		
		return super.handlePageResult(resultList);
	}
	

	@Override
	public List<UserVO> queryList(UserQuery query) {
		query.setPageFlag(false);
		PageVO<UserVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

	@Override
	public String createUser(UserDTO userDTO) throws BizException {
		userDTO.setStatus(1);
		User user = ConverterUtils.copyBean(userDTO, User.class);
		String userId = RandomStringUtils.randomNumeric(8);
		String password = RandomStringUtils.randomAlphabetic(8);
		user.setUserId(userId);
		user.setPassword(password);
		userMapper.insertSelective(user);
		if (StringUtils.isNotEmpty(userDTO.getDeptId()) || StringUtils.isNotEmpty(userDTO.getRoleId())) {
			this.assignDeptRole(userId, userDTO.getDeptId(), userDTO.getRoleId(), true);
		}


		return userId;
	}

	@Override
	public IResult changePassword(String userId, String oldPassword, String newPassword) {
		User user = new User();
		user.setUserId(userId);
		User validation = userMapper.selectOne(user);
		if (!validation.getPassword().equals(oldPassword)) {
			return IResult.error("10001", "旧密码不正确");
		}
		user.setPassword(newPassword);
		user.setStatus(0);
		userMapper.updateByPrimaryKeySelective(user);
		return Result.success("修改成功");
	}

	@Override
	public IResult resetPassword(String userId) {
		String password = RandomStringUtils.randomAlphabetic(8);
		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		user.setStatus(1);
		userMapper.updateByPrimaryKeySelective(user);
		return Result.success("重置成功");
	}

	@Override
	public IResult assignDeptRole(String userId, String deptId, String roleId, Boolean isDefault) throws BizException {
		UserRole userRole = new UserRole();
		// TODO 校验存在
		if (!this.isExist(userId)) {
			return IResult.error("10002", "用户信息不存在");
		}

		if (StringUtils.isNotEmpty(deptId) && !departmentService.isExist(deptId)) {
			return IResult.error("10003", "机构信息不存在");
		}

		if (StringUtils.isNotEmpty(roleId) && !roleService.isExist(roleId)) {
			return IResult.error("10004", "角色信息不存在");
		}

		userRole.setUserId(userId);
		userRole.setDeptId(StringUtils.defaultString(deptId, "0"));
		userRole.setRoleId(StringUtils.defaultString(roleId, "0"));
		userRole.setIsDefault(isDefault);
		userRole.setStatus(0);

		try {
			userRoleMapper.insertSelective(userRole);
		} catch (DuplicateKeyException e) {
			log.warn("userRoleMapper.insertSelective 插入失败： {} - {}", userRole, e.getMessage());
			return IResult.error("10005", "存在用户关系");
		}
		return Result.success("success");
	}

	private class ExampleProxy extends UserExample {
		// 静态代理一下
		public Criteria createCriteria() {
			return (Criteria) CriteriaProxy.getInstance(super.createCriteria());
		}
		
		public Criteria or() {
			return (Criteria) CriteriaProxy.getInstance(super.or());
		}
		
	}
}
