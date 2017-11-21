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

import net.sf.json.JSONObject;

import dao.YueparkDao;
import dao.impl.YueparkDaoImpl;

import bean.Yuepark;

import pojo.SNSUserInfo;
import pojo.WeixinOauth2Token;
import util.AdvancedUtil;
import yuepark.http.HttpClientUrl;
import yuepark.http.HttpUrl;
import yuepark.util.MathsUtil;
import yuepark.util.StringsUtil;

public class OAuthServlet extends HttpServlet {

	private static final long serialVersionUID = -1847238807216447030L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
//		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2d81ef523987ef12&redirect_uri=http%3A%2F%2Fyuebotingche.duapp.com%2FOAuthServlet&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		String openId = null;
		String headImgUrl =null;
		// 用户同意授权
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
					String url1 = HttpUrl.Getuserinfo_Url;
					url1 = MathsUtil.getEncryptionValuePair(StringsUtil.urlParse(url1));
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("mobileno", phone); 				
					String info =HttpClientUrl.sendGet(url1,map1,"UTF-8");				
					JSONObject jsonObjectinfo =  JSONObject.fromObject(info);
			    	JSONObject jsonObject1 =JSONObject.fromObject(jsonObjectinfo.getString("data"));
//			    	String mobileinfo = jsonObject1.getString("MobileNumber");//电话
			    	String overagePrice = jsonObject1.getString("OveragePrice");//余额
//					session.setAttribute("mobileinfo", mobileinfo);
					session.setAttribute("overagePrice", overagePrice);
				}
			}
			else{
				session.setAttribute("mobileinfo", null);  
			}
//			}else{
//				Yuepark yuepark1 = new Yuepark();
//				yuepark1.setOpenid(openId);
//				yuepark1.setImgurl(headImgUrl);
//	//			yuepark1.setPhone(mobileno);
//	//			yuepark1.setPassword(password1);
//				yuepark1.setAccesstoken(accessToken);
//				yuepark1.setAddtime(time);
//				yueparkDao.Addopenid(yuepark1);
//			}
		}
	}
//	request.getRequestDispatcher("register.jsp").forward(request, response);
//	response.sendRedirect("register.jsp");
		
		String mobileinfo = (String)session.getAttribute("mobileinfo");
		if(null !=mobileinfo){
//			request.getRequestDispatcher("wodezhanghu.jsp").forward(request, response);
			response.sendRedirect("wodezhanghu.jsp");
		}else{
			response.sendRedirect("login.jsp");
//			request.getRequestDispatcher("login.jsp").forward(request, response);
//			response.sendRedirect("register.jsp");
		}
//		 跳转到index.jsp
//		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	
	

}
