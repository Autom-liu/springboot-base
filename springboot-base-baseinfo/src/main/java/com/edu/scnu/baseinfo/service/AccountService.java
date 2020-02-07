package com.edu.scnu.baseinfo.service;

import java.util.List;

import com.edu.scnu.common.service.IService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.baseinfo.bean.Account;
import com.edu.scnu.baseinfo.dto.AccountDTO;
import com.edu.scnu.baseinfo.query.AccountQuery;
import com.edu.scnu.baseinfo.vo.AccountVO;

/**
 * if your service don't need this method  you can remove it manually
 */
public interface AccountService extends IService<Account, AccountDTO, AccountVO> {
	
	PageVO<AccountVO> queryPage(AccountQuery query);
	
	List<AccountVO> queryList(AccountQuery query);
	
}
