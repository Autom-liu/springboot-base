package com.edu.scnu.web.api;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edu.scnu.common.vo.PageVo;
import com.edu.scnu.common.vo.Result;
import com.edu.scnu.common.vo.ResultPage;
import com.edu.scnu.user.query.UserQuery;
import com.edu.scnu.user.service.UserService;
import com.edu.scnu.user.vo.UploadVo;
import com.edu.scnu.user.vo.UserVo;
import com.edu.scnu.web.common.utils.ExcelUtil;
import com.edu.scnu.web.common.utils.FileUtil;
import com.edu.scnu.web.dto.UserDTO;

@RestController
@RequestMapping("/user")
public class UserAPI extends BaseAPI {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/detail")
	public Result<UserVo> detail(@NotBlank(message = "id不能为空") String id) {
		UserVo data = userService.getOneAlive(id);
		return Result.success(data);
	}
	
	@GetMapping(value = "/export")
	public void export(HttpServletResponse response) throws Exception {
		List<UserVo> all = userService.getAll();
		String[] rowName = new String[] {"用户编号", "用户名", "昵称", "是否锁定"};
		String[] fieldNames = new String[] {"bzId", "username", "nickname", "isLock"};
		List<Object[]> dataList = ExcelUtil.bean2ObjList(all, fieldNames);
		ExcelUtil.export("用户信息表", rowName, dataList, response);
	}
	
	@PostMapping(value = "/import", headers = "content-type=multipart/form-data")
	public Result<String> importExcel(MultipartFile excelFile) throws Exception {
		String[] fieldNames = new String[] {"bzId", "username", "nickname", "isLock"};
		List<UserDTO> userList = ExcelUtil.importExcel(excelFile, fieldNames, UserDTO.class);
		userService.batchAddOrUpdate(userList);
		return Result.success("success");
	}
	
	@PostMapping(value = "/add", headers = "content-type=multipart/form-data")
	public Result<UserVo> add(@Validated(value = {UserDTO.addUserAPI.class}) UserDTO userDTO) {
		UploadVo uploadFile = FileUtil.upload(userDTO.getAvatarFile(), "user");
		if (uploadFile.getIsSuccess()) {
			userDTO.setAvatar(uploadFile.getUploadPath());			
		} else {
			return Result.error(500, uploadFile.getMsg());
		}
		String id = userService.addUser(userDTO);
		UserVo result = new UserVo();
		result.setId(id);
		return Result.success(result);
	}
	
	@GetMapping("/list")
	public ResultPage<UserVo> list(UserQuery query) {
		PageVo<UserVo> userPage = userService.getUserAlive(query);
		return ResultPage.success(userPage);
	}
	
	@PostMapping("/edit/password")
	public Result<String> editPassword(
				@NotBlank(message = "id不能为空") String userId,
				@NotBlank(message = "旧密码不能为空") String originPassword,
				@NotBlank(message = "新密码密码不能为空") String newPassword) {
		
		userService.editPassword(userId, originPassword, newPassword);
		return Result.success("success");
	}
	
	@PostMapping("lock")
	public Result<String> lock(@NotBlank(message = "id不能为空") String userId, @NotNull(message = "标记不能为空") Boolean lockFlag) {
		if (lockFlag) {
			userService.lockUser(userId);
		} else {
			userService.unlockUser(userId);
		}
		return Result.success("success");
	}
	
	@PostMapping("delete")
	public Result<String> delete(@NotBlank(message = "id不能为空") String userId) {
		userService.deleteUser(userId);
		return Result.success("success");
	}

}
