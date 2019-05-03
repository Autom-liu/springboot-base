package com.edu.scnu.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.edu.scnu.common.enums.ErrorCode;
import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.proxy.CriteriaProxy;
import com.edu.scnu.common.utils.DataUtils;
import com.edu.scnu.common.utils.KeyUtils;
import com.edu.scnu.common.vo.PageVo;
import com.edu.scnu.user.bean.User;
import com.edu.scnu.user.bean.UserExample;
import com.edu.scnu.user.bean.UserExample.Criteria;
import com.edu.scnu.user.dao.ext.UserDao;
import com.edu.scnu.user.dto.UserDTO;
import com.edu.scnu.user.enums.UserOrderField;
import com.edu.scnu.user.enums.UserState;
import com.edu.scnu.user.query.UserQuery;
import com.edu.scnu.user.service.UserService;
import com.edu.scnu.user.vo.UserVo;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends BaseService implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public String addUser(UserDTO userDTO) {
		UserVo userTest = getOne(userDTO.getBzId(), false);
		if (userTest != null) {
			throw new BizException(ErrorCode.USER_EXIST);
		}
		User user = DataUtils.copyBean(userDTO, User.class);
		String id = KeyUtils.randomString();
		user.setId(id);
		user.setIsLock(UserState.UNLOCK.getValue());
		user.setIsDel(UserState.UNDEL.getValue());
		userDao.insertSelective(user);
		return id;
	}
	
	/**
	 * 	不考虑直接使用sql批量插入，因为这样错误不好追踪
	 */
	@Override
	public void batchAddOrUpdate(List<? extends UserDTO> userDTOList) {
		List<User> userList = DataUtils.copyList(userDTOList, User.class);
		for(User user : userList) {
			user.setId(KeyUtils.randomString());
			userDao.saveOrUpdate(user);
		}
	}

	@Override
	public int updateUser(UserDTO userDTO) {
		User user = DataUtils.copyBean(userDTO, User.class);
		return updateUser(user);
	}
	
	@CacheEvict(key = "user.id")
	private int updateUser(User user) {
		return userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public int editPassword(String userId, String originPassword, String newPassword) {
		UserVo userTest = getOneAlive(userId);
		if (userTest == null) {
			throw new BizException(ErrorCode.USER_NOT_EXIST);
		}
		
		if(!userTest.getPassword().equals(originPassword)) {
			throw new BizException(ErrorCode.PASSWORD_NOCORRECT);
		}
		
		User user = new User();
		user.setId(userTest.getId());
		user.setPassword(newPassword);
		return updateUser(user);
	}

	@Override
	public int lockUser(String userId) {
		User user = new User();
		user.setId(userId);
		user.setIsLock(UserState.LOCK.getValue());
		return updateUser(user);
	}
	
	public int unlockUser(String userId) {
		User user = new User();
		user.setId(userId);
		user.setIsLock(UserState.UNLOCK.getValue());
		return updateUser(user);
	}

	@Override
	public int deleteUser(String userId) {
		User user = new User();
		user.setId(userId);
		user.setIsDel(UserState.DEL.getValue());
		return updateUser(user);
	}

	@Override
	public List<UserVo> getAll() {
		List<User> userList = userDao.selectByExample(null);
		List<UserVo> result = DataUtils.copyList(userList, UserVo.class);
		return result;
	}

	@Override
	public PageVo<UserVo> getUserAlive(UserQuery query) {
		UserExample userExample = new UserExample();
		
		// 进行分页和排序相关处理
		super.handlePageOrder(query, UserOrderField.class);
		
		if (query.getOrderByFiled() != null) {
			userExample.setOrderByClause(query.getOrderByFiled());
		}
		
		Criteria criteria = (Criteria) CriteriaProxy.getInstance(userExample.createCriteria());
		
		criteria.andBzIdEqualTo(query.getBzId())
			.andUsernameEqualTo(query.getUsername())
			.andNicknameEqualTo(query.getNickname())
			.andIsDelEqualTo(UserState.UNDEL.getValue())
			.andCreateTimeBetween(query.getStartTime(), query.getEndTime());

		List<User> userList = userDao.selectByExample(userExample);
		
		PageInfo<User> userPageList = new PageInfo<User>(userList);
		
		List<UserVo> result = DataUtils.copyList(userPageList.getList(), UserVo.class);
		
		return new PageVo<>(userPageList, result);
	}
	
	@Cacheable(key = "#id", condition = "#id != null", unless = "#result != null")
	public UserVo getOne(String id) {
		User user = userDao.selectByPrimaryKey(id);
		return DataUtils.copyBean(user, UserVo.class);
	}
	

	@Override
	@Cacheable(key = "#id", condition = "#id != null", unless = "#result != null")
	public UserVo getOneAlive(String id) {
		User user = userDao.selectByPrimaryKey(id);
		if(user == null || user.getIsDel()) {
			return null;
		}
		return DataUtils.copyBean(user, UserVo.class);
	}

	@Override
	@Cacheable(key = "#bzId", condition = "#bzId != null", unless = "#result != null")
	public UserVo getOne(String bzId, boolean isDel) {
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andBzIdEqualTo(bzId).andIsDelEqualTo(UserState.DEL.getValue());
		
		List<User> user = userDao.selectByExample(userExample);
		
		return CollectionUtils.isEmpty(user) ? null : DataUtils.copyBean(user.get(0), UserVo.class);
	}

}
