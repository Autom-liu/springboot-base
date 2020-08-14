package com.edu.scnu.baseinfo.service;

import java.util.List;

import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.service.IService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.baseinfo.bean.App;
import com.edu.scnu.baseinfo.dto.AppDTO;
import com.edu.scnu.baseinfo.query.AppQuery;
import com.edu.scnu.baseinfo.vo.AppVO;

/**
 * if your service don't need this method  you can remove it manually
 */
public interface AppService extends IService<App, AppDTO, AppVO> {
	
	PageVO<AppVO> queryPage(AppQuery query) throws BizException;
	
	List<AppVO> queryList(AppQuery query) throws BizException;

	AppVO queryOneBySimpleName(String simpleName) throws BizException;
	
}
