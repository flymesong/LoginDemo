package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Yuepark;
import dao.YueparkDao;
import dao.impl.YueparkDaoImpl;

import pojo.SNSUserInfo;
import pojo.WeixinOauth2Token;
import util.AdvancedUtil;

public class OeauthServlet extends HttpServlet {

	private static final long serialVersionUID = -1847238807216447030L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");

		// 用户同意授权
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wxfd965e088d2fdb94", "d6f0c3d327a2b5000a1a3c28b44f828d", code);
			// 网页授权接口访问凭证
//			String accessToken1 = weixinOauth2Token1.getAccessToken();
			//刷新网页授权refreshToken
//			WeixinOauth2Token weixinOauth2Token=AdvancedUtil.refreshOauth2AccessToken("wx2d81ef523987ef12", accessToken1);
			
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
			// 获取用户信息
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
			// 设置要传递的参数
			session.setAttribute("snsUserInfo", snsUserInfo);
			session.setAttribute("openId", openId);
			session.setAttribute("accessToken", accessToken);
			session.setAttribute("headImgUrl", snsUserInfo.getHeadImgUrl());
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = sdf.format(date);
			YueparkDao yueparkDao = new YueparkDaoImpl();
			Yuepark yuepark=yueparkDao.seach(openId);
			if(null !=yuepark){
				String phone = yuepark.getPhone();
//				yueparkDao.update(accessToken, openId,phone);
				
				if(!"null".equals(phone)){
					session.setAttribute("mobileinfo", phone);  
				}
//			}else{
//				Yuepark yuepark1 = new Yuepark();
//				yuepark1.setOpenid(openId);
//				yuepark1.setImgurl(snsUserInfo.getHeadImgUrl());
////				yuepark1.setPhone(mobileno);
////				yuepark1.setPassword(pwd1);
//				yuepark1.setAccesstoken(accessToken);
//				yuepark1.setAddtime(time);
//				yueparkDao.Addopenid(yuepark1);
			}
			else{
				session.setAttribute("mobileinfo", null);  
			}
		}
		String mobileinfo = (String)session.getAttribute("mobileinfo");
		if(null !=mobileinfo){
//			request.getRequestDispatcher("zhanghuchongzhi1.jsp").forward(request, response);
			response.sendRedirect("zhanghuchongzhi1.jsp");
		}else{
			response.sendRedirect("login.jsp");
//			request.getRequestDispatcher("login.jsp").forward(request, response);
//			response.sendRedirect("register.jsp");
		}
		// 跳转到index.jsp
//		request.getRequestDispatcher("wodezhanghu.jsp").forward(request, response);
//		response.sendRedirect("wodezhanghu.jsp");
	}

}
