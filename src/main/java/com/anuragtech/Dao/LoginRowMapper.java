package com.anuragtech.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.anuragtech.Model.Login;

public class LoginRowMapper implements RowMapper<Login> {

	
	@Override
	public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
		Login login = new Login();
		login.setUniqueIdNo(rs.getInt("UNIQUE_ID_NO"));
		login.setUname(rs.getString("UNAME"));
		login.setEmailId(rs.getString("EMAIL_ID"));
		login.setPassword(rs.getString("PASSWORD"));
		login.setMobileNo(rs.getString("MOBILE_NO"));
		return login;
	}

}
