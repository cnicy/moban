package com.gehua.moban.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by rono on 2015/11/3.
 * MyBatisBaseMapper 作为所有Mapper的公用mapper
 * 注意：当前这个Mapper不能被扫描到，不然会报错！
 */
public interface MyBatisBaseMapper<T> extends Mapper<T>,MySqlMapper<T> {

}