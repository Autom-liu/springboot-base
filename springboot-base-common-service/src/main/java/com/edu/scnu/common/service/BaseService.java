package  com.edu.scnu.common.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.edu.scnu.common.base.BaseMapper;
import com.edu.scnu.common.enums.ErrorEnum;
import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.query.QueryBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import  com.edu.scnu.common.query.PageQuery;
import  com.edu.scnu.common.util.ConverterUtils;
import  com.edu.scnu.common.vo.PageVO;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * 通用服务接口，它可以快速为我们完成增删改查（单个）的服务开发<br/>
 * 通过继承，即可实现单表查询零SQL开发
 * @author Autom
 *
 * @param <T> 实体类型
 * @param <DTO> DTO类型
 * @param <VO> VO类型
 */
public abstract class BaseService<T, DTO, VO> implements IService<T, DTO, VO> {
	
	@Autowired
	protected BaseMapper<T> mapper;
	
	private Class<T> beanClass;
	
	// private Class<DTO> dtoClass;
	
	private Class<VO> voClass;
	
	@SuppressWarnings("unchecked")
	protected BaseService() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.beanClass = (Class<T>) pt.getActualTypeArguments()[0];
		// this.dtoClass = (Class<DTO>) pt.getActualTypeArguments()[1];
		this.voClass = (Class<VO>) pt.getActualTypeArguments()[2];
	}
	
	public List<VO> findAll() {
		List<T> all = mapper.selectAll();
		return ConverterUtils.copyList(all, voClass);
	}
	
	public List<VO> findByIds(List<Integer> ids) {
		List<T> result = mapper.selectByIdList(ids);
		
		return ConverterUtils.copyList(result, this.voClass);
	}
	
	public VO findOne(Integer id) {
		T bean = mapper.selectByPrimaryKey(id);
		VO result = ConverterUtils.copyBean(bean, this.voClass);
		return result;
	}
	
	public VO findOne(String id) {
		T bean = mapper.selectByPrimaryKey(id);
		VO result = ConverterUtils.copyBean(bean, this.voClass);
		return result;
	}
	
	public int insert(DTO dto) {
		T bean = ConverterUtils.copyBean(dto, this.beanClass);
		return mapper.insertSelective(bean);
	}
	
	public int deleteById(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public int deleteByIds(List<Integer> idList) {
		String ids = StringUtils.join(idList, ',');
		return mapper.deleteByIds(ids);
	}
	
	public int updateByIdSelective(DTO dto) {
		T bean = ConverterUtils.copyBean(dto, beanClass);
		return mapper.updateByPrimaryKeySelective(bean);
	}

	/**
	 * 条件分页查询
	 * @param pageQuery
	 * @param queryBuilder
	 * @return
	 * @throws BizException 分页参数不正确
	 */
	protected final PageVO<VO> queryPage(PageQuery pageQuery, QueryBuilder<T> queryBuilder) throws BizException {
		return queryPage(pageQuery, queryBuilder, (r, t) -> {});
	}

	/**
	 * 条件分页查询
	 * @param pageQuery
	 * @param queryBuilder
	 * @param convertAction
	 * @return
	 * @throws BizException 分页参数不正确
	 */
	protected final PageVO<VO> queryPage(PageQuery pageQuery, QueryBuilder<T> queryBuilder, ConverterUtils.Executor<T, VO> convertAction) throws BizException {

		long total = mapper.countByExample(queryBuilder);
		Integer currentPage = pageQuery.getCurrentPage();
		Integer pageSize = pageQuery.getPageSize();

		if (currentPage == null || pageSize == null) {
			throw new BizException(ErrorEnum.ERRCODE_0001);
		}

		int beginNum = (currentPage - 1) * pageSize;
		queryBuilder.limit(beginNum, pageSize);
		List<T> ts = mapper.selectByExample(queryBuilder);
		List<VO> voList = ConverterUtils.copyList(ts, voClass, convertAction);
		queryBuilder.clearLimit();
		Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize);
		PageImpl<VO> pageComputed = new PageImpl<>(voList, pageRequest, total);
		return new PageVO<>(pageComputed);
	}

}
