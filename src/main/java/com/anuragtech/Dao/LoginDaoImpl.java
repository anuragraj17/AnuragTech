package com.anuragtech.Dao;

import java.sql.Types;
import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.jdbc.core.JdbcTemplate;

import com.anuragtech.Common.GlobalDao;
import com.anuragtech.Model.Login;


@ManagedBean
public class LoginDaoImpl extends GlobalDao<Login> implements LoginDao  {
	
	public LoginDaoImpl() {
		super();
		initializeVars();
	}
	

	public LoginDaoImpl(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		initializeVars();
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return super.getJdbcTemplate();
	}
	
	public void setJdbcTemplate(JdbcTemplate pJdbcTemplate) {
		super.setJdbcTemplate(pJdbcTemplate);
	}
	
	public void initializeVars() {
		rowMapper = new LoginRowMapper();
		noOfCols = 5; 
		tableName = "User";
		
		columnNames = new String[] {
			"UNIQUE_ID_NO","UNAME", "EMAIL_ID", "PASSWORD" ,"MOBILE_NO"
		};
		
		columnTypes= new int[] {
			Types.INTEGER,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR,
		};
		pkColumnNames = new String[] {"UNIQUE_ID_NO"};
		pkColumnTypes = new int[] {Types.INTEGER};
		
	}
	
	public int insert(Login login) {
		return super.insert(login);
	}


	@SuppressWarnings("unchecked")
	@Override
	public Login selectUser(Login login) {
		StringBuilder sql = new StringBuilder().append("Select * from ").append(tableName).append(" WHERE")
				.append(" EMAIL_ID = ?").append(" AND ").append(" PASSWORD = ?");
		
		Object[] paramValues = new Object [] {login.getEmailId() ,login.getPassword()};
		int[] paramTypes = new int[] { Types.VARCHAR, Types.VARCHAR};
		
		List<?> userList = getJdbcTemplate().query(sql.toString(), paramValues, paramTypes,rowMapper);
		
		return !userList.isEmpty() ? (Login) userList.get(0) : null;
	}

}
