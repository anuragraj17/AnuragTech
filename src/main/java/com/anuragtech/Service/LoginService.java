package com.anuragtech.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anuragtech.Dao.LoginDaoImpl;
import com.anuragtech.Model.Login;



@Service
public class LoginService {
	
	@Autowired
	private LoginDaoImpl loginDao;
	public boolean saveUserData (Login login) {
		int success = loginDao.insert(login);
		if (success < 1) {
			return false;
		} else {
			return true;
		}
		
	}
	public Login getUserData(Login login) {
		Login user = loginDao.selectUser(login);
		return user;
	}
}
