package com.edu.scnu.idnt.service;

import java.util.List;

import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.service.IService;
import com.edu.scnu.common.vo.IResult;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.idnt.bean.User;
import com.edu.scnu.idnt.dto.UserDTO;
import com.edu.scnu.idnt.query.UserQuery;
import com.edu.scnu.idnt.vo.UserVO;

/**
 * if your service don't need this method  you can remove it manually
 */
public interface UserService extends IService<User, UserDTO, UserVO> {
	
	PageVO<UserVO> queryPage(UserQuery query);
	
	List<UserVO> queryList(UserQuery query);

	/**
	 * 新建用户，返回新建成功的用户名
	 * @param userDTO
	 * @return
	 */
	String createUser(UserDTO userDTO) throws BizException;

	/**
	 * 修改用户密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	IResult changePassword(String userId, String oldPassword, String newPassword);

	/**
	 * 重置密码
	 * @param userId
	 * @return
	 */
	IResult resetPassword(String userId);

	/**
	 * 为用户分配机构和角色
	 * @param userId
	 * @param deptId
	 * @param roleId
	 * @param isDefault
	 * @return
	 */
	IResult assignDeptRole(String userId, String deptId, String roleId, Boolean isDefault) throws BizException;
}
