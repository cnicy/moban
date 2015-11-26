package com.gehua.moban.service.core;

import com.gehua.moban.model.entity.core.Page;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends Object,PK extends Serializable> {
	/**
	 * 添加
	 * @param t
	 * @return
	 */
	Integer create(T t) throws Exception;
	/**
	 * 修改
	 * @param t
	 */
	void update(T t)throws Exception;
	/**
	 * 根据主键查询
	 * @param pk
	 * @return
	 */
	T searchById(PK pk)throws Exception;
	/**
	 * 分页查询
	 * @return
	 */
	Page<T> searchByPage(Page<T> page)throws Exception;
	/**
	 * 删除
	 * @throws Exception
	 */
	void delete(List<Integer> ids,Class<T> clazz) throws Exception;
	/**
	 * 批量增加
	 * @param e
	 * @throws Exception
	 */
	void batchInsert(List<T> e) throws Exception;
}