package com.edu.scnu.common.service;

import java.util.List;

import com.edu.scnu.common.exception.BizException;

/**
 * 公共接口的服务
 * @author Autom
 *
 * @param <T> BO 实体类
 * @param <DTO> DTO
 * @param <VO> VO
 */
public interface IService<T, DTO, VO> {
	
	List<VO> findAll() throws BizException;
	
	VO findOne(Integer id) throws BizException;

	VO findOne(String id) throws BizException;

	List<VO> findByIds(Integer[] idList) throws BizException;

	List<VO> findByIds(String[] idList) throws BizException;

	int countByIds(Integer[] idList) throws BizException;

	int countByIds(String[] idList) throws BizException;
	
	int insert(DTO dto) throws BizException;
	
	int deleteById(Integer id) throws BizException;
	
	int deleteByIds(Integer[] idList) throws BizException;

	int deleteByIds(String[] idList) throws BizException;
	
	int updateByIdSelective(DTO dto) throws BizException;
	
}
