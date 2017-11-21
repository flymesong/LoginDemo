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
import yuepark.util.GetMD5Code;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class RegisterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
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
//		HttpSession session = request.getSession();
		
		
		String phone = request.getParameter("mphone");
		String pwd =request.getParameter("pwd");
		String pwd1=MathsUtil.GetMD5Code(pwd);
		
		String url = HttpUrl.Register_Url;
		url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobileno", phone); 
		map.put("loginpwd", pwd1); 
		String a =HttpClientUrl.sendGet(url,map,"UTF-8");
		JSONObject jsonObject =  JSONObject.fromObject(a);
		JSONObject jsonObjectlogin =JSONObject.fromObject(jsonObject.getString("data"));
		String msg =jsonObject.getString("msg");
		String status = jsonObject.getString("status");
		JSONObject allDate = new JSONObject();
		System.out.println(jsonObject);
		allDate.put("status", status);
		allDate.put("msg", msg);
		allDate.put("jsonObjectlogin", jsonObjectlogin);
		out.print(jsonObject.toString());
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
//    public final static String paypwd = "123456";
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
