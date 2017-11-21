package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.sun.java.swing.plaf.windows.resources.windows;


import pojo.SNSUserInfo;
import pojo.WeixinOauth2Token;
import bean.Yuepark;
import dao.YueparkDao;
import dao.impl.YueparkDaoImpl;
import net.sf.json.JSONObject;
import util.AdvancedUtil;
import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.GetMD5Code;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
				
		String code = request.getParameter("code");
		String type= request.getParameter("type");
		String mphone = request.getParameter("mphone");
		String pwd =request.getParameter("pwd");
		String pwd1=MathsUtil.GetMD5Code(pwd);
			
		String openId = null;
		String headImgUrl =null;
		String accessToken = null;
		if (null != code) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wxfd965e088d2fdb94", "d6f0c3d327a2b5000a1a3c28b44f828d", code);			
			// 网页授权接口访问凭证
			accessToken = weixinOauth2Token.getAccessToken();
			// 用户标识
			openId = weixinOauth2Token.getOpenId();
			// 获取用户信息
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
			headImgUrl = snsUserInfo.getHeadImgUrl();
			
			
			// 设置要传递的参数
			session.setAttribute("snsUserInfo", snsUserInfo);
			session.setAttribute("openId", openId);
			session.setAttribute("accessToken", accessToken);
			session.setAttribute("headImgUrl", snsUserInfo.getHeadImgUrl());
	}
		
		String unurl="https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN ";
		String unurlid =HttpClientUrl.sendGet(unurl,null,"UTF-8");
		JSONObject jsonObjectunurlid =  JSONObject.fromObject(unurlid);
		String jsonObjectunurlid12 =jsonObjectunurlid.getString("unionid");
		
				
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String time = sdf.format(date);
				YueparkDao yueparkDao = new YueparkDaoImpl();
				Yuepark yuepark=yueparkDao.seachPhone(mphone);
				
				Yuepark yuepark1 = new Yuepark();
				yuepark1.setOpenid(openId);
				yuepark1.setImgurl(headImgUrl);
				yuepark1.setPhone(mphone);
				yuepark1.setPassword(pwd1);
				yuepark1.setAccesstoken(accessToken);
				yuepark1.setAddtime(time);
				yuepark1.setUnionid(jsonObjectunurlid12);
				if(null != yuepark){
					yueparkDao.update(yuepark1);
				}else{
					
					yueparkDao.Addopenid(yuepark1);
				}
				if(type.equals("1")){
					response.sendRedirect("login.jsp?aa=2");
				}else{
					response.sendRedirect("login.jsp?aa=0");
				}
				
		out.flush();
		out.close();
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
//	public final static String loginpwd = "123456";
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
