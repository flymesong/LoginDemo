package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class VerificationServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public VerificationServlet() {
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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String phone = request.getParameter("phone");
		String ma = request.getParameter("ma");
		//请求接口返回验证码；
		//判断验证码为不为空：存验证码
		
		
		String url = HttpUrl.Getsafecode_Url;
		url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
		Map<String, Object> map = new HashMap<String, Object>();  
  	  	map.put("mobileno", phone);
	    String a =HttpClientUrl.sendGet(url,map,"UTF-8");
	    System.out.println(a);
	
	    JSONObject jsonObject =  JSONObject.fromObject(a);
	
	    JSONObject jsonObject1 =JSONObject.fromObject(jsonObject.getString("data"));
		System.out.println(jsonObject1.getString("safecode"));
		
		String yanzhengma=jsonObject1.getString("safecode");
		ma=yanzhengma;
//  	  safecode
		JSONObject allDate = new JSONObject();
		allDate.put("ma", ma);
		out.print(allDate.toString());
//		System.out.println(allDate.toString());
//		request.setAttribute("yanzhengma", yanzhengma);
//		session.setAttribute("yanzhengma",yanzhengma);
//		response.sendRedirect("../weixin/register.jsp");
//		request.getRequestDispatcher("../weixin/register.jsp").forward(request, response);
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
