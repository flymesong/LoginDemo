package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yuepark.http.HttpClientUrl;

import net.sf.json.JSONObject;
import bean.Yuepark;
import dao.YueparkDao;
import dao.impl.YueparkDaoImpl;

public class Session_key extends HttpServlet {

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
		String code = request.getParameter("code");
		String appid="wxcc58309eeef6b660";
		String secret ="29496670e27f1b933e6157d2715255e0";
		String url ="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
		String a =HttpClientUrl.sendGet(url,null,"UTF-8");
		JSONObject jsonObject =  JSONObject.fromObject(a);
		String jsonObject12 =jsonObject.getString("unionid");
		String jsonObject1 =jsonObject.getString("openid");
//		YueparkDao yueparkDao = new YueparkDaoImpl();
//		Yuepark yuepark =yueparkDao.select(openid);
//		if(yuepark !=null){
//			phone=yuepark.getPhone();
//		}else{
//			phone="1";
//		}
//		
		JSONObject allDate = new JSONObject();
		allDate.put("unionid", jsonObject12);
		allDate.put("openid", jsonObject1);
		out.print(allDate.toString());
		
		out.flush();
		out.close();
	}

}
