package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.Yuepark;

import dao.YueparkDao;
import dao.impl.YueparkDaoImpl;

import pojo.SNSUserInfo;
import pojo.WeixinOauth2Token;

import net.sf.json.JSONObject;

import util.AdvancedUtil;
import util.CommonUtil;
import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class PayServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PayServlet() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
									
//		String mobileinfo = (String)session.getAttribute("mobileinfo");
		
		String code = request.getParameter("code");
		String paymoney = request.getParameter("paymoney");
		Float money = Float.parseFloat(paymoney);
		String openId = null;
		if (null != code) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wxfd965e088d2fdb94", "d6f0c3d327a2b5000a1a3c28b44f828d", code);			
			// 用户标识
			openId = weixinOauth2Token.getOpenId();
	
	}
		 
		
		if(null != openId){
			YueparkDao yueparkDao = new YueparkDaoImpl();
			Yuepark yuepark =yueparkDao.seach(openId);
			if(yuepark!=null){
				String phone = yuepark.getPhone();				
				if(!"null".equals(phone)){
					session.setAttribute("mobileinfo", phone);  
				}
			
//		String mobileinfo = (String)session.getAttribute("mobileinfo");
			String url = HttpUrl.BalanceRecharge_Url;//余额充值
			url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
			Map<String, Object> map = new HashMap<String, Object>();  
	  	  	map.put("mobileno", phone);
	  	  	map.put("amount", money); 
	  	  	map.put("paytype", 3);
	  	  	map.put("openid", openId);
	  	  	String a =HttpClientUrl.sendGet(url,map,"UTF-8");
	  	  	System.out.println(a);
	  	  	JSONObject payjsonObject =  JSONObject.fromObject(a);
	  	  	JSONObject payjsonObject1 =JSONObject.fromObject(payjsonObject.getString("data"));
//	  	  	System.out.println(payjsonObject1);
	  	  	
	  	  	JSONObject payjsonObject2 =JSONObject.fromObject(payjsonObject1.getString("WxResponse"));
//	  	  	System.out.println(payjsonObject2);
//			String appId =payjsonObject2.getString("appId");
//			String signType =payjsonObject2.getString("signType");
//			String nonceStr =payjsonObject2.getString("nonceStr");
//			String paySign =payjsonObject2.getString("paySign");
//			String package1 =payjsonObject2.getString("package");
//			String timeStamp =payjsonObject2.getString("timeStamp");
	  	  	
			out.print(payjsonObject2.toString());
//			out.print(openId.toString());
	  	  	}
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
