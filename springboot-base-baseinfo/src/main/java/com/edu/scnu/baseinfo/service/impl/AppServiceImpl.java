package com.edu.scnu.baseinfo.service.impl;

import java.util.List;

import com.edu.scnu.common.enums.ErrorEnum;
import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.query.QueryBuilder;
import com.edu.scnu.common.util.ConverterUtils;
import com.edu.scnu.common.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.scnu.common.service.BaseService;
import com.edu.scnu.baseinfo.bean.App;
import com.edu.scnu.baseinfo.dto.AppDTO;
import com.edu.scnu.baseinfo.mapper.AppMapper;
import com.edu.scnu.baseinfo.query.AppQuery;
import com.edu.scnu.baseinfo.service.AppService;
import com.edu.scnu.baseinfo.vo.AppVO;
import org.springframework.util.CollectionUtils;

@Service
public class AppServiceImpl extends BaseService<App, AppDTO, AppVO> implements AppService {

	@Autowired
    private AppMapper appMapper;


	@Override
	public PageVO<AppVO> queryPage(AppQuery query) throws BizException {
		QueryBuilder<App> queryBuilder = new QueryBuilder<>(App.class);
		queryBuilder.select(App::getId, App::getSimpleName, App::getFullName, App::getCreateTime).createCriteria()
				.andEqualTo(App::getId, query.getId())
				.andEqualTo(App::getSimpleName, query.getSimpleName())
				.andLikeTrim(App::getFullName, query.getFullName())
				.andBetween(App::getCreateTime, query.getStartDate(), query.getEndDate())
				.build().orderby(App::getCreateTime, true);
		return super.queryPage(query, queryBuilder);
	}

	@Override
	public List<AppVO> queryList(AppQuery query) {
		QueryBuilder<App> queryBuilder = new QueryBuilder<>(App.class);
		queryBuilder.select(App::getId, App::getSimpleName, App::getFullName, App::getCreateTime).createCriteria()
				.andEqualTo(App::getId, query.getId())
				.andEqualTo(App::getSimpleName, query.getSimpleName())
				.andLikeTrim(App::getFullName, query.getFullName())
				.andBetween(App::getCreateTime, query.getStartDate(), query.getEndDate())
				.build().orderby(App::getCreateTime, true);

		List<App> appList = appMapper.selectByExample(queryBuilder);
		return ConverterUtils.copyList(appList, AppVO.class);
	}

	@Override
	public AppVO queryOneBySimpleName(String simpleName) throws BizException {
		QueryBuilder<App> queryBuilder = new QueryBuilder<>(App.class);
		queryBuilder.createCriteria().andEqualTo(App::getSimpleName, simpleName).build();
		List<App> appList = appMapper.selectByExample(queryBuilder);
		if (CollectionUtils.isEmpty(appList)) {
			throw new BizException(ErrorEnum.ERRCODE_0404);
		}
		return ConverterUtils.copyBean(appList.get(0), AppVO.class);
	}
}
