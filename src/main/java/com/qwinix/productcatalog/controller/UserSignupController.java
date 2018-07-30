package com.qwinix.productcatalog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qwinix.productcatalog.ValidationException;
import com.qwinix.productcatalog.model.UserRetVal;
import com.qwinix.productcatalog.model.UserSignUp;
import com.qwinix.productcatalog.service.UserSignupService;
//import com.qwinix.productcatalog.service.UserSignupService;

@Controller
public class UserSignupController {
	@Autowired
	UserSignupService userSignupService;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String userForm(Model model) {
		model.addAttribute("signUp", new UserSignUp());
		model.addAttribute("errors", "");
		return "usersignup";
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String userSubmit(@ModelAttribute (name="signUp") UserSignUp userSignUp, Model model) {	   
		try { 
			userSignUp = userSignupService.createUser(userSignUp);  
		} catch(Exception e) {
			model.addAttribute("errors","Error = " + e.getMessage());
			return "usersignup";
		}
		String userInfo = String.format("email = %s, password = %s, name = %s, annualincome = %s, dob= %s",
				userSignUp.getEmail(), userSignUp.getPassword(), userSignUp.getName(), userSignUp.getAnnualIncome(), userSignUp.getDateOfBirth());
		log.info(userInfo);

		model.addAttribute("user", "abc");
		return "result";

	}

	//	@GetMapping("/users")
	//	public List<UserSignUp> getAllUsers() {
	//		return  userSignupService.getAllUser();
	//	}
	//
	//	@GetMapping("/user/{email}")
	//	public UserSignUp getUserById(@PathVariable String email) {
	//		return userSignupService.findByEmail(email);
	//	}
	//	
	//	@RequestMapping(value="/signup", method=RequestMethod.GET)
	//	public String getLoginForm() {
	//		return "usersignup.html";
	//	}
	//	
	//	@PostMapping("/signup")
	//	public String signup(@ModelAttribute(name="signUp") UserSignUp usersignup, Model model ) {
	//		String email = usersignup.getEmail();
	//		String password = usersignup.getPassword();
	//		String phoneNumber = usersignup.getPhoneNumber();
	//		String name = usersignup.getName();
	//		String annualIncome = usersignup.getAnnualIncome();
	//		String dateOfBirth = usersignup.getDateOfBirth();
	//		
	//		return "welcome";
	//	}

	//	@PostMapping("/user")
	////	public  ResponseEntity <UserRetVal> createUser(@RequestBody UserSignup userDetails) {
	//	public  UserRetVal createUser(@RequestBody UserSignUp userDetails) {
	//		ResponseEntity<UserRetVal> re = null;
	//		UserRetVal retVal = new UserRetVal();
	//		try {
	//			userDetails = userSignupService.createUser(userDetails);
	//			retVal.setMessage("User Created");
	//			retVal.setStatus("true");
	//			re = ResponseEntity.ok().body(retVal);
	//		}
	//		catch(ValidationException ve) {
	//			retVal.setMessage(ve.getMessage());
	//			retVal.setStatus("false");
	//			re = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(retVal);
	//		}		
	//		return retVal;
	//	}
	//	
	//	@DeleteMapping("/user/{id}")
	//	public void deleteById(@PathVariable(value = "id") int deleteUserId) {
	//		userSignupService.deleteById(deleteUserId);
	//	}
}
