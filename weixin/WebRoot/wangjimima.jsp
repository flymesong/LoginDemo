<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width"/>
	<meta name="viewport" content="initial-scale=1.0,user-scalable=no"/>
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    
    <title>约泊停车</title>
    <link rel="stylesheet" type="text/css" href="css/wangjimima.css">
	<script src="js/jquery-3.1.0.min.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="layer/layer.js"></script>
	<script src="js/wangjimima.js"></script>
  </head>
  
  <body>
  <form name="loginform" id="loginform" action="" method="get" style="margin-top:55px">
  <center>
  <!------------内容一项开始------------->
  <input type="hidden" id="msg" value="">
<div class="gongwuyuan"><input name="phone" id="phone" class="shoujihao" type="tel" onkeyup="this.value=this.value.replace(/\D/g, '')" placeholder="手机号" /></div>
<div style="clear:both;"></div>
  <!---------------------内容一项结束------------------------->  
   
  <!------------内容一项开始------------->
   
   <input id="ma" name="ma"  type="hidden" value=""/>
    <div class="gongwuyuan">
<div style="width:86%">
<input name="yanzhengma" id="yan"  class="yanzhengma shoujihao" type="text" placeholder="验证码" />
 <div class="detaill"><div><input id="huoqu" type="button" class="xiaoma " value="获取验证码"/></div></div>
       </div>
   </div>
            <div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
    <!------------内容一项开始------------->
    <input class="shoujihao" type="text" style="display: none;"/>
    <input class="shoujihao" type="text" style="display: none;"/>
<div class="gongwuyuan"><input name="pwd" id="password" class="shoujihao" type="text" onfocus="this.type='password'" autocomplete="off" placeholder="密码" /></div>
<div style="clear:both;"></div>
  <!---------------------内容一项结束------------------------->  
<div ><input id="register" class="zhuce btncolor" disabled="disabled" type="button" value="确定"/></div>
  </center>
  </form>
  </body>
</html>
