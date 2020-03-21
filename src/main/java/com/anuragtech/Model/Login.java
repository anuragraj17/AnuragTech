package com.anuragtech.Model;

import org.springframework.context.annotation.ComponentScan;

import com.anuragtech.Common.GlobalBase;

@ComponentScan
public class Login implements GlobalBase{
	
	private int uniqueIdNo;
	private String uname;
	private String emailId;
	private String password;
	private String mobileNo;
	
	public Login() {
	}
	
	public Login(int uniqueIdNo, String uname, String emailId, String password, String mobileNo) {
		this.uniqueIdNo = uniqueIdNo;
		this.uname = uname;
		this.emailId = emailId;
		this.password = password;
		this.mobileNo = mobileNo;
	}
	
	public int getUniqueIdNo() {
		return uniqueIdNo;
	}
	public void setUniqueIdNo(int uniqueIdNo) {
		this.uniqueIdNo = uniqueIdNo;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Override
	public String toString() {
		return "Login [uniqueIdNo=" + uniqueIdNo + ", uname=" + uname + ", emailId=" + emailId + ", password="
				+ password + ", mobileNo=" + mobileNo + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public Object[] getMemberValues() {
		Object[] memberValues = new Object[] {uniqueIdNo ,uname,emailId,password,mobileNo};
		return memberValues;
	}

	@Override
	public Object[] getPrimaryValues() {
		Object[] pkValues = new Object[] {uniqueIdNo};
		return pkValues;
	}
}
