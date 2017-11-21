package filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BrowserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsrequest = (HttpServletRequest)request;
		HttpServletResponse hsresponse = (HttpServletResponse)response;
		HttpSession session = hsrequest.getSession();
		String mobileinfo = (String)session.getAttribute("mobileinfo");
		if(null != mobileinfo){
			chain.doFilter(request, response);//传递给下一个过滤器
		}else{
			hsresponse.sendRedirect("register.jsp");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
