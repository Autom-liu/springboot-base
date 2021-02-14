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

	Boolean isExist(Integer id) throws BizException;

	Boolean isExist(String id) throws BizException;
	
	VO findOne(Integer id) throws BizException;

	VO findOne(String id) throws BizException;

	int countByIds(List<Object> ids) throws BizException;

	int countByIds(String[] ids) throws BizException;

	int countByIds(Integer[] ids) throws BizException;
	
	List<VO> findByIds(List<Object> ids) throws BizException;

	List<VO> findByIds(Integer[] ids) throws BizException;

	List<VO> findByIds(String[] ids) throws BizException;
	
	int insert(DTO dto) throws BizException;
	
	int deleteById(Integer id) throws BizException;

	int deleteById(String id) throws BizException;
	
	int deleteByIds(List<Object> ids) throws BizException;

	int deleteByIds(String[] ids) throws BizException;

	int deleteByIds(Integer[] ids) throws BizException;
	
	int updateByIdSelective(DTO dto) throws BizException;
	
}
