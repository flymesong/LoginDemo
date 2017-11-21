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

import bean.Yuepark;
import dao.YueparkDao;
import dao.impl.YueparkDaoImpl;

import net.sf.json.JSONObject;

import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.GetMD5Code;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class ModifyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ModifyServlet() {
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
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		
		String oldpwd1=GetMD5Code.GetMD5Code(oldpwd);
		String newpwd1=GetMD5Code.GetMD5Code(newpwd);
		String mobile = request.getParameter("mphone");
		

//		if(null !=mobile){
			
		String url = HttpUrl.ChangePwd_Url;
		url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
		Map<String, Object> map = new HashMap<String, Object>();  
		map.put("mobilenumber", mobile);
  	  	map.put("oldpwd", oldpwd1);
  	  	map.put("newpwd", newpwd1);
  	  	String a =HttpClientUrl.sendGet(url,map,"UTF-8");
  	  	System.out.println(a);
	    JSONObject jsonObject =  JSONObject.fromObject(a);
	    String msg=jsonObject.getString("msg");
	    String status = jsonObject.getString("status");
		JSONObject allDate = new JSONObject();
		if(status.equals("1")){
	    	allDate.put("snum", status);
	    	allDate.put("msg", msg);
	    	YueparkDao yueparkDao = new YueparkDaoImpl();
			yueparkDao.updatepassword(newpwd1, mobile);
	    }else{
	    	allDate.put("snum", status);
	    	allDate.put("msg", msg);
	    }
		out.print(allDate.toString());
  	  	

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
		
		this.doGet(request, response);
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
