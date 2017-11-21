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

import pojo.SNSUserInfo;
import pojo.WeixinOauth2Token;
import bean.Yuepark;
import dao.YueparkDao;
import dao.impl.YueparkDaoImpl;

import net.sf.json.JSONObject;

import util.AdvancedUtil;
import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class BalanceServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BalanceServlet() {
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

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String code = request.getParameter("code");
		String openId = null;
		String headImgUrl =null;
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wxfd965e088d2fdb94", "d6f0c3d327a2b5000a1a3c28b44f828d", code);			
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
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
			YueparkDao yueparkDao = new YueparkDaoImpl();
			if(openId !=null){
			Yuepark yuepark =yueparkDao.seach(openId);
			if(yuepark!=null){
				String phone = yuepark.getPhone();				
				if(!"null".equals(phone)){
					session.setAttribute("mobileinfo", phone);  
				}
			}
			else{
				session.setAttribute("mobileinfo", null);  
			}
		}
		String mobileinfo = (String)session.getAttribute("mobileinfo");
		if(null != mobileinfo){
			String url = HttpUrl.QueryBalance_Url;
			url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
	    	Map<String, Object> map = new HashMap<String, Object>();  
	    	map.put("mobileno", mobileinfo);
	    	String a =HttpClientUrl.sendGet(url,map,"UTF-8");
	    	System.out.println(a);
	    	
	    	JSONObject jsonObject =  JSONObject.fromObject(a);
	    	JSONObject jsonObject1 =JSONObject.fromObject(jsonObject.getString("data"));
			String balance =jsonObject1.getString("balance");
	    	session.setAttribute("overagePrice", balance);//余额
	    	session.setAttribute("mobileinfo", mobileinfo);//余额
	    	response.sendRedirect("yuechaxun.jsp");
//			request.getRequestDispatcher("yuechaxun.jsp?mphone="+mobileinfo+"&balance="+balance).forward(request, response);
		}else{
//			request.getRequestDispatcher("login.jsp").forward(request, response);
			response.sendRedirect("login.jsp");
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
