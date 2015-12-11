<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../core/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>欢迎登录后台管理系统</title>
  <script src="${ctx}/js/jquery-1.11.3.min.js" type="text/javascript" charset="UTF-8"></script>
  <script src="${ctx}/js/allin.js" type="text/javascript" charset="UTF-8"></script>
  <script src="${ctx}/js/cloud.js" type="text/javascript"></script>
  <script language="javascript" type="text/javascript">
    $(function(){
      $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
      $(window).resize(function(){
        $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
      })
    });
  </script>

</head>

<body class="login">

<div id="mainBody">
  <div id="cloud1" class="cloud"></div>
  <div id="cloud2" class="cloud"></div>
</div>
<div class="logintop">
  <span>欢迎登录后台管理界面平台</span>
  <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
  </ul>
</div>

<div class="loginbody">
  <span class="systemlogo"></span>
  <div class="loginbox loginbox3">
    <form action="" method="post">
      <ul>
        <li><input name="username" type="text" class="loginuser" value="admin" onclick="JavaScript:this.value=''"/></li>
        <li><input name="password" type="text" class="loginpwd" value="密码" onclick="JavaScript:this.value=''"/></li>
        <li class="yzm">
          <span><input name="" type="text" value="验证码" onclick="JavaScript:this.value=''"/></span><cite>X3D5S</cite>
        </li>
        <li>
          <input name="" type="submit" class="loginbtn" value="登录"  onclick="javascript:window.location='main.html'"  />
          <label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label>
        </li>
      </ul>
    </form>
  </div>
</div>
<div class="loginbm">版权所有  2014  <a href="http://www.uimaker.com">uimaker.com</a>  仅供学习交流，勿用于任何商业用途</div>
</body>
</html>
