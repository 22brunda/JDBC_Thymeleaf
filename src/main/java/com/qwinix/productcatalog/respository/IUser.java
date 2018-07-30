package com.qwinix.productcatalog.respository;

import java.util.List;

import com.qwinix.productcatalog.model.UserSignUp;

public interface IUser {
	UserSignUp getUserByEmail(String email);

	List<UserSignUp> getAllUsers();

	boolean deleteUser(int userId);

	boolean createUser(UserSignUp user);
}
