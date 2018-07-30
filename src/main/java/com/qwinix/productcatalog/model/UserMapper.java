package com.qwinix.productcatalog.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<UserSignUp> {

	public UserSignUp mapRow(ResultSet resultSet, int i) throws SQLException {

		UserSignUp user = new UserSignUp();
		user.setUser_id(resultSet.getInt("user_id"));
		user.setName(resultSet.getString("name"));
		user.setPassword(resultSet.getString("password"));
		user.setAnnualIncome(resultSet.getString("annual_income"));
		user.setEmail(resultSet.getString("email"));
		user.setPhoneNumber(resultSet.getString("phone_number"));
		user.setDateOfBirth(resultSet.getString("date_of_birth"));
		return user;
	}
}