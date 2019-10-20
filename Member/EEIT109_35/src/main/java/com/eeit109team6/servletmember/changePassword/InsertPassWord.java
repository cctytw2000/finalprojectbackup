package com.eeit109team6.servletmember.changePassword;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eeit109team6.memberDao.IMemberDao;
import com.eeit109team6.memberDao.Member;

@WebServlet("/InsertPassWord")
public class InsertPassWord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Member mem = context.getBean(Member.class);
		IMemberDao memdao = context.getBean(IMemberDao.class);
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String account = request.getParameter("account");
		String token = request.getParameter("token");

		mem.setAccount(account);
		mem.setPassword(oldpassword);
		mem.setToken(token);
		mem.setPassword(oldpassword);

		Boolean isDeal = memdao.changePwd(mem, newpassword);

		if (isDeal) {
			System.out.println(mem.getAccount() + ":修改密碼成功");
		} else {

			System.out.println(mem.getAccount() + ":修改密碼失敗");
		}

	}

}
