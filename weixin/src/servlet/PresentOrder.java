package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.SNSUserInfo;
import pojo.WeixinOauth2Token;
import bean.Yuepark;
import dao.YueparkDao;
import dao.impl.YueparkDaoImpl;

import net.sf.json.JSONObject;

import util.AdvancedUtil;
import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class PresentOrder extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PresentOrder() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		HttpSession session = request.getSession();
		
		String code = request.getParameter("code");
		String openId = null;
		String headImgUrl =null;
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wxfd965e088d2fdb94", "d6f0c3d327a2b5000a1a3c28b44f828d", code);			
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			// 用户标识
			openId = weixinOauth2Token.getOpenId();
			// 获取用户信息
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
			headImgUrl = snsUserInfo.getHeadImgUrl();
			
			
			// 设置要传递的参数
			session.setAttribute("snsUserInfo", snsUserInfo);
			session.setAttribute("openId", openId);
			session.setAttribute("accessToken", accessToken);
			session.setAttribute("headImgUrl", snsUserInfo.getHeadImgUrl());
//		}
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = sdf.format(date);
			YueparkDao yueparkDao = new YueparkDaoImpl();
			if(openId !=null){
			Yuepark yuepark =yueparkDao.seach(openId);
	//	Yuepark yuepark1=new Yuepark();
			if(yuepark!=null){
				String phone = yuepark.getPhone();
//				yueparkDao.update(accessToken, openId,phone);
				
				if(!"null".equals(phone)){
					session.setAttribute("mobileinfo", phone);  
				}
			}else{
				Yuepark yuepark1 = new Yuepark();
				yuepark1.setOpenid(openId);
				yuepark1.setImgurl(headImgUrl);		
	//			yuepark1.setPassword(password1);
				yuepark1.setAccesstoken(accessToken);
				yuepark1.setAddtime(time);
				yueparkDao.Addopenid(yuepark1);
			}
		}
	}
		String mobile =(String)session.getAttribute("mobileinfo");
		if(null !=mobile){
			String url = HttpUrl.CurrentOrder_Url;//当前订单
			url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
	    	Map<String, Object> map = new HashMap<String, Object>();  
	    	map.put("mobileno", mobile);
	    	String a =HttpClientUrl.sendGet(url,map,"UTF-8");
	    	System.out.println(a);
	    	JSONObject jsonObject =  JSONObject.fromObject(a);
	    	String msg = jsonObject.getString("msg");
	    	if(msg.equals("成功")){
	    	JSONObject jsonObject1 =JSONObject.fromObject(jsonObject.getString("data"));
	    	System.out.println(jsonObject1);
	    	
	    	JSONObject jsonObject2 = JSONObject.fromObject(jsonObject1.getString("parkorder"));
	    	session.setAttribute("parkorder", jsonObject2);
			System.out.println(jsonObject2);
			System.out.println("未支付金额:"+jsonObject2.getString("arrearsprice"));
			System.out.println("已支付金额:"+jsonObject2.getString("deductedprice"));
			System.out.println("停车密码:"+jsonObject2.getString("parkpwd"));
			System.out.println("是否已到场停车:--1未到场、2已到场--："+jsonObject2.getString("enterstatus"));
			System.out.println("停车订单号:"+jsonObject2.getString("bargainordercode"));
			System.out.println("泊位编号:"+jsonObject2.getString("berthcode"));
			System.out.println("停车场名称:"+jsonObject2.getString("parkingname"));
			System.out.println("停车场编号:"+jsonObject2.getString("parkingid"));
			System.out.println("地址:"+jsonObject2.getString("seationaddress"));
			System.out.println("纬度:"+jsonObject2.getString("latitude"));
			System.out.println("经度:"+jsonObject2.getString("longitude"));
			System.out.println("申请时间:"+jsonObject2.getString("addtime"));
			System.out.println("订单开始时间:"+jsonObject2.getString("startparkingtime"));
			System.out.println("订单结束时间:"+jsonObject2.getString("endparkingtime"));
			System.out.print("实际费用:"+jsonObject2.getString("amount"));
			
			System.out.println("停车时长:"+jsonObject2.getString("parkduration"));
			System.out.println("预约类型：-1立即预约、4扫码停车-:"+jsonObject2.getString("applytype"));			
			System.out.println("停车时间:"+jsonObject2.getString("parktime"));
//			response.sendRedirect("dangqiandingdan.jsp");
			request.getRequestDispatcher("dangqiandingdan.jsp").forward(request, response);
	    	}else{
	    		response.sendRedirect("login.jsp");
//	    		request.getRequestDispatcher("login.jsp").forward(request, response);
	    	}
		}else{
//			response.sendRedirect("register.jsp");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
