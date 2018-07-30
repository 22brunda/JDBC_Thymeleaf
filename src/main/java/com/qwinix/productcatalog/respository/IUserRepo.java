package com.qwinix.productcatalog.respository;

import javax.sql.DataSource;

import com.qwinix.productcatalog.model.SignIn;

public interface IUserRepo {
	public abstract void setDataSource(DataSource dataSource);
	public abstract boolean authenticateUser(SignIn signin);
}

