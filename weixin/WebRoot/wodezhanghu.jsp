<%@ page language="java" import="java.util.*,pojo.*" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="css/wodezhuanghu.css">
<script src="js/jquery-3.1.0.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript" src="js/wodezhanghu.js"></script>
</head>

<body>
<% 
		String headImgUrl = (String)session.getAttribute("headImgUrl");
		String xiugaiphone =(String)session.getAttribute("mobileinfo");
		String xiugaioveragePrice=(String)session.getAttribute("overagePrice");
	%>
   <input id="" name="" type="hidden" value="<%=headImgUrl %>"> 
   <input id="abc" name="abc" type="hidden" value="${param.abc}">
  <!------------内容一项开始------------->
<div class="qq"><div style="margin-top:30px;"></div><div style=" height:60px; width:100%"><div style="width:200px;height:60px;margin:0 auto"><div style="  margin-left:10px; margin-top:10px;float:left"><img class="imges" src="${sessionScope.headImgUrl}" width="55" height="55" ></div>
<div style="float:left; margin-top:15px; margin-left:8px;color:#FFF;"><p style=" font-size:18px; ">${sessionScope.mobileinfo}</p><p style="font-size:14px;">账户余额:<span>${sessionScope.overagePrice}</span>元</p></div> </div>
    </div></div>
            <div style="clear:both;"></div>
  <!---------------------内容一项结束-------------------------> 
  <div class="qqq">
<!------------内容一项开始------------->
<a href="xiugaimima.jsp?phone=<%=xiugaiphone %>&overagePrice=<%=xiugaioveragePrice %>&headImgUrl=<%=headImgUrl %>"><div class="gongwuyuan"><div class="ceng"><img src="image/xiugaimima.png" width="25" height="25"></div><div class="ci"><p>修改密码</p></div><div style="float:right; margin-top:20px; margin-right:10px;"><img src="image/fanhui2.png" width="15" height="15"></div></div>
<div style="clear:both;"></div></a>
  <!---------------------内容一项结束------------------------->    
  <!------------内容一项开始------------->
<a href="shiyongxieyi.jsp"><div class="gongwuyuan"><div class="ceng" style="margin-left:22px"><img src="image/shiyongxieyi.png" width="22" height="22"></div><div class="ci"><p>使用协议</p></div><div style="float:right; margin-top:20px;margin-right:10px;"><img src="image/fanhui2.png" width="15" height="15"></div></div>
<div style="clear:both;"></div></a>
  <!---------------------内容一项结束-------------------------> 
  <!------------内容一项开始------------->
<a href="changjianwenti.jsp"><div class="gongwuyuan"><div class="ceng"><img src="image/changjianwenti.png" width="25" height="25"></div><div class="ci"><p>常见问题</p></div><div style="float:right; margin-top:20px;margin-right:10px;"><img src="image/fanhui2.png" width="15" height="15"></div></div>
<div style="clear:both;"></div></a>
  <!---------------------内容一项结束-------------------------> 
    <!------------内容一项开始------------->
<div class="gongwuyuan" id="click"><div class="ceng"><img src="image/exit.png" width="25" height="25"></div><div class="ci"><p>安全退出</p></div><div style="float:right; margin-top:20px;margin-right:10px;"><img src="image/fanhui2.png" width="15" height="15"></div></div>
<div style="clear:both;"></div>
<div class="cloak">
    <!-- <div class="box">
        <div class="title">我是标题 <span>X</span></div>
        <div class="content">
            <p>我是内容</p>
            <p>我是内容</p>
            <p>我是内容</p>
        </div>
    </div> -->
    <div class="div_bottom">
    <input id="exit_phone" name="exit_phone" type="hidden" value="${sessionScope.mobileinfo}"> 
    	<div><input type="button" id="btn_exit" class="btn_exit" value="退出登录"></div>
    	<div><input type="button" id="btn_off" class="btn_off" value="取消"></div>
    </div>
</div>

  <!---------------------内容一项结束-------------------------> </div>
</body>
</html>
