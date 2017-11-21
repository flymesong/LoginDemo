<%@ page language="java" import="java.util.*,pojo.*,yuepark.http.*,yuepark.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
	<meta content="width=device-width,user-scalable=0" name="viewport">
	<style type="text/css">
		*{margin: 0;padding: 0}
		table {border: 1px dashed #B9B9DD;font-size: 12pt}
		td {border: 1px bashed B9B9DD;word-break:break-all;word-wrap:break-word;}
		img { border-radius:50%}
		.btn_class{
				width:95%;
				height:50px;
				text-align:center;
				font-size:24px;
				border-radius:5px;
				border:0;
				background-image: url(image/exit.png);
				background-repeat: no-repeat;
				/* background-size:100% 100%; */
				}
	</style>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
   <script type="text/javascript">
  	 window.alert = function(name){
	var iframe = document.createElement("IFRAME");
	iframe.style.display="none";
	iframe.setAttribute("src", 'data:text/plain,');
	document.documentElement.appendChild(iframe);
	window.frames[0].window.alert(name);
	iframe.parentNode.removeChild(iframe);
	};
	
  </script>
  <body>
  <input type="hidden" id="phone" value="${param.code}" >
  <%--  <% 
		// 获取由OAuthServlet中传入的参数
		SNSUserInfo user = (SNSUserInfo)request.getAttribute("snsUserInfo"); 
		if(null != user) {
	%>
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr><td width="20%">属性</td><td width="80%">值</td></tr>
		<tr><td>OpenID</td><td><%=user.getOpenId()%></td></tr>
		<tr><td>昵称</td><td><%=user.getNickname()%></td></tr>
		<tr><td>性别</td><td><%=user.getSex()%></td></tr>
		<tr><td>国家</td><td><%=user.getCountry()%></td></tr>
		<tr><td>省份</td><td><%=user.getProvince()%></td></tr>
		<tr><td>城市</td><td><%=user.getCity()%></td></tr>
		<tr><td>头像</td><td><img src="<%=user.getHeadImgUrl()%>" /></td></tr>
		<tr><td>特权</td><td><%=user.getPrivilegeList()%></td></tr>
	</table>
	<%
		}
		else 
			out.print("用户不同意授权,未获取到用户信息！");
			
	%>
	<img src="http://wx.qlogo.cn/mmopen/U4ic68icMpNjm7O8eibBXHeYdEoWSP8xZ5gvQ66KrmzmJicAPkI7libCicR7KFlia4Y1qQVXkaqL9uXPf3ZRlaYBRQ4TTv5Tgpmkk5W/0" width="44" height="44" />
 --%>  
<%--  <%
 	String url = HttpUrl.Register_Url;
 	String s=HttpUtil.sendGet(url, null);
  %> --%>
 <%--  
  <input type="text"id="phone" value="${param.a}" ><br>
  <input type="button" id="json" value="Json">
  
 <input type="button" id="btn" value="免费获取验证码" /> 
 <input type="submit" value="提交" /> 
 <p> <%=getServletContext().getAttribute("name") %> </p> <p> <%=getServletContext().getAttribute("pwd") %> </p>
<script type="text/javascript">  
var wait=60;  
function time(o) {  
        if (wait == 0) {  
            o.removeAttribute("disabled");            
            o.value="免费获取验证码";  
            wait = 60;  
        } else {  
            o.setAttribute("disabled", true);  
            o.value="重新发送(" + wait + ")";  
            wait--;  
            setTimeout(function() {  
                time(o)  
            },  
            1000)  
        }  
    }  
document.getElementById("btn").onclick=function(){time(this);}  
</script>  --%>
 </body>
</html>
