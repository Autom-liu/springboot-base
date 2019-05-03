package com.edu.scnu.test.user;

import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu.scnu.common.utils.KeyUtils;
import com.edu.scnu.test.BaseTest;
import com.edu.scnu.test.dto.UserDTO;
import com.edu.scnu.user.service.UserService;

public class UserTest extends BaseTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void userSave() {
		UserDTO user = new UserDTO();
		
		user.setId(KeyUtils.randomString());
		user.setBzId("SN003");
		user.setUsername("wangsan");
		user.setPassword(Md5Crypt.md5Crypt("123456".getBytes()));
		user.setNickname("nickname");
		
		userService.addUser(user);
	}
	
}
