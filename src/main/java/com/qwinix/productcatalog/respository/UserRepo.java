package com.qwinix.productcatalog.respository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.qwinix.productcatalog.ValidationException;
import com.qwinix.productcatalog.model.UserMapper;
import com.qwinix.productcatalog.model.UserSignUp;

@Component
public class UserRepo implements IUser{

	@Autowired
	JdbcTemplate jdbcTemplate;

	private final String SQL_GET_ALL_USERS = "select * from user" ;
	private final String SQL_INSERT_USER = "insert into user(user_id, email, password, phone_number, name, annual_income, date_of_birth) values(?,?,?,?,?,?,?)";
	private final String SQL_FIND_USER = "select * from user where email = ?";
	private final String SQL_DELETE_USER = "delete from user where user_id = ?";

	@Autowired
	public UserRepo(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<UserSignUp> getAllUsers() {
		List<UserSignUp> users = jdbcTemplate.query(SQL_GET_ALL_USERS, new UserMapper());
		//		for(UserSignUp user: users )
		return users;
	}

	public UserSignUp getUserByEmail(String email) {
		List <UserSignUp> users = jdbcTemplate.query(SQL_FIND_USER,new Object[] {email}, 
				new UserMapper());
		if(users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}

	public boolean createUser(UserSignUp userCreate) {
		Integer id = jdbcTemplate.queryForObject("select id from seq_table where entity=?", 
				new Object[] {"user"}, Integer.class);
		id += 1;
		userCreate.setUser_id(id);

		int rowsAffected = jdbcTemplate.update(SQL_INSERT_USER, userCreate.getUser_id(),
				userCreate.getEmail(), userCreate.getPassword(), userCreate.getPhoneNumber(),
				userCreate.getName(),userCreate.getAnnualIncome(), 
				userCreate.getDateOfBirth());
		if(rowsAffected == 0)
			return false;

		rowsAffected = jdbcTemplate.update("update seq_table set id=? where entity=?",
				userCreate.getUser_id(), "user");
		if(rowsAffected == 0)
			return false;

		return true;
	}

	public boolean deleteUser(int userId) {
		return jdbcTemplate.update(SQL_DELETE_USER, userId) > 0;
	}
}
