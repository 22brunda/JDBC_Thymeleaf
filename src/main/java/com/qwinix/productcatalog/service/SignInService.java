package com.qwinix.productcatalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qwinix.productcatalog.model.SignIn;
import com.qwinix.productcatalog.respository.SignInRepo;


@Service
public class SignInService implements IUserService {
	@Autowired
	SignInRepo signinRepo;

	public boolean authenticateUser(SignIn signin){
		return signinRepo.authenticateUser(signin);
	}
}
