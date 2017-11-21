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
<link rel="stylesheet" type="text/css" href="css/xiugaimima.css">
<script src="js/jquery-3.1.0.min.js"></script>
<!-- <script src="layer/layer.js"></script>
<script src="layer/extend/layer.ext.js"></script> -->
<script type="text/javascript" src="layer/layer.js"></script>
<script src="js/xiugaimima.js"></script>
</head>

<body>
<!--  <div class="header"><p><a href=""><img src="image/guanbi.png" class="left" width="44" height="44" ></a><span>约泊停车</span><a href=""><img src="image/gengduo.png" width="48" height="48"class="right"/></a></p></div>
    -->
  <!------------内容一项开始------------->
<div class="mima"><p style="float:left"><a href="javascript:history.go(-1);"><img src="image/fanhui.png" width="20" height="20" style="margin-top:10px; margin-left:15px;"></a></p><p style="float:left; margin-top:8px; margin-left:25px;"><span class="shouji">修改密码</span></p>
   </div>
            <div style="clear:both;"></div>
            <form name="modifyform" id="modifyform" action="" method="post" onsubmit="return login()" >
            <center>
  <!---------------------内容一项结束-------------------------> 
  <% String mobileinfo = (String)session.getAttribute("mobileinfo"); %>
  <input id="headImgUrl" name="headImgUrl" type="hidden" value="${param.headImgUrl}">
  <input id="overagePrice" name="overagePrice" type="hidden" value="${param.overagePrice}">
  <input id="phone" name="phone" type="hidden" value="${param.phone}">
  <input id="reoldpwd" type="hidden" value="1">
  <input id="dangqianpwd" type="hidden" value="">
  <input class="shoujihao" type="text" style="display: none;"/>
  <input class="shoujihao" type="text" style="display: none;"/>
<!------------内容一项开始------------->
<div class="gongwuyuan"><input id="pwd" name="oldpwd" class="shoujihao" type="text" onfocus="this.type='password'" autocomplete="off" placeholder="当前密码" /></div>
<div style="clear:both;"></div>
  <!---------------------内容一项结束------------------------->    
<!------------内容一项开始------------->
<div class="gongwuyuan"><input id="newpwd" name="newpwd" class="shoujihao" type="text" onfocus="this.type='password'" autocomplete="off" placeholder="新密码" /></div>
<div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
  <!------------内容一项开始------------->
<div class="gongwuyuan"><input id="repwd" name="repwd" class="shoujihao" type="text" onfocus="this.type='password'" autocomplete="off" placeholder="确认密码" /></div>
<div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
  <!------------内容一项开始------------->
<div class="xieyi">
   </div>
            <div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
<div ><input id="queren" class="zhuce btncolor" type="button" disabled="disabled" value="确定修改"/></div></center></form>
</body>
</html>

