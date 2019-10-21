package com.eeit109team6.servletmember.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eeit109team6.LiLoInfoDao.ILiLoInforDao;
import com.eeit109team6.LiLoInfoDao.LiLoInfo;
import com.eeit109team6.memberDao.Member;

/**
 * Servlet implementation class LogOutMember
 */
@WebServlet("/LogOutMember")
public class LogOutMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		LiLoInfo liloinfo = context.getBean(LiLoInfo.class);
		ILiLoInforDao LiLoDao = (ILiLoInforDao) context.getBean("liLoInforDaoJdbcImpl");

		HttpSession session = request.getSession(false);
		Member mem = (Member) session.getAttribute("mem");

		String logouttime = (String) context.getBean("time");
		if (mem != null) {

			liloinfo.setMember(mem);
			liloinfo.setLoginTime(logouttime);
			liloinfo.setType("logout");
			liloinfo.setClientIP(request.getRemoteAddr());
			liloinfo.setAccountType("General");
			liloinfo.setIsSuccess(1);

			LiLoDao.add(liloinfo);

			System.out.println("會員登出");
			session.invalidate();
			response.getWriter().write("<script>alert('已登出');</script>");
			response.sendRedirect(getServletContext().getContextPath() + "/home.jsp");
		} else {
			System.out.println("會員登出但從沒登入過");
			response.getWriter().write("<script>alert('您尚未登入');");
			response.sendRedirect(getServletContext().getContextPath() + "/home.jsp");
		}
	}

}
