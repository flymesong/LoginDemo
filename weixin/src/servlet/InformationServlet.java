package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.jfinal.kit.HttpKit;


public class InformationServlet extends HttpServlet {

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
		String appid="wx2d81ef523987ef12";
		String secret ="0cda0f120f1f2ad2c05440a469e9da8a";
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
		String xmlStr = HttpKit.get(url);
		JSONObject jsonObject =  JSONObject.fromObject(xmlStr);
		String access_token =jsonObject.getString("access_token");
		String openid="oN-T2wzVX7kjCgmtZK9lkYJURpmc";
		String url1 ="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;//推送消息请求url post请求
		
		String json = "{\"touser\":\""+openid+"\",\"template_id\":\"1L2ZCIftGEicLEzAsqp8UywaoNFXgh4DzqqwYuQWZ-g\",\"url\":\"http://yuepark.applinzi.com\"" +//template_id-模板ID：eKXAn158l_eXcSg-QYdr0UpbKInqLhvL4lyefZabMjA;跳转页面url:template_id:http://yuepark.applinzi.com
//				",\"miniprogram\":{\"appid\":\"wxcc58309eeef6b660\",\"pagepath\":\"pages/presentorder/presentorder\"}" +//跳转小程序的，可有可无
				",\"data\":{\"first\":{\"value\":\"您已经结束订单\",\"color\":\"#173177\"}" +//模板标题
				",\"keyword1\":{\"value\":\"00000000021\",\"color\":\"#173177\"}" +//模板内容
				",\"keyword2\":{\"value\":\"测试停车场2.0\",\"color\":\"#173177\"}" +//模板内容
				",\"keyword3\":{\"value\":\"2017-10-10 10:40:23\",\"color\":\"#173177\"}" +//模板内容
//				",\"keyword4\":{\"value\":\"10分钟\",\"color\":\"#173177\"}" +//模板内容
//				",\"keyword5\":{\"value\":\"5元\",\"color\":\"#173177\"}" +//模板内容
				",\"remark\":{\"value\":\"欢迎再次使用！\",\"color\":\"#FF0000\"}}}";     //模板内容
		String xmlStr1 = HttpKit.post(url1,json);//HttpKit --- import com.jfinal.kit.HttpKit;这个包
		JSONObject allDate = new JSONObject();
		allDate.put("xmlStr", xmlStr1);
		out.print(allDate.toString());
		out.flush();
		out.close();
	}

}
