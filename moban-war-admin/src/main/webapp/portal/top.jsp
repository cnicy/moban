<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../core/taglibs.jsp"%>
<%
  response.setHeader("P3P","CP=CAO PSA OUR");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>后台管理</title>
  <script src="../js/jquery-1.11.3.min.js" type="text/javascript" charset="UTF-8"></script>
  <script src="../js/allin.js" type="text/javascript" charset="utf-8"></script>
</head>

<body class="topbg">

<div class="topleft">
  <a href="main.html" target="_parent"><img src="../images/blank.gif" class="logo" title="系统首页" /></a>
</div>
<ul class="nav">
  <li><a href="default.html" target="rightFrame" class="selected"><img src="../images/blue/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
  <li><a href="imgtable.html" target="rightFrame"><img src="../images/blue/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li>
  <li><a href="imglist.html"  target="rightFrame"><img src="../images/blue/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li>
  <li><a href="tools.html"  target="rightFrame"><img src="../images/blue/icon04.png" title="常用工具" /><h2>常用工具</h2></a></li>
  <li><a href="computer.html" target="rightFrame"><img src="../images/blue/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li>
  <li><a href="tab.html"  target="rightFrame"><img src="../images/blue/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
</ul>
<div class="topright">
  <ul>
    <li onclick="blue();"><a href="#">蓝色</a></li>
    <li onclick="orange();"><a href="#">橙色</a></li>
    <li><span><img id="helpimg" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    <li><a href="login.html" target="_parent">退出</a></li>
  </ul>
  <div class="user">
    <span>admin</span>
    <i>消息</i>
    <b>5</b>
  </div>
</div>
<script type="text/javascript">
  $(function(){
    //顶部导航切换
    $(".nav li a").click(function(){
      $(".nav li a.selected").removeClass("selected")
      $(this).addClass("selected");
    });
    $("#helpimg").attr('src','../images/'+getTheme()+'/help.png');
  })
  // 改变颜色
  function orange(){
    setCookie('themeName','style_orange',300);
    parent.refrensh();
  }
  // 蓝色
  function blue(){
    setCookie('themeName','style_blue',300);
    parent.refrensh();
  }
</script>
</body>
</html>
