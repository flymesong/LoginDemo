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

<title>我的钱包</title>
<link rel="stylesheet" type="text/css" href="css/yuechaxun.css">
<script src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script src="js/yuechaxun.js"></script>
</head>

<body>
<!--  <div class="header"><p><a href=""><img src="image/guanbi.png" class="left" width="44" height="44" ></a><span>约泊停车</span><a href=""><img src="image/gengduo.png" width="48" height="48"class="right"/></a></p></div>
    -->
  <!------------内容一项开始------------->
<!-- <div class="mima"><p style="float:left"><a href="javascript:history.go(-1);"><img src="image/fanhui.png" width="20" height="20" style="margin-top:10px; margin-left:15px;"></a></p><p style="float:left; margin-top:8px; margin-left:25px;"><span class="shouji">我的钱包</span></p>
   </div>  -->
<div class="" style="clear:both;"></div>
 <!---------------------内容一项结束-------------------------> 
 <div>
<div class="beijing">
<!-- <div align="center" style=" width:250px; height:26px; margin:0 auto; padding-top:3px;"><p style="float:left;margin-top: 5px; "><img src="image/tanhao.png" width="15" height="15"></p><p style="float:left;margin:2% auto 2% auto; color:#FFF; font-size:12px">您需要保持账户余额不低于99元，才能停车</p></div> -->
<div class="money"><p style="font-size:36px;font-weight: bold;"><% String mobileinfo = (String)session.getAttribute("mobileinfo"); if(null!=mobileinfo){%>${sessionScope.overagePrice}<%}else{ %>0.00<%} %></p><p style="font-size:12px;">账户余额（元）</p></div>
<input id="mphone" name="mphone" type="hidden" value="${sessionScope.mobileinfo}">
<input id="balance" name="balance" type="hidden" value="${sessionScope.overagePrice}">

</div>
<div class="btn_chongzhi"><a href="zhanghuchongzhi.jsp"><input id="chongzhi" class="chongzhi" type="button" value="我要充值"/></a><br/>

<a id="tixian"><input  class="tixian" type="button" value="我要提现"/></a>

</div></div>
</body>
</html>
