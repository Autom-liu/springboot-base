package com.edu.scnu.baseinfo.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.scnu.baseinfo.dto.AccountDTO;
import com.edu.scnu.baseinfo.query.AccountQuery;
import com.edu.scnu.baseinfo.service.AccountService;
import com.edu.scnu.baseinfo.vo.AccountVO;
import com.edu.scnu.common.vo.IResult;
import com.edu.scnu.common.vo.Result;
import com.edu.scnu.common.vo.ResultList;
import com.edu.scnu.common.web.annotation.CommonRequestBody;
import com.edu.scnu.common.web.annotation.PermissionRequire;

@RestController
public class AccountApi {
	
	@Autowired
	private AccountService accountService;

	@PermissionRequire
	@PostMapping("/account/add")
	public IResult addAccount(@Valid @CommonRequestBody AccountDTO dto) {
		// TODO 必传字段校验
		accountService.insert(dto);
		return Result.success("");
	}
	
	@DeleteMapping("/account/{id}")
	public IResult deleteAccount(@PathVariable("id") Integer id) {
		accountService.deleteById(id);
		return Result.success("");
	}
	
	@GetMapping("/account/{id}")
	public IResult findOne(@PathVariable("id") Integer id) {
		AccountVO accountVO = accountService.findOne(id);
		return Result.success(accountVO);
	}
	
	@GetMapping("/account/list")
	public IResult findList(AccountQuery query) {
		List<AccountVO> result = accountService.queryList(query);
		return ResultList.success(result);
	}
	
}
