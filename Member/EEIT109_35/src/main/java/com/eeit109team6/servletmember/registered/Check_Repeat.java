package com.eeit109team6.servletmember.registered;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eeit109team6.memberDao.IMemberDao;
import com.eeit109team6.memberDao.Member;

@WebServlet("/Check_Repeat")
public class Check_Repeat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		String account = request.getParameter("account");
		String type = request.getParameter("type");

		if (type == null) {
			type = "General";
		}

		System.out.println("帳號:" + account);
		System.out.println("申請帳號種類為:" + type);
		IMemberDao MEMDaoF = null;
		Member m = context.getBean(Member.class);
		m.setAccount(account);
		m.setType(type);

		MEMDaoF = (IMemberDao) context.getBean("memberDaoJdbcImpl");

		String check_answ = Boolean.toString(MEMDaoF.checkAccount(m));
		PrintWriter out = response.getWriter();
//		System.out.println("Check 結果為" + check_answ + "不重複可以申請該帳號");
		out.write(check_answ);
		out.close();

	}

}
