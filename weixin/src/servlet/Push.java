package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yuepark.http.HttpClientUrl;
import yuepark.util.Access_tokenUtil;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;
import bean.AccessToken;
import bean.Yuepark;

import com.jfinal.kit.HttpKit;

import dao.AccessTokenDao;
import dao.YueparkDao;
import dao.impl.AccessTokenDaoImpl;
import dao.impl.YueparkDaoImpl;
import net.sf.json.JSONObject;

public class Push extends HttpServlet {

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
		String type = request.getParameter("type");
		JSONObject allDate = new JSONObject();
		if(type==null){
			allDate.put("",null);
		}else
		if(type.equals("1")){
			String phone = request.getParameter("phone");//手机号
			String parkingname = request.getParameter("parkingname");//停车场名称
			String berthcode = request.getParameter("berthcode");//泊位编号
			String qrcodemsg = request.getParameter("qrcodemsg");//泊位号
			String parktime = request.getParameter("parktime");//停车时间	
			String applytype = request.getParameter("applytype");//1预约 4 扫码
			
			if(applytype.equals("4")){
				YueparkDao yueparkDao = new YueparkDaoImpl();
				Yuepark yuepark=yueparkDao.seachPhone(phone);
				if(yuepark !=null){
					String openid=yuepark.getOpenid();
					System.out.println(openid);

					String accesstoken = Access_tokenUtil.getToken();//获取access_token
					String url1 ="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accesstoken;
					
					String json = "{\"touser\":\""+openid+"\",\"template_id\":\"m9dt_tosOhXoqfJH9b8P1UZEA37llrK9JbrPXq-pasE\",\"url\":\"https://yuepark.applinzi.com/CurrentOrder?mobileno="+phone+"\"" +
							",\"miniprogram\":{\"appid\":\"wxcc58309eeef6b660\",\"pagepath\":\"pages/presentorder/presentorder\"}" +
							",\"data\":{\"first\":{\"value\":\"您的订单已生成。\",\"color\":\"#173177\"}" +
							",\"keyword1\":{\"value\":\""+parkingname+"\",\"color\":\"#173177\"}" +
							",\"keyword2\":{\"value\":\""+berthcode+"\",\"color\":\"#173177\"}" +
							",\"keyword3\":{\"value\":\""+qrcodemsg+"\",\"color\":\"#173177\"}" +
							",\"keyword4\":{\"value\":\""+parktime+"\",\"color\":\"#173177\"}" +
							",\"remark\":{\"value\":\"\",\"color\":\"#FF0000\"}}}";     
					String xmlStr = HttpKit.post(url1,json);
					allDate.put("xmlStr", xmlStr);
				}	
				
				allDate.put("phone", phone);
				allDate.put("berthcode", berthcode);
				allDate.put("parktime", parktime);
				allDate.put("qrcodemsg", qrcodemsg);
				allDate.put("parkingname", parkingname);
			}
			allDate.put("phone", phone);
			allDate.put("berthcode", berthcode);
			allDate.put("parktime", parktime);
			allDate.put("qrcodemsg", qrcodemsg);
			allDate.put("parkingname", parkingname);
			
		}else if(type.equals("2")){
			String phone = request.getParameter("phone");//手机号
			String bargainordercode = request.getParameter("bargainordercode");//停车订单号
			String parkingname = request.getParameter("parkingname");//停车场名称
			String parktime = request.getParameter("parktime");//离场时间
			String parkduration = request.getParameter("parkduration");//停车时长
			String amount = request.getParameter("amount");//消费金额
			String applytype = request.getParameter("applytype");//1预约 4 扫码
			
			if(applytype.equals("4")){
				YueparkDao yueparkDao = new YueparkDaoImpl();
				Yuepark yuepark=yueparkDao.seachPhone(phone);
				if(yuepark !=null){
					String openid=yuepark.getOpenid();
					System.out.println(openid);

					String accesstoken = Access_tokenUtil.getToken();//获取access_token
					String url1 ="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accesstoken;
	
					String json = "{\"touser\":\""+openid+"\",\"template_id\":\"eKXAn158l_eXcSg-QYdr0UpbKInqLhvL4lyefZabMjA\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfd965e088d2fdb94&redirect_uri=http://yuepark.applinzi.com/OrderServlet&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect\"" +
	//						",\"miniprogram\":{\"appid\":\"wxcc58309eeef6b660\",\"pagepath\":\"pages/presentorder/presentorder\"}" +
							",\"data\":{\"first\":{\"value\":\"您已经结束订单\",\"color\":\"#173177\"}" +
							",\"keyword1\":{\"value\":\""+bargainordercode+"\",\"color\":\"#173177\"}" +
							",\"keyword2\":{\"value\":\""+parkingname+"\",\"color\":\"#173177\"}" +
							",\"keyword3\":{\"value\":\""+parktime+"\",\"color\":\"#173177\"}" +
							",\"keyword4\":{\"value\":\""+parkduration+"分钟\",\"color\":\"#173177\"}" +
							",\"keyword5\":{\"value\":\""+amount+"元\",\"color\":\"#173177\"}" +
							",\"remark\":{\"value\":\"欢迎再次使用！\",\"color\":\"#FF0000\"}}}"; 
					String xmlStr = HttpKit.post(url1,json);
					allDate.put("xmlStr", xmlStr);
				}
				
				
				
				allDate.put("phone", phone);
				allDate.put("bargainordercode", bargainordercode);
				allDate.put("parkingname", parkingname);
				allDate.put("parktime", parktime);
				allDate.put("parkduration", parkduration);
				allDate.put("amount", amount);
			}
		}else if(type.equals("3")){
			String phone = request.getParameter("phone");//手机号
			String amount = request.getParameter("amount");//消费金额
			String arrearsprice = request.getParameter("arrearsprice");//欠费金额
			
			allDate.put("phone", phone);
			allDate.put("arrearsprice", arrearsprice);
			allDate.put("amount", amount);
		}else{
			allDate.put("",null);
		}
		
		
		
		
		
		out.print(allDate.toString());
		out.flush();
		out.close();
	}

}
