package com.gehua.moban.service.core;

import com.gehua.moban.common.exception.BizException;
import com.gehua.moban.model.entity.core.Page;
import com.gehua.moban.mybatis.MyBatisBaseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.dao.DataAccessException;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T extends Object, PK extends Serializable> implements BaseService<T, PK>  {
    public abstract MyBatisBaseMapper<T> getBaseMapper();

    @Override
    public Integer create(T t) throws Exception {
        return getBaseMapper().insert(t);
    }

    @Override
    public void update(T t) throws BizException, DataAccessException {
        getBaseMapper().updateByPrimaryKeySelective(t);
    }

    @Override
    public T searchById(PK pk) throws BizException, DataAccessException {
        return getBaseMapper().selectByPrimaryKey(pk);
    }

    @Override
    public Page<T> searchByPage(Page<T> page) throws BizException, DataAccessException {
        PageHelper.orderBy(" id desc");
        PageHelper.startPage(page.getPageNumber(),page.getPageSize());

        PageInfo pageInfo = new PageInfo(getBaseMapper().selectAll());
        page.setRows(pageInfo.getList());
        page.setTotal(pageInfo.getTotal());
        return page;
    }
    @Override
    public void delete(List<Integer> ids,Class<T> clazz) throws BizException, DataAccessException {
        Example example = new Example(clazz);
        example.createCriteria().andIn("id",ids);
        getBaseMapper().deleteByExample(example);
    }

    @Override
    public void batchInsert(List<T> e) throws BizException, DataAccessException {
        getBaseMapper().insertList(e);
    }
}
