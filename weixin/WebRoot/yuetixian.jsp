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
<link rel="stylesheet" type="text/css" href="css/yuetixian.css">
<script src="js/jquery-3.1.0.min.js"></script>
<!-- <script src="layer/layer.js"></script>
<script src="layer/extend/layer.ext.js"></script> -->
<script type="text/javascript" src="layer/layer.js"></script>
<script src="js/tixian.js"></script>
</head>

<body>
<!--  <div class="header"><p><a href=""><img src="image/guanbi.png" class="left" width="44" height="44" ></a><span>约泊停车</span><a href=""><img src="image/gengduo.png" width="48" height="48"class="right"/></a></p></div>
    -->
  <!------------内容一项开始------------->
<div class="mima"><p style="float:left"><a href="javascript:history.go(-1);"><img src="image/fanhui.png" width="20" height="20" style="margin-top:10px; margin-left:15px;"></a></p><p style="float:left; margin-top:8px; margin-left:25px;"><span class="shouji">余额提现</span></p>
   </div>
            <div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
 <form name="coshoutform" id="coshoutform" action="CashOutServlet" method="post">
<div style="margin-left:7%; margin-top:20px;">
<p>请输入要转出的金额</p><br/>

<p><input id="shuzhi" name="tixianmenoy" class="tiqu" type="tel" placeholder="0" onkeyup="clearNoNum(this)"/></p><br/>
<input name="balance" id="" type="hidden" value="${param.balance}">
<p>当前账户余额：<b id="cashout">${param.balance}</b>元，<b id="allcash" style="color:#3598fe;">全部提现</b></p><br/>
<br/>
<input id="mphone" name="mphone"  type="hidden" value="${param.mphone}">
<input id="balance" name="balance" type="hidden" value="${param.balance}">
<input  id="tixian" name="" class="retiqu btncolor" disabled="disabled" type="button" value="3~5个工作日到账，确认提现" />
</div></form>
</body>
</html>
