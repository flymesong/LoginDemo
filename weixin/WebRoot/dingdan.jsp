<%@ page language="java" import="java.util.*,net.sf.json.JSONArray" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width"/>
<meta name="viewport" content="initial-scale=1.0,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>我的订单</title>
<link rel="stylesheet" type="text/css" href="css/dingdan.css">
<script src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">
    // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
    var useragent = navigator.userAgent;
    if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
        // 这里警告框会阻塞当前页面继续加载
        layer.msg('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
        // 以下代码是用javascript强行关闭当前页面
        var opened = window.open('about:blank', '_self');
        opened.opener = null;
        opened.close();
    }
</script>
</head>

<body>
<!--  <div class="header"><p><a href=""><img src="image/guanbi.png" class="left" width="44" height="44" ></a><span>约泊停车</span><a href=""><img src="image/gengduo.png" width="48" height="48"class="right"/></a></p></div>
    -->
  <!------------内容一项开始------------->
<!-- <div class="mima"><p style="float:left"><a href="javascript:history.go(-1);"><img src="image/fanhui.png" width="20" height="20" style="margin-top:10px; margin-left:15px;"></a></p><p style="float:left; margin-top:8px; margin-left:25px;"><span class="shouji">我的订单</span></p>
</div> -->
         <!--    <div style="clear:both;"></div> -->
  <!---------------------内容一项结束-------------------------> 
  
  <!-- <div style="width:85%;height:40px;"></div> -->
  <% Integer count = (Integer)session.getAttribute("count");%>
  <% if(count > 0){ %>
 <c:forEach var="arrays" items="${sessionScope.jsonarray}">
 <c:if test="${arrays.applytype == 4}">
<div class="dingdan" >
<div class="top">

<p style="margin-top: 15px;font-weight: bold;">${arrays.parkingname}</p>
<div class="xian"></div>
<p>泊位编号：${arrays.berthcode}</p>
<p>订单编号：${arrays.bargainordercode}</p>
<c:if test="${arrays.qrcodemsg != null}">
<p>泊  位  号：${arrays.qrcodemsg}</p>
</c:if>
<p>停车时间：${arrays.parktime}</p>
<p>停车时长：${arrays.parkduration}分钟</p>
<p style="margin-bottom: 10px;">实际费用：${arrays.amount}元</p>
</div>
</div>
</c:if>
</c:forEach>
<% }else { %> 
<div class="dingdan1" >
<div class="car">
<div style=" width:120px; margin-left:40px; margin-top:130px; position:absolute; color:#999999;">您当前没有订单哦</div>
</div>
</div>
<%} %>
</body>
</html>
