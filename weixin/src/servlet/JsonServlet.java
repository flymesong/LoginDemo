package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Yuepark;

import dao.YueparkDao;
import dao.impl.YueparkDaoImpl;

import yuepark.http.HttpUrl;

public class JsonServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public JsonServlet() {
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

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
//		ServletContext application=this.getServletContext(); 
		String code =request.getParameter("aa");
		String aas="密码错误";
		if(code == ""){
			response.sendRedirect("close.jsp?aa=1");
		}else{
			response.sendRedirect("index.jsp?aa=1");
		}
//		String asds = "124";
//		application.setAttribute("name", "123");
//		application.setAttribute("pwd", "123qw");
//		out.println("<script language='javascript'>alert('"+aas+"');</script>"); 
//		request.getRequestDispatcher("index.jsp?a="+aas).forward(request, response);
//		String as =request.getParameter("code");
//		HttpSession session = request.getSession();
//		session.setAttribute("one", as);
//		YueparkDao yueparkDao = new YueparkDaoImpl();
//		Yuepark yuepark =yueparkDao.seach("rew");
//		String phone = yuepark.getPhone();
//		
//		String ks=(String)session.getAttribute("one");
//		if(null !=yuepark){
			
//		}else{
//			response.sendRedirect("register.jsp");
//		}
		
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

		this.doGet(request, response);
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
