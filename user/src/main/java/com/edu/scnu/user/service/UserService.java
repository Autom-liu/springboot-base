package com.edu.scnu.user.service;


import java.util.List;

import com.edu.scnu.common.vo.PageVo;
import com.edu.scnu.user.dto.UserDTO;
import com.edu.scnu.user.query.UserQuery;
import com.edu.scnu.user.vo.UserVo;

public interface UserService {
	
	/**
	 * 添加用户信息
	 * @param user
	 * @return id if insert success
	 */
	String addUser(UserDTO user);
	
	/**
	 * 	批量新增，如存在则更新数据
	 * @param user
	 */
	void batchAddOrUpdate(List<? extends UserDTO> userList);
	
	/**
	 * 根据主键id，更新用户信息
	 * @param user
	 * @return
	 */
	int updateUser(UserDTO user);
	
	/**
	 * 使用旧密码修改密码
	 * @param userId  主键id
	 * @param originPassword 旧密码
	 * @param newPassword 新密码
	 * @return 
	 */
	int editPassword(String userId, String originPassword, String newPassword);
	
	/**
	 * 锁定用户
	 * @param userId 主键id
	 * @return
	 */
	int lockUser(String userId);
	
	/**
	 * 解锁用户
	 * @param userId 主键id
	 * @return
	 */
	int unlockUser(String userId);
	
	/**
	 * 删除用户，标记删除法
	 * @param userId 主键id
	 * @return
	 */
	int deleteUser(String userId);
	
	/**
	 * 无脑获取所有用户信息
	 * @return
	 */
	List<UserVo> getAll();
	
	/**
	 * 根据主键id 获取单个用户信息
	 * @param id
	 * @return
	 */
	UserVo getOne(String id);
	
	/**
	 * 根据主键id 获取单个未被删除的用户信息，
	 * @param id 主键id
	 * @return <code>null</code> if is_del = 0 or id not exist
	 */
	UserVo getOneAlive(String id);
	
	/**
	 * 根据业务id 获取单个用户信息
	 * @param bzId 业务id
	 * @param isDel 是否有删除标记
	 * @return
	 */
	UserVo getOne(String bzId, boolean isDel);
	
	/**
	 * 根据条件，可分页的获取未被删除的用户列表
	 * @param query
	 * @return
	 */
	PageVo<UserVo> getUserAlive(UserQuery query);

}
