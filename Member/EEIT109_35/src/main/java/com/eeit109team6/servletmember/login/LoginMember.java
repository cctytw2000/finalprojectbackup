package com.eeit109team6.servletmember.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
import com.eeit109team6.memberDao.IMemberDao;
import com.eeit109team6.memberDao.Member;
import com.eeit109team6.servletmember.CipherUtils;

@WebServlet("/LoginMember")
public class LoginMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();

		if (request.getParameter("fergetpwd") != null) {
			System.out.println("客戶忘記密碼");
			RequestDispatcher rd = request.getRequestDispatcher("/member/forgetPWD.html");
			rd.forward(request, response);
			return;
		}
		System.out.println("客戶登入帳號");
		String account = request.getParameter("loginaccount");
		String password = request.getParameter("loginpassword");
		String type = "General";

		if (account == null || account.trim().length() == 0) {
			response.getWriter().write("<script>alert('帳號必須填入');history.go(-1);</script>");
			return;
		}
		// 如果 password 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (password == null || password.trim().length() == 0) {
			response.getWriter().write("<script>alert('密碼必須填入');history.go(-1);</script>");
			return;
		}

		String key = "MickeyKittyLouis";
		String password_AES = CipherUtils.encryptString(key, password).replaceAll("[\\pP\\p{Punct}]", "").replace(" ",
				"");

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Member mem = context.getBean(Member.class);
		LiLoInfo liloinfo = context.getBean(LiLoInfo.class);
		IMemberDao MemDao = (IMemberDao) context.getBean("memberDaoJdbcImpl");
		ILiLoInforDao LiLoDao = (ILiLoInforDao) context.getBean("liLoInforDaoJdbcImpl");
		
		
		
		
		mem.setAccount(account);
		mem.setPassword(password_AES);
		mem.setType(type);

		Member member = MemDao.login(mem);

		if (member != null) {
			String logintime = (String) context.getBean("time");
			session.setAttribute("username", member.getUsername());
			session.setAttribute("token", member.getToken());
			session.setAttribute("account", member.getAccount());
			session.setAttribute("member_id", member.getMember_id());
			session.setAttribute("type", type);
			
			liloinfo.setMember(member);
			liloinfo.setLoginTime(logintime);
			liloinfo.setType("Login");
			liloinfo.setClientIP(request.getRemoteAddr());
			liloinfo.setAccountType("General");
			
			
			response.getWriter().write("<script>alert('歡迎光臨');</script>");
			System.out.println("login IP :"+request.getRemoteAddr());
			LiLoDao.add(liloinfo);
			request.setAttribute("msg", "歡迎光臨");
			RequestDispatcher rd = request.getRequestDispatcher("member/jump.jsp");
			rd.forward(request, response);
		} else {

			response.getWriter().write("<script>alert('帳號或密碼錯誤，或者未開通');history.go(-1);</script>");
		}

	}
}
