package com.qwinix.productcatalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qwinix.productcatalog.ValidationException;
import com.qwinix.productcatalog.model.SignIn;
import com.qwinix.productcatalog.model.UserSignUp;
import com.qwinix.productcatalog.service.SignInService;

@Controller
public class SignInController {

	@Autowired
	SignInService signinService;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String userForm(Model model) {
		model.addAttribute("signin", new UserSignUp());
		model.addAttribute("errors", "");
		model.addAttribute("usernameErrorColor", "background-color:white");
		model.addAttribute("passwordErrorColor", "background-color:white");
		model.addAttribute("btn", "btn");
		return "signin";
	}

	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public String signin(@ModelAttribute(name="signIn") SignIn userSignIn, BindingResult result, Model model){
		if(!result.hasFieldErrors()) {
			try {
				if(signinService.authenticateUser(userSignIn)) {
					return "home";
				} 
			} catch(ValidationException e) {
				model.addAttribute("errors","Error = " + e.getMessage());
				if(e.getErrorSource().equals("username")) {
					model.addAttribute("usernameErrorColor", "background-color:red");
					model.addAttribute("passwordErrorColor", "background-color:white");
					model.addAttribute("btn", "btn-danger");
				}
				else if(e.getErrorSource().equals("password")) {
					model.addAttribute("usernameErrorColor", "background-color:white");
					model.addAttribute("passwordErrorColor", "background-color:red");
				}
				model.addAttribute("btn", "btn-danger");
				return "signin";
			}
		}
		model.addAttribute("errors","Error in binding");
		return "signin";
	}			
}
