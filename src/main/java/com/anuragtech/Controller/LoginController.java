/**
 * 
 */
package com.anuragtech.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping(value = "/home")
	public ResponseEntity<Login> login(@RequestBody Login login) throws Exception {
		login = loginService.getUserData(login);
		return new ResponseEntity<Login>(login, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(method =RequestMethod.POST, value = "/register")
	public boolean registerUser(@RequestBody Login login){
		boolean isUserCreated = loginService.saveUserData(login);
		return isUserCreated;
	}
	
}
