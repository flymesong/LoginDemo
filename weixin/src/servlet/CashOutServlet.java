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

import net.sf.json.JSONObject;

import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class CashOutServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CashOutServlet() {
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

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
//		String mphone = (String)session.getAttribute("mobileinfo");
		String mphone = request.getParameter("mphone");
		String balances= request.getParameter("balance");
//		String mphone =null;
		if(null != mphone){
			String tixianmenoy = request.getParameter("tixianmenoy");
			String url = HttpUrl.ApplyCashOut_Url;//余额提现
			url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
			Map<String, Object> map = new HashMap<String, Object>();  
	  	  	map.put("mobileno", mphone);
	  	  	map.put("amount", tixianmenoy); 
	  	  	String a =HttpClientUrl.sendGet(url,map,"UTF-8");
	  	  	System.out.println(a);
	  	  	JSONObject jsonObjecta =  JSONObject.fromObject(a);
//			JSONObject jsonObject12a =JSONObject.fromObject(jsonObjecta.getString("data"));
			String status =jsonObjecta.getString("status");
			String msg =jsonObjecta.getString("msg");
			System.out.println(status);
			if(status.equals("1")){
	  	    String url1 = HttpUrl.QueryBalance_Url;
			url1 = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url1));
	    	Map<String, Object> map1 = new HashMap<String, Object>();  
	    	map1.put("mobileno", mphone);
	    	String a1 =HttpClientUrl.sendGet(url1,map1,"UTF-8");
	    	System.out.println(a1);
	    	
	    	JSONObject jsonObject =  JSONObject.fromObject(a1);
	    	JSONObject jsonObject1 =JSONObject.fromObject(jsonObject.getString("data"));
			String balance =jsonObject1.getString("balance");
//			float result = Float.parseFloat(balance)-Float.parseFloat(tixianmenoy);
	    	session.setAttribute("overagePrice", balance);//余额
//	    	response.sendRedirect("yuechaxun.jsp");
	    	request.getRequestDispatcher("yuechaxun.jsp?mphone="+mphone+"&balance="+balance).forward(request, response);
			}else
			if(status.equals("0")){
//				String sjf="123";
//				System.out.println(sjf);
//				out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
//				out.println("<meta name='viewport' content='width=device-width'/>");
//				out.println("<meta name='viewport' content='initial-scale=1.0,user-scalable=no'/>");
//				out.println("<meta name='apple-mobile-web-app-capable' content='yes'>");
//				out.println("<meta name='apple-mobile-web-app-status-bar-style' content='black'>");				
//				out.println("<script src='../weixin/js/jquery-3.1.0.min.js'></script>");
//				out.println("<script type='text/javascript' src='../weixin/layer/layer.js'></script>");
//				out.println("<script language='javascript'>layer.msg('"+msg+"');window.location.href='yuetixian.jsp?mphone="+mphone+"&balance="+balances+"';</script>");
//				out.println("  <BODY>");
//				
//				out.println("  </BODY>");
//				out.println("</HTML>");
				
				response.sendRedirect("yuetixian.jsp?mphone="+mphone+"&balance="+balances);
//				request.getRequestDispatcher("yuetixian.jsp?mphone="+mphone+"&tixianmenoy="+tixianmenoy).forward(request, response);
//				out.print("<script language='javascript'>alert('1');</script>");
//			}else{
				
			}
		}else{
			response.sendRedirect("register.jsp");
//			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
//		}
		
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
