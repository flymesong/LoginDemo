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

import net.sf.json.JSONObject;

import pojo.SNSUserInfo;
import pojo.WeixinOauth2Token;
import util.AdvancedUtil;
import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;
import bean.Yuepark;
import dao.YueparkDao;
import dao.impl.YueparkDaoImpl;

public class YuechaxunServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public YuechaxunServlet() {
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

		this.doPost(request, response);
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

		response.setContentType("text/html");
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
//		}
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = sdf.format(date);
			YueparkDao yueparkDao = new YueparkDaoImpl();
			if(openId !=null){
			Yuepark yuepark =yueparkDao.seach(openId);
	//	Yuepark yuepark1=new Yuepark();
			if(yuepark!=null){
				String phone = yuepark.getPhone();
//				yueparkDao.update(accessToken, openId,phone);
				
				if(!"null".equals(phone)){
					session.setAttribute("mobileinfo", phone);  
				}
			}else{
				Yuepark yuepark1 = new Yuepark();
				yuepark1.setOpenid(openId);
				yuepark1.setImgurl(headImgUrl);		
	//			yuepark1.setPassword(password1);
				yuepark1.setAccesstoken(accessToken);
				yuepark1.setAddtime(time);
				yueparkDao.Addopenid(yuepark1);
			}
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
			request.getRequestDispatcher("yuechaxun.jsp").forward(request, response);
		}else{
//			request.getRequestDispatcher("register.jsp").forward(request, response);
			response.sendRedirect("login.jsp");
		}
		out.flush();
		out.close();
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
