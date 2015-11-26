package com.gehua.moban.controller.user;

import com.gehua.moban.common.cons.Constants;
import com.gehua.moban.common.exception.BizException;
import com.gehua.moban.common.utils.CommonUtil;
import com.gehua.moban.model.entity.core.Page;
import com.gehua.moban.model.entity.uc.User;
import com.gehua.moban.service.uc.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试controller
 */
@Controller
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("userPage")
    public String userPage(){
        return "user";
    }

    @RequestMapping("page")
    public  @ResponseBody
    Page<User> page(Page<User> page){
        try {
            page =  userService.searchUserPage(page);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return page;
    }

    @RequestMapping("add")
    public @ResponseBody ResponseEntity<String> add(User user) {
        if(null==user) return new ResponseEntity<String>(Constants.EX_PARAM, HttpStatus.BAD_REQUEST);

        try {
            userService.createUser(user);
            return new ResponseEntity<String>(Constants.PASS_OK,HttpStatus.OK);
        } catch(BizException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new ResponseEntity<String>(Constants.EX_APP,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("edit")
    public @ResponseBody
    ResponseEntity<String> edit(User user){
        if(null==user || CommonUtil.isEmpty(user.getId()))
            return new ResponseEntity<String>(Constants.EX_PARAM, HttpStatus.BAD_REQUEST);

        try {
            userService.updateUser(user);
            return new ResponseEntity<String>(Constants.PASS_OK,HttpStatus.OK);
        } catch(BizException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new ResponseEntity<String>(Constants.EX_APP,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping("getUserById")
    public @ResponseBody ResponseEntity<Object> getUserById(Long id) {
        if (null == id)
            return new ResponseEntity<Object>(Constants.EX_PARAM, HttpStatus.BAD_REQUEST);

        try {
            User user = this.userService.searchUserById(id);
            if (null != user) {
                return new ResponseEntity<Object>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<Object>(Constants.EX_APP, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("delete")
    public @ResponseBody
    ResponseEntity<String> delete(@RequestParam("ids[]") List<Long> ids){
        if(CommonUtil.isEmpty(ids))
            return new ResponseEntity<String>(Constants.EX_PARAM,HttpStatus.BAD_REQUEST);

        try {
            this.userService.deleteUser(ids);
            return new ResponseEntity<String>(Constants.PASS_OK,HttpStatus.OK);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<String>(Constants.EX_APP,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @RequestMapping("list")
    public List<User> list(){
        User user = new User();
        user.setId(1L);
        user.setName("fk");

        User user1 = new User();
        user1.setId(1L);

        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user1);

        return list;
    }
}
