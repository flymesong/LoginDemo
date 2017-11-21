package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.kit.HttpKit;

import net.sf.json.JSONObject;

import pojo.SNSUserInfo;
import pojo.WeixinOauth2Token;
import util.AdvancedUtil;
import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class InformationService extends HttpServlet {

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
		String  access_token= request.getParameter("access_token");
		String openid = request.getParameter("openid");
//		String text= request.getParameter("text");
		String text="Hello World!!!!!";
		

        
        
        
		String url ="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+access_token;
//		Map<String, Object> textmap = new HashMap<String, Object>();
//		textmap.put("content", "Hello World");
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("touser", openid); 
//		map.put("msgtype", "text");
//		map.put("text", textmap);
//		String a =HttpClientUrl.sendGet(url,map,"UTF-8");
//		String json = "{\"touser\":\""+openid+"\",\"msgtype\":\"text\",\"text\":{\"content\":\""+text+"\"}}";
		String json = "{\"industry_id1\":\"25\",\"industry_id2\":\"1\"}";
		String xmlStr = HttpKit.post(url,json);
		JSONObject allDate = new JSONObject();
		allDate.put("xmlStr", xmlStr);
		out.print(allDate.toString());
		out.flush();
		out.close();
	}

}
