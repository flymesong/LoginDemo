<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>关闭</title>
    <meta content="width=device-width,user-scalable=0" name="viewport">
	<style type="text/css">
		*{margin: 0;padding: 0}
		table {border: 1px dashed #B9B9DD;font-size: 12pt}
		td {border: 1px bashed B9B9DD;word-break:break-all;word-wrap:break-word;}
		img { border-radius:50%}
	</style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script src="js/jquery-3.1.0.min.js"></script>
	<!-- <script src="layer/layer.js"></script>
	<script src="layer/extend/layer.ext.js"></script> -->
	<script type="text/javascript" src="layer/layer.js"></script>
  	<script type="text/javascript" src="js/close.js"></script>
  </head>
  
  <body>
  <input id="shu" type="text" class="close" />
    <input id="close" type="button" class="close"  value="dian" />
    <form action="JsonServlet" method="get">
  <input name="aaa" type="hidden" value="qwe" />
  <input name="aa" id="aa" type="text" value="${param.abc}" />
  <input type="submit" value="提交" />
  </form>
  <a href="index.jsp?ab=${param.abc}">aaa</a>
    <input type="button" id="dian" value="点"/>
    <input type="text" id="xuanze">
    <input type='text' onkeyup="(this.v=function(){this.value=this.value.replace(/^-?([1-9]\d*\.\d{1,2}|0\.\d*[1-9]\d{1,2}|0?\.0+|0)$/,'');}).call(this)" onblur="this.v();" />
    
<input onKeyPress="if((event.keyCode<48 || event.keyCode>57) && event.keyCode!=46 || /\.\d\d$/.test(value))event.returnValue=false"><br/>
<input type="text" onkeyup="clearNoNum(this)">
<hr/>
</body>
</html>

  </body>
</html>
