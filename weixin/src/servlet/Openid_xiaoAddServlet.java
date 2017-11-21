package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import bean.Yuepark;
import dao.YueparkDao;
import dao.impl.YueparkDaoImpl;

public class Openid_xiaoAddServlet extends HttpServlet {

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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String openid = request.getParameter("openid");
		String phone = request.getParameter("phone");
		String result;
		YueparkDao yueparkDao = new YueparkDaoImpl();
		int yuepark =yueparkDao.addopenid_xiao(phone, openid);
		if(yuepark > 0){
			result="1";
		}else{
			result="0";
		}
		
		JSONObject allDate = new JSONObject();
		allDate.put("result", result);
		out.print(allDate.toString());
		
		out.flush();
		out.close();
	}

}
