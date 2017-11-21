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

public class CurrentOrder extends HttpServlet {

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
		String mobileno =request.getParameter("mobileno");
		String mphone =request.getParameter("mphone");
		session.setAttribute("mobileno", mobileno);
		String url = HttpUrl.CurrentOrder_Url;
		url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobileno", mobileno);
		String a =HttpClientUrl.sendGet(url,map,"UTF-8");
		JSONObject jsonObject =  JSONObject.fromObject(a);
		String msg =jsonObject.getString("msg");
		session.setAttribute("msg", msg);
		if(msg.equals("成功")){
			JSONObject jsonObject12 =JSONObject.fromObject(jsonObject.getString("data"));
			JSONObject jsonObject13 =JSONObject.fromObject(jsonObject12.getString("parkorder"));
			String parkingname =jsonObject13.getString("parkingname");//停车场名称
			String berthcode =jsonObject13.getString("berthcode");//泊位编号
			String qrcodemsg =jsonObject13.getString("qrcodemsg");//泊位号
			String parkduration =jsonObject13.getString("parkduration");//停车时长
			String addtime =jsonObject13.getString("addtime");//停车时间
			String amount =jsonObject13.getString("amount");//当前费用
			
			session.setAttribute("parkingname", parkingname);
			session.setAttribute("berthcode", berthcode);
			session.setAttribute("qrcodemsg", qrcodemsg);
			session.setAttribute("parkduration", parkduration);
			session.setAttribute("addtime", addtime);
			session.setAttribute("amount", amount);
			
		}
		if(mphone==null){
			response.sendRedirect("dangqiandingdan.jsp");
		}
//		JSONObject allDate = new JSONObject();
//		allDate.put("parkingname", parkingname);
//		allDate.put("berthcode", berthcode);
////		allDate.put("qrcodemsg", qrcodemsg);
//		allDate.put("addtime", addtime);
//		allDate.put("parkduration", parkduration);
//		allDate.put("amount", amount);
//		out.print(allDate.toString());
		out.flush();
		out.close();
	}

}
