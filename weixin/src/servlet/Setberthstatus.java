package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yuepark.http.HttpUrl;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class Setberthstatus extends HttpServlet {

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
		String mobileno =request.getParameter("mobileno");
		String parkPwd =request.getParameter("parkPwd");//车位锁编号
		String parkStatus = request.getParameter("parkStatus");//车位状态
		String machineStatus = request.getParameter("machineStatus");//锁臂状态
		String fusestatus = request.getParameter("fusestatus");//保险丝状态
		String url = HttpUrl.Setberthstatus_Url;//控锁状态
		url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url))+"&mobileno="+mobileno+"&parkPwd="+parkPwd+"&parkStatus="+parkStatus+"&machineStatus="+machineStatus+"&fusestatus="+fusestatus;
		response.sendRedirect(url);
		out.flush();
		out.close();
	}

}
