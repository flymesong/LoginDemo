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
<link rel="stylesheet" type="text/css" href="css/zhanghuchongzhi.css">
<script src="js/jquery-3.1.0.min.js"></script>
<!-- <script src="layer/layer.js"></script>
<script src="layer/extend/layer.ext.js"></script> -->
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script> 
<script type="text/javascript" src="layer/layer.js"></script>
<script src="js/zhanghuchongzhi.js"></script>
</head>

<body>
<!--  <div class="header"><p><a href=""><img src="image/guanbi.png" class="left" width="44" height="44" ></a><span>约泊停车</span><a href=""><img src="image/gengduo.png" width="48" height="48"class="right"/></a></p></div>
    -->
  <!------------内容一项开始------------->
<div class="mima"><p style="float:left"><a href="javascript:history.go(-1);"><img src="image/fanhui.png" width="20" height="20" style="margin-top:10px; margin-left:15px;"></a></p><p style="float:left; margin-top:8px; margin-left:25px;"><span class="shouji">账户充值</span></p>
   </div>
<div class="" style="clear:both;"></div>
 <!---------------------内容一项结束-------------------------> 
 <form name="payform" id="payform" action="" method="post">
<div style="margin-left:5%; margin-top:50px;">
<p><input id="shuzhi" name="paymoney" class="chongzhi" type="tel" value="50" onkeyup="clearNoNum(this)" /></p><!-- <input type="hidden" class="tiqu"> -->
<p class="moneys">
<input name="" class="money end" type="button" value="50" />
<input name="" class="money" type="button" value="100" />
<input name="" class="money" type="button" value="200" />
<input name="" class="money" type="button" value="500" /></p>

</div>
<div style="margin-left:5%;margin-top:30px;">
<p><input id="zhifu" name="" class="zhifu weixin" type="button" value="微信支付" /></p><br/>
<!-- <img id="btn_image" class="btn_image" src="image/btn_weixin.png"></img> -->
<input id="appId" class="" type="hidden" value="">
<input id="partnerId" class="" type="hidden" value="">
<input id="nonceStr" class="" type="hidden" value="">
<input id="sign" class="" type="hidden" value="">
<input id="package" class="" type="hidden" value="">
<input id="prepayid" class="" type="hidden" value="">
<input id="timestamp" class="" type="hidden" value="">


</div></form>
</body>
</html>
