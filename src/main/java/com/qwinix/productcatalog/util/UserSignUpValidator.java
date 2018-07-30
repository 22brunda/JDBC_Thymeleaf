package com.qwinix.productcatalog.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSignUpValidator {
	//Email_Validation
	public static boolean validateEmail(String email) {
		return email.matches("^\\w+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{3}$");
	}
	
	//Password_Validation
	public static boolean validatePassword(String password) {
		if(password.length() <= 0) {
			throw new RuntimeException("Password is Required");
		}else {
			String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&]).{8,}";
			Pattern pattern;
			Matcher matcher;
			pattern = Pattern.compile(passwordRegex);
			matcher = pattern.matcher(password);
			return matcher.matches();
		}
	}

	//PhoneNumber_Validation	
	public static boolean validatePhoneNum(String phoneNum) {
		if(phoneNum.length() <= 0) {
			throw new RuntimeException("Phone Number is Required");
		}else {
			String phonenumRegex = "^[0-9]{10}$";
			Pattern pattern;
			Matcher matcher;
			pattern = Pattern.compile(phonenumRegex);
			matcher = pattern.matcher(phoneNum);
			return matcher.matches();
		}	
	}

	//Name_Validation
	public static boolean validateName(String name) {
		if(name.length() <= 0) {
			throw new RuntimeException("Name is Required");
		}else {
			String nameRegex = "^[A-z]+$";
			Pattern pattern;
			Matcher matcher;
			pattern = Pattern.compile(nameRegex);
			matcher = pattern.matcher(name);
			return matcher.matches();
		}		
	}

	//Annual_Income_Validation
	public static boolean validateIncome(String income) {
		if(income.length() <= 0) {
			throw new RuntimeException("Name is Required");
		}else {
			String incomeRegex = "^[0-9]+$";
			Pattern pattern;
			Matcher matcher;
			pattern = Pattern.compile(incomeRegex);
			matcher = pattern.matcher(income);
			return matcher.matches();
		}		
	}

	//Date_Of_Birth_Validation
	public static boolean validateDob(String dob) {
		if(dob.length()<=0){
			return false;
		}else {
			LocalDate dateOfBirth = LocalDate.parse(dob, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
			LocalDate now = LocalDate.now();
			if((now.getYear() - dateOfBirth.getYear()) < 18) {
				return false;
			}
			else
				return true; 
		}
	}
}
