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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import util.AdvancedUtil;
import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class OrderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OrderServlet() {
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
//			}else{
//				Yuepark yuepark1 = new Yuepark();
//				yuepark1.setOpenid(openId);
//				yuepark1.setImgurl(headImgUrl);		
//	//			yuepark1.setPassword(password1);
//				yuepark1.setAccesstoken(accessToken);
//				yuepark1.setAddtime(time);
//				yueparkDao.Addopenid(yuepark1);
			}
			else{
				session.setAttribute("mobileinfo", null);  
			}
		}
	}
		
		String mobileinfo = (String)session.getAttribute("mobileinfo");
		if(null != mobileinfo){
			String url = HttpUrl.GetOrderList_Url;//订单列表
			url = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mobileno", mobileinfo);
			String a =HttpClientUrl.sendGet(url,map,"UTF-8");
	    	System.out.println(a);
	    	JSONObject jsonObject =  JSONObject.fromObject(a);
	    	JSONObject jsonObject1 =JSONObject.fromObject(jsonObject.getString("data"));
//	    	System.out.println(jsonObject1);  
	    	int count = jsonObject1.getInt("count");
	    	session.setAttribute("count", count);
	    	System.out.println(count);
	    	if(count>0){
	    		JSONArray jsonarray = JSONArray.fromObject(jsonObject1.getJSONArray("items"));
	    		session.setAttribute("jsonarray", jsonarray);
	    		System.out.println(jsonarray);
	    		if(jsonarray.size()>0){
	    			for(int i =0;i<jsonarray.size();i++){
	    				JSONObject job = jsonarray.getJSONObject(i);
	    				
	    				System.out.println("------------------------------------------------------------------------------");
	    				System.out.println("未支付金额:"+job.get("arrearsprice"));
	    				System.out.println("已支付金额:"+job.get("deductedprice"));
	    				System.out.println("是否已到场停车:-1未到场、2已到场-："+job.get("enterstatus"));
	    				System.out.println("停车订单号:"+job.get("bargainordercode"));
	    				System.out.println("泊位编号:"+job.get("berthcode"));
	    				System.out.println("停车场名称:"+job.get("parkingname"));
	    				System.out.println("申请时间:"+job.get("addtime"));
	    				System.out.println("订单开始时间:"+job.get("startparkingtime"));
	    				System.out.println("订单结束时间:"+job.get("endparkingtime"));
	    				System.out.println("当前费用:"+job.get("amount"));
	    				System.out.println("取消时间:"+job.get("canceltime"));
	    				System.out.println("停车时长:"+job.get("parkduration"));
	    				System.out.println("预约类型：-1立即预约、4扫码停车-:"+job.get("applytype"));
	    				System.out.println("受理时间:"+job.get("applytime"));
	    				System.out.println("订单状态:-1已完成、2已取消、3欠费-："+job.get("orderstatus"));
	    				System.out.println("停车时间:"+job.get("parktime"));
	    				System.out.println("------------------------------------------------------------------------------");
	    				
	    			}
	    		}
	    		response.sendRedirect("dingdan.jsp");
//	    		request.getRequestDispatcher("dingdan.jsp").forward(request, response);//订单有数据
	    		
	    	}else{
			response.sendRedirect("dingdan.jsp");
//			request.getRequestDispatcher("dingdan.jsp").forward(request, response);//订单没有数据
	    	}
		}else{
//			request.getRequestDispatcher("login.jsp").forward(request, response);
			response.sendRedirect("login.jsp");
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
