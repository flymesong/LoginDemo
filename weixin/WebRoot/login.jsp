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

<title>登录</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
<script src="js/jquery-3.1.0.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<!-- <script src="layer/layer.js"></script>
<script src="layer/extend/layer.ext.js"></script> -->
<script type="text/javascript" src="layer/layer.js"></script>
<script src="js/login.js"></script>
</head>

<body>
<!--  <div class="header"><p><a href=""><img src="image/guanbi.png" class="left" width="44" height="44" ></a><span>约泊停车</span><a href=""><img src="image/gengduo.png" width="48" height="48"class="right"/></a></p></div>
    -->
    <form name="loginform" id="loginform" action="" method="post">
    <center>
  <!------------内容一项开始------------->
  <input id="code" name="code" type="hidden" value="${param.code}">
  <input id="btn_error" name="btn_error" type="hidden" value="${param.aa}">
  <input id="exit_btn" name="exit_btn" type="hidden" value="${param.exit_btn}">
  <input id="biaoshi" name="biaoshi" type="hidden" value="${sessionScope.mobileinfo}"> 
  
  <input id="relogin" type="hidden" value="">

            <div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
<!------------内容一项开始------------->
<div id="keyup" style="text-align:center">
<div class="gongwuyuan" style="margin-top:55px"><input id="mphone" name="mophone"  class="shoujihao" type="tel" onkeyup="this.value=this.value.replace(/\D/g, '')" placeholder="手机号" /></div>
<div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
  <!------------内容一项开始------------->
  <input class="shoujihao" type="text" style="display: none;"/>
  <input class="shoujihao" type="text" style="display: none;"/>
<div class="gongwuyuan" style="    margin-top: 25px;"><input id="pwd" name="pwd"  class="shoujihao" type="text" onfocus="this.type='password'" autocomplete="off" placeholder="密码" /></div>
<div style="clear:both;"></div>

</div>
  <!---------------------内容一项结束------------------------->      
   <!------------内容一项开始------------->
   
   <input id="ma" name="ma"  type="hidden" value=""/>
<div class="gongwuyuan" style="    margin-top: 25px;">
<div style="width:86%">
<input name="yanzhengma" id="yan"  class="yanzhengma shoujihao" type="text" placeholder="验证码" />
 <div class="detaill"><div><input id="huoqu" type="button" class="xiaoma " value="获取验证码"/></div></div>
       </div>
   </div>
            <div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
  <!------------内容一项开始------------->
  <!---------------------内容一项结束-------------------------> 
<div style="text-align:center"><input id="login" class="denglu btncolor" disabled="disabled" type="button" value="立即登录"/></div>

<div style="text-align:center"><input id="zhuce" class="zhuce btncolor" disabled="disabled" type="button" value="快速注册" /></div>
<div class="gongwuyuan"><a href="wangjimima.jsp" style="float:right;font-size:15px; margin-right: 8%;color:#3598fe">忘记密码</a>
   </div>
</center></form>
</body>
</html>

