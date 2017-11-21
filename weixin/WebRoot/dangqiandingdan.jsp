<%@ page language="java" import="java.util.*,net.sf.json.JSONArray" pageEncoding="UTF-8"%>
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

<title>当前订单</title>
<link rel="stylesheet" type="text/css" href="css/dingdan.css">
<script src="js/jquery-3.1.0.min.js"></script>
<script src="js/dangqiandingdan.js"></script>
</head>

<body>
<!--  <div class="header"><p><a href=""><img src="image/guanbi.png" class="left" width="44" height="44" ></a><span>约泊停车</span><a href=""><img src="image/gengduo.png" width="48" height="48"class="right"/></a></p></div>
    -->
  <!------------内容一项开始------------->
<!-- <div class="mima"><p style="float:left"><a href="javascript:history.go(-1);"><img src="image/fanhui.png" width="20" height="20" style="margin-top:10px; margin-left:15px;"></a></p><p style="float:left; margin-top:8px; margin-left:25px;"><span class="shouji">当前订单</span></p>
</div> -->
            <div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
 <input id="mphone" type="hidden" value="${sessionScope.mobileno}">
 <% String msg = (String)session.getAttribute("msg");%> 
  <% if(msg.equals("成功")){ %>
<div class="dangqiandingdan" >
	<div class="top"><br/>
		<p>${sessionScope.parkingname}</p>
		<div class="xian"></div>
		<p>泊位编码:${sessionScope.berthcode}</p>
		<% String qrcodemsg = (String)session.getAttribute("qrcodemsg"); %>
		<% if(qrcodemsg !=null){ %>
		<p>泊  位 号:${sessionScope.qrcodemsg}</p>
		<% } %>
		<p>停车时间:${sessionScope.addtime}</p>
		<p>停车时长:${sessionScope.parkduration}分钟</p>
		<p>当前费用:${sessionScope.amount}元</p>
		<br/>
	
	</div>
</div>
<% }else { %> 
<div class="dingdan1" >
<div class="car">
<div style=" width:120px; margin-left:40px; margin-top:130px; position:absolute; color:#999999;">您当前没有订单哦</div>
</div>
</div>
<%} %>
</body>
</html>
