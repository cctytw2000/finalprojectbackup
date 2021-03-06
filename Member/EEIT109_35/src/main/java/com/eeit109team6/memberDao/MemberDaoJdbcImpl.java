package com.eeit109team6.memberDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class MemberDaoJdbcImpl implements IMemberDao {

	private Connection conn;
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public MemberDaoJdbcImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer add(Member m) {
		System.out.println("----註冊中- - - -");
		System.out.println("帳號為" + m.getAccount());
		System.out.println("類型為" + m.getType());
		System.out.println("註冊人為" + m.getUsername());
		System.out.println("註冊帳號時間為" + m.getRegisteredtime());
		Integer memberid = (Integer) sessionFactory.getCurrentSession().save(m);
		System.out.println("申請帳號成功");
		System.out.println("--------------");
		return memberid;

	}

	@Override
	public void update(Member m) {

	}

	@Override
	public void delete(Member m) {

	}

	@Override
	public ArrayList<Member> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Member");
		ArrayList<Member> member = (ArrayList<Member>) query.getResultList();
		return member;
	}

	@Override
	public Member findById(Member m) {

		Member member = (Member) sessionFactory.getCurrentSession().get(Member.class, m.getMember_id());

		return member;

	}

	@Override
	public Member login(Member m) {

		List<Member> memList = null;
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Member where account = ?1 and password = ?2 and type = ?3 and isactive = 1");
		query.setParameter(1, m.getAccount());
		query.setParameter(2, m.getPassword());
		query.setParameter(3, m.getType());
		memList = (List<Member>) query.getResultList();
		if (memList.size() != 0) {

			return memList.get(0);
		} else {
			return null;
		}

	}

	@Override
	public boolean openActive(Member m) {

		Query query = sessionFactory.getCurrentSession().createQuery("from Member where member_id = ?1 and token = ?2");
		query.setParameter(1, m.getMember_id());
		query.setParameter(2, m.getToken());

		try {
			Member mem = (Member) query.getSingleResult();
			mem.setIsactive(1);
			sessionFactory.getCurrentSession().update(mem);
			return true;
		} catch (NoResultException e) {
			System.out.println("找不到此帳號");
			return false;
		}

	}

	@Override
	public boolean forgetPwd(Member m) {

		Query query = sessionFactory.getCurrentSession().createQuery("from Member where account = ?1 and type = ?2");
		query.setParameter(1, m.getAccount());
		query.setParameter(2, m.getType());

		try {
			Member mem = (Member) query.getSingleResult();

			mem.setToken(m.getToken());
			sessionFactory.getCurrentSession().update(mem);
			return true;
		} catch (NoResultException e) {
			System.out.println("忘記密碼===找不到帳號");
			return false;
		}

	}

	@Override
	public Boolean changePwd(Member m) {

		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Member where account =?1  and token = ?2 and type= ?3");
		query.setParameter(1, m.getAccount());
		query.setParameter(2, m.getToken());
		query.setParameter(3, m.getType());

		try {
			Member memList = (Member) query.getSingleResult();
			memList.setPassword(m.getPassword());
			return true;
		} catch (NoResultException e) {
			System.out.println("沒有此帳號");
			return false;
		}

	}

	@Override
	public Boolean changePwd(Member m, String newpassword) {

		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Member where account =?1  and token = ?2 and type= ?3 and password = ?4");
		query.setParameter(1, m.getAccount());
		query.setParameter(2, m.getToken());
		query.setParameter(3, m.getType());
		query.setParameter(4, m.getPassword());

		try {
			Member memList = (Member) query.getSingleResult();
			memList.setPassword(m.getPassword());
			return true;
		} catch (NoResultException e) {
			System.out.println("沒有此帳號");
			return false;
		}

	}

	@Override
	public boolean checkAccount(Member m) {
		System.out.println("----查詢帳號是否重複------");
		System.out.println("此帳號為" + m.getType() + "帳號");

		Query query = sessionFactory.getCurrentSession().createQuery("from Member where account =?1 and type = ?2");
		query.setParameter(1, m.getAccount());
		query.setParameter(2, m.getType());

		try {
			Member mem = (Member) query.getSingleResult();
			System.out.println("帳號重複");
			System.out.println("-----------------");
			return false;
		} catch (NoResultException e) {
			System.out.println("帳號不重複");
			System.out.println("-----------------");
			return true;
		}

	}

	@Override
	public Member checkAccount(String account) {

		Query query = sessionFactory.getCurrentSession().createQuery("from Member where account =?1");
		query.setParameter(1, account);


		try {
			Member mem = (Member) query.getSingleResult();

			return mem;
		} catch (NoResultException e) {

			return null;
		}


	
	}

}
