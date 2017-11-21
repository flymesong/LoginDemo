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

<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/register.css">
<script src="js/jquery-3.1.0.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script src="js/register.js"></script>
</head>

<body>
<!--  <div class="header"><p><a href=""><img src="image/guanbi.png" class="left" width="44" height="44" ></a><span>约泊停车</span><a href=""><img src="image/gengduo.png" width="48" height="48"class="right"/></a></p></div>
     -->
     <form name="loginform" id="loginform" action="" method="get" style="margin-top:55px">
     <center>
  <!------------内容一项开始------------->
<input id="btn_error" name="btn_error" type="hidden" value="${param.aa}">
            <div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
  <input id="txtphone" name="yanphone"  type="hidden" value="${sessionScope.mobileinfo}"/>
  <input id="yanphone" name="yanphone"  type="hidden" value=""/>
<!------------内容一项开始------------->
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
  <input class="shoujihao" type="text" style="display: none;"/>
  <input class="shoujihao" type="text" style="display: none;"/>
    <!------------内容一项开始------------->
<div class="gongwuyuan"><input name="pwd" id="password" class="shoujihao" type="text" onfocus="this.type='password'" autocomplete="off" placeholder="密码" /></div>
<div style="clear:both;"></div>
  <!---------------------内容一项结束------------------------->  
      <!------------内容一项开始------------->
<div class="gongwuyuan"><input name="repassword" id="repassword" class="shoujihao" type="text" onfocus="this.type='password'" autocomplete="off" placeholder="确认密码" /></div>
<div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
  <!------------内容一项开始------------->
<div class="xieyi">
<div style="width:86%">
<input id="checked" style="-webkit-appearance:checkbox !important;"  class="ppp"  name="checked"  checked="checked"  type="checkbox" value=""> <p class="pp">我已阅读并同意<p style="color:#3598fe">约泊用户服务协议</p> </div>
   </div>
            <div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
<div ><input id="register" class="zhuce btncolor" disabled="disabled" type="button" value="注册"/></div>
<div class="gongwuyuan"><a href="login.jsp" style="float:right;font-size:15px; margin-right: 7%;color:#3598fe;">去登录</a>
   </div>
</center></form>
</body>
</html>

