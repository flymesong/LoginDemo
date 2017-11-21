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

public class ShangBaoServlet extends HttpServlet {

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
		String getwaystate=request.getParameter("getwaystate");//网关状态1
		String channel=request.getParameter("channel");//信道1
		String voltage=request.getParameter("voltage");//电压1
		String sensitivity=request.getParameter("sensitivity");//灵敏度1
		String lockstate=request.getParameter("lockstate");//锁状态1
		String clock=request.getParameter("clock");//时钟1
		String berthstate=request.getParameter("berthstate");//车位状态1
		String mode=request.getParameter("mode");//车位锁模式1
		String deviceno=request.getParameter("deviceno");//车位锁设备号
		String fusestatus = request.getParameter("fusestatus");//保险丝状态1
		String fwver =request.getParameter("fwver");//软件/固件版本号1
		String hwver = "0";//硬件版本号
		
		String url = HttpUrl.SetStatus_Url;//控锁状态
		url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url))+"&mobileno="+mobileno+"&getwaystate="+getwaystate+"&channel="+channel+"&voltage="+voltage+"&fusestatus="+fusestatus+"&sensitivity="+sensitivity+"&lockstate="+lockstate+"&berthstate="+berthstate+"&mode="+mode+"&deviceno="+deviceno+"&fwver="+fwver+"&hwver="+hwver;
		response.sendRedirect(url);
		out.flush();
		out.close();
	}

}
