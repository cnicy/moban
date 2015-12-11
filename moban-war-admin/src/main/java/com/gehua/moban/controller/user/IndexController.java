package com.gehua.moban.controller.user;

import com.gehua.moban.common.http.HttpMethod;
import com.gehua.moban.common.http.HttpRequest;
import com.gehua.moban.common.http.HttpRequestBean;
import com.gehua.moban.common.http.HttpResponseBean;
import com.gehua.moban.common.utils.CommonUtil;
import com.gehua.moban.common.weixin.SignUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by rono on 2015/11/20.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("login")
    public String login(HttpServletRequest req,Model model){
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/portal/main.jsp";
        }
        final String exception = (String) req.getAttribute("shiroLoginFailure");
        String errorMsg = "";
        final String userName = req.getParameter("username");
        if(CommonUtil.isNotEmpty(exception)){
            if (UnknownAccountException.class.getName().equals(exception)
                    || IncorrectCredentialsException.class.getName().equals(exception)) {
                errorMsg = "用户名或者密码错误，请重新输入！";
            }  else if(LockedAccountException.class.getName().equals(exception)){
                errorMsg = "用户已被锁定";
            }else{
                errorMsg = "账户异常，请联系管理员！";
            }
        }
        String kickMsg = null;
        try{
            kickMsg = req.getSession().getAttribute("kickOutMsg").toString();
        }catch(Exception e){
            // TODO
        }
        if(kickMsg!=null){
            errorMsg = kickMsg;
            req.getSession().setAttribute("kickOutMsg",null);
        }
        model.addAttribute("error", errorMsg);
        model.addAttribute("userName", userName);
        return "login";
    }

    @RequestMapping("auth2")
    @RequiresPermissions("user:create")
    public String auth2(){
        HttpRequest http = new HttpRequest();
        HttpRequestBean req = new HttpRequestBean();
        req.setUri("");
        req.setMethod(HttpMethod.POST);
        req.setParam("{\"component_appid\":\"x45a08cc791eb84e9\"}");
        try {
            HttpResponseBean res = http.execute(req);
            String  str = res.getResponse();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "auth2";
    }

    @RequestMapping("cas")
    public void cas(HttpServletRequest request, HttpServletResponse response){
        String ticket = request.getParameter("ticket");

        System.out.println("cas"+ticket);
    }

    /**
     * 接受系统消息
     * @param request
     * @param response
     */
    @RequestMapping("sysMsg")
    public void sysMsg(HttpServletRequest request, HttpServletResponse response){
        System.out.println("sysMsg");
    }
    /**
     * 接受事件消息
     * @param request
     * @param response
     */
    @RequestMapping("eventMsg")
    public void eventMsg(HttpServletRequest request, HttpServletResponse response,@PathVariable("appId")String appId){
        System.out.println("eventMsg");
    }
    @RequestMapping("/index")
    public void index(HttpServletRequest request, HttpServletResponse response){
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        if(CommonUtil.isEmpty(signature,timestamp,nonce,echostr))
            return ;

        try {
            PrintWriter out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
            out.close();
            out = null;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
    }
}
