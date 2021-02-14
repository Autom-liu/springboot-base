package com.edu.scnu.baseinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.scnu.common.proxy.CriteriaProxy;
import com.edu.scnu.common.service.CommonService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.baseinfo.bean.Account;
import com.edu.scnu.baseinfo.bean.AccountExample;
import com.edu.scnu.baseinfo.dto.AccountDTO;
import com.edu.scnu.baseinfo.mapper.AccountMapper;
import com.edu.scnu.baseinfo.query.AccountQuery;
import com.edu.scnu.baseinfo.service.AccountService;
import com.edu.scnu.baseinfo.vo.AccountVO;
import tk.mybatis.mapper.common.BaseMapper;

@Service
public class AccountServiceImpl extends CommonService<Account, AccountDTO, AccountVO> implements AccountService {

	private AccountMapper accountMapper;

	public AccountServiceImpl(AccountMapper accountMapper) {
		super(Account.class, AccountDTO.class, AccountVO.class, accountMapper);
		this.accountMapper = accountMapper;
	}

	@Override
	public PageVO<AccountVO> queryPage(AccountQuery query) {
		
		AccountExample example = new ExampleProxy();
		
		super.handlePageOrder(query, false, example);
		
		// TODO Here build the condition you want
		
		List<Account> accounts = accountMapper.selectByExample(example);
		
		return super.handlePageResult(accounts);
	}
	

	@Override
	public List<AccountVO> queryList(AccountQuery query) {
		query.setPageFlag(false);
		PageVO<AccountVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

	private class ExampleProxy extends AccountExample {
		// 静态代理一下
		public Criteria createCriteria() {
			return (Criteria) CriteriaProxy.getInstance(super.createCriteria());
		}
		
		public Criteria or() {
			return (Criteria) CriteriaProxy.getInstance(super.or());
		}
		
	}
}
