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
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class LogoutServlet extends HttpServlet {

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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String mobileno =request.getParameter("exit_phone");
		String url = HttpUrl.Logout_Url;
		url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobileno", mobileno);
		String a =HttpClientUrl.sendGet(url,map,"UTF-8");
		JSONObject jsonObject =  JSONObject.fromObject(a);
		String status = jsonObject.getString("status");
		System.out.println(status);
		JSONObject allDate = new JSONObject();
		if(status.equals("1")){
			YueparkDao yueparkDao = new YueparkDaoImpl();
			Yuepark yuepark=yueparkDao.seachPhone(mobileno);
			if(yuepark==null){
				allDate.put("status", "0");
			}else{
				yueparkDao.delPhone(mobileno);
				allDate.put("status", "1");
				session.invalidate();
			}
		}else{
			allDate.put("status", "0");
		}
		out.print(allDate.toString());
		out.flush();
		out.close();
	}

}
