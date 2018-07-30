package com.qwinix.productcatalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.qwinix.productcatalog.ValidationException;
import com.qwinix.productcatalog.model.UserSignUp;
import com.qwinix.productcatalog.respository.UserRepo;
import com.qwinix.productcatalog.util.UserSignUpValidator;

@Service
@PropertySource("messages.properties")
public class UserSignupService {
	@Autowired
	UserRepo userSignupRepository;

	@Autowired
	Environment env;

	public List<UserSignUp> getAllUser() {
		List<UserSignUp> users = new ArrayList<>(); 
		users = userSignupRepository.getAllUsers();
		return users;
	}

	public UserSignUp findByEmail(String email) {
		return userSignupRepository.getUserByEmail(email);
	}

	public UserSignUp createUser(UserSignUp userDetails) {
		if (validate(userDetails)) {
			if(findByEmail(userDetails.getEmail()) == null) 
				userSignupRepository.createUser(userDetails);	
			else
				throw new ValidationException("Duplicate Entry, email already exists!!");
		}
		else
			return null;
		return userDetails;
	}

	public boolean validate(UserSignUp userDetails) {

		if(UserSignUpValidator.validateEmail(userDetails.getEmail())) {
			if(UserSignUpValidator.validatePassword(userDetails.getPassword())) {
				if(UserSignUpValidator.validatePhoneNum(userDetails.getPhoneNumber())) {
					if(UserSignUpValidator.validateName(userDetails.getName())) {
						if(UserSignUpValidator.validateIncome(userDetails.getAnnualIncome())) {
							if(UserSignUpValidator.validateDob(userDetails.getDateOfBirth())) {
								return true;
							} else {
								throw new ValidationException(getMessage(11));
							}
						} else {
							throw new ValidationException(getMessage(9));
						}
					} else {
						throw new ValidationException(getMessage(8));
					}
				} else {
					throw new ValidationException(getMessage(6));
				}
			} else {
				throw new ValidationException(getMessage(4));
			}
		} else {
			throw new ValidationException(getMessage(2));
		}
	}

	private String getMessage(int errorCode) {
		return env.getProperty("user." + errorCode);
	}

	public void deleteById(int deleteUserId) {
		userSignupRepository.deleteUser(deleteUserId);	
	}

}
