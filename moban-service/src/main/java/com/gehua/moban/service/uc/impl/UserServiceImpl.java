package com.gehua.moban.service.uc.impl;

import com.gehua.moban.common.exception.BizException;
import com.gehua.moban.dao.UserMapper;
import com.gehua.moban.model.entity.core.Page;
import com.gehua.moban.model.entity.uc.User;
import com.gehua.moban.service.uc.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by rono on 2015/11/11.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> searchUserPage(Page<User> page) {
        PageHelper.orderBy(" id desc");
        PageHelper.startPage(page.getPageNumber(),page.getPageSize());

        PageInfo pageInfo = new PageInfo(userMapper.selectAll());
        page.setRows(pageInfo.getList());
        page.setTotal(pageInfo.getTotal());
        return page;
    }

    @Override
    public void createUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteUser(List<Long> ids) throws BizException {
        Example example = new Example(User.class);
        example.createCriteria().andIn("id",ids);
        userMapper.deleteByExample(example);
    }

    @Override
    public User searchUserById(Long id) throws BizException {
        return userMapper.selectByPrimaryKey(id);
    }
}
