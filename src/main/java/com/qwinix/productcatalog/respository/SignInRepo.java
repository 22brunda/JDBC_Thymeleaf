package com.qwinix.productcatalog.respository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.qwinix.productcatalog.ValidationException;
import com.qwinix.productcatalog.model.SignIn;
import com.qwinix.productcatalog.model.UserMapper;
import com.qwinix.productcatalog.model.UserSignUp;

@Component
public class SignInRepo implements IUserRepo{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean authenticateUser(SignIn signIn) {
		UserSignUp u = null;
		try {
			
			System.out.println("Checking email - " + signIn.getEmail());
			u = jdbcTemplate.queryForObject("select * from user where email = ?",
					new Object[] {signIn.getEmail()}, new UserMapper());
			
		}  catch(EmptyResultDataAccessException edae) {
			throw new ValidationException("Email does not exist!", "username");
		}
		if(signIn.getPassword().equals(u.getPassword())) {
			return true;
		} else {
			throw new ValidationException("Incorrect Password!", "password");
		}
	}
}