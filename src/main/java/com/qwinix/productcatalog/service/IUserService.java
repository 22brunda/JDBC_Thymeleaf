package com.qwinix.productcatalog.service;

import com.qwinix.productcatalog.model.SignIn;

public interface IUserService {
	public interface ICombatService {
		public abstract boolean authenticateUser(SignIn signin);
	}
}

