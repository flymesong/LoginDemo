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

public class CancelOrder extends HttpServlet {

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
		String ordercode = request.getParameter("ordercode");
		String parkPwd = request.getParameter("parkPwd");
		String fusestatus = request.getParameter("fusestatus");
		String url = HttpUrl.CancelOrder_Url;//取消订单
		url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url))+"&mobileno="+mobileno+"&ordercode="+ordercode+"&parkPwd="+parkPwd+"&fusestatus="+fusestatus;
		response.sendRedirect(url);
		out.flush();
		out.close();
	}

}
