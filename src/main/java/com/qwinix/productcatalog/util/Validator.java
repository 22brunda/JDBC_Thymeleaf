package com.qwinix.productcatalog.util;

public class Validator {
	public static final boolean validateLength(String val, int length) {
		if(val.length()>length) {
			throw new RuntimeException("Field should not have more than " + length + " digits!!");
		}else {
			return true;
		}
	}  

	public static boolean validateText(String aname, int min, int max) {
		if(aname.length()<min || aname.length()>max) {
			System.out.println(aname);
			throw new RuntimeException("Field length should be between " + min + " and " 
					+ max + " characters!!");
		}
		return true;
	}
}

