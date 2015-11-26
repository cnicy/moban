package com.gehua.moban.service.uc;

import com.gehua.moban.common.exception.BizException;
import com.gehua.moban.model.entity.core.Page;
import com.gehua.moban.model.entity.uc.User;

import java.util.List;

/**
 * Created by rono on 2015/11/11.
 */
public interface UserService {
    /**
     * 获取用户分页数据信息
     * @param page
     * @return
     */
    Page<User> searchUserPage(Page<User> page)throws BizException;

    void createUser(User user)throws BizException;

    void updateUser(User user)throws BizException;

    void deleteUser(List<Long> ids)throws BizException;

    User searchUserById(Long id)throws BizException;
}
