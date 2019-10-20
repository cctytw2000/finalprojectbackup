package com.eeit109team6.servletmember.registered;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Calendar;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eeit109team6.memberDao.IMemberDao;
import com.eeit109team6.memberDao.Member;
import com.eeit109team6.memberDetailDao.IMemberDetailDao;
import com.eeit109team6.memberDetailDao.MemberDetail;

@WebServlet("/InsertMemberImfo")
public class InsertMemberImfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String memberID = request.getParameter("memberID");
		String token = request.getParameter("token");

		String account = request.getParameter("account");
		String username = request.getParameter("username");
		String idnumber = request.getParameter("idnumber");
		String alladdress = request.getParameter("alladdress");
		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");

		if (memberID != null && token != null) {
			System.out.println("一般帳號");
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			Member mem = context.getBean(Member.class);
			IMemberDao MemDao = (IMemberDao) context.getBean("memberDaoJdbcImpl");

			IMemberDetailDao MDDao = (IMemberDetailDao) context.getBean("memberDetailDaoJdbcImpl");

			MemberDetail md = context.getBean(MemberDetail.class);
			mem.setMember_id(Integer.valueOf(memberID));
			mem.setToken(token);
			md.setAddress(alladdress);
			md.setBirth(birth);
			md.setIdnumber(idnumber);
			md.setSex(sex);

			Member member = MemDao.findById(mem);
			md.setMember(member);

			MDDao.add(md);
			MemDao.openActive(member);
			request.setAttribute("msg", "已開通帳號請至登入頁面登入");
			RequestDispatcher rd = request.getRequestDispatcher("member/jump.jsp");
			rd.forward(request, response);

		} else {
			System.out.println("第三方帳號");
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
		
			
			
			
			IMemberDetailDao MDDao = (IMemberDetailDao) context.getBean("memberDetailDaoJdbcImpl");
			IMemberDao MemDao = (IMemberDao) context.getBean("memberDaoJdbcImpl");
			MemberDetail md = context.getBean(MemberDetail.class);
			Member mem = context.getBean(Member.class);

			mem.setMember_id(Integer.valueOf(memberID));
			Member member = MemDao.findById(mem);

			md.setAddress(alladdress);
			md.setBirth(birth);
			md.setIdnumber(idnumber);
			md.setSex(sex);
	
			md.setMember(member);

			MDDao.add(md);

			MemDao.openActive(member);

			request.setAttribute("msg", "已完成輸入會員資料及開通帳號");
			RequestDispatcher rd = request.getRequestDispatcher("member/jump.jsp");
			rd.forward(request, response);

		}
	}

}
