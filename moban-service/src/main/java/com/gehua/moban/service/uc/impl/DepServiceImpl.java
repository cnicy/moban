package com.gehua.moban.service.uc.impl;

import com.gehua.moban.dao.DepMapper;
import com.gehua.moban.model.entity.uc.Dep;
import com.gehua.moban.mybatis.MyBatisBaseMapper;
import com.gehua.moban.service.core.BaseServiceImpl;
import com.gehua.moban.service.uc.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rono on 2015/11/16.
 */
@Service
public class DepServiceImpl extends BaseServiceImpl<Dep,Integer> implements DepService {
    @Autowired
    private DepMapper depMapper;
    @Override
    public MyBatisBaseMapper<Dep> getBaseMapper() {
        return depMapper;
    }

}
