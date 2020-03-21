/**
 * 
 */
package com.anuragtech.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anuragtech.Model.Login;
import com.anuragtech.Service.LoginService;
import com.google.gson.JsonArray;

/**
 * @author Anuragsriv
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders="*")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	/*@RequestMapping(method =RequestMethod.POST, value = "/home")
	public Login login(@RequestBody Login login) {
		return loginService.getUserData(login);
	}*/
	@RequestMapping(method =RequestMethod.POST, value = "/home")
	public String login(@RequestBody Login login) {
		login = loginService.getUserData(login);
		JsonArray jsonArray = new JsonArray();
		jsonArray.add(login.getEmailId());
		jsonArray.add(login.getPassword());
		return jsonArray.toString();
	}
	
	@RequestMapping(method =RequestMethod.POST, value = "/register")
	public boolean registerUser(@RequestBody Login login){
		boolean isUserCreated = loginService.saveUserData(login);
		return isUserCreated;
	}
	
}
