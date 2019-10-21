package com.eeit109team6.LiLoInfoDao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eeit109team6.memberDao.Member;

@Component(value = "liLoInfo")
@Scope(value = "prototype")
@Entity
@Table(name = "LiLoInfo")
public class LiLoInfo {
	private Integer memberID;
	private String type;
	private String ClientIP;
	private Member member;
	private String loginTime ;
	private String accountType;
	private Integer isSuccess;
	

	@Id
	@Column(name = "MEMBERID")
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "member"))
	@GeneratedValue(generator = "generator")
	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}



	

	@PrimaryKeyJoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getClientIP() {
		return ClientIP;
	}

	public void setClientIP(String clientIP) {
		ClientIP = clientIP;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Integer getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}

}
