package com.eeit109team6.memberDao;

import java.util.ArrayList;

public interface IMemberDao {

	public Integer add(Member m);

	public void update(Member m);

	public void delete(Member m);

	public ArrayList<Member> findAll();

	public Member findById(Member m);

	public Member login(Member m);

	public boolean openActive(Member m);

	public boolean forgetPwd(Member m);

	public Boolean changePwd(Member m);

	public Boolean changePwd(Member m, String oldpassword);

	public boolean checkAccount(Member m);

}
