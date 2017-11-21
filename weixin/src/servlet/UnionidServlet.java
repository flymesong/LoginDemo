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

public class UnionidServlet extends HttpServlet {

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
		String unionid = request.getParameter("unionid");
		String phone;
		YueparkDao yueparkDao = new YueparkDaoImpl();
		Yuepark yuepark =yueparkDao.UnionId(unionid);
		if(yuepark !=null){
			phone=yuepark.getPhone();
		}else{
			phone="1";
		}
		
		JSONObject allDate = new JSONObject();
		allDate.put("phone", phone);
		out.print(allDate.toString());
		
		out.flush();
		out.close();
	}

}
