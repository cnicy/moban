package com.gehua.moban.dao.test;

import com.gehua.moban.dao.UserMapper;
import com.gehua.moban.model.entity.uc.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rono on 2015/11/9.
 */
public class MapperTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-datasource.xml");
        UserMapper userMapper = (UserMapper) ctx.getBean("userMapper");
        andIn(userMapper);
    }

    static void andIn(UserMapper userMapper){
        Example example = new Example(User.class);
        example.createCriteria().andIn("id", Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}));

        List<User> list =userMapper.selectByExample(example);
        System.out.println(list.size());
    }

    static void page(UserMapper userMapper){
        PageHelper.startPage(0,10);
        PageInfo page = new PageInfo(userMapper.selectAll());
        System.out.println(page);
    }
    static void insertList(UserMapper userMapper){
        User user = new User();
        user.setName("name1");

        User user1 = new User();
        user1.setName("name1");

        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user1);

        userMapper.insertList(list);
    }
}
