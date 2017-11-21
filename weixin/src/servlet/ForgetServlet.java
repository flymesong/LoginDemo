package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class ForgetServlet extends HttpServlet {

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
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String pwd1=MathsUtil.GetMD5Code(password);
		JSONObject allDate = new JSONObject();
//		boolean result =false;
		String url = HttpUrl.ResetPwd_Url;
		url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobileno", phone);
		map.put("loginpwd", pwd1);
		String a =HttpClientUrl.sendGet(url,map,"UTF-8");
	    System.out.println(a);
	    JSONObject jsonObject =  JSONObject.fromObject(a);
	    String msg=jsonObject.getString("msg");
	    String status = jsonObject.getString("status");
	    if(status.equals("1")){
	    	allDate.put("snum", status);
	    	allDate.put("msg", msg);
	    }else{
	    	allDate.put("snum", status);
	    	allDate.put("msg", msg);
	    }
		out.print(allDate.toString());
		out.flush();
		out.close();
	}

}
