package com.qwinix.productcatalog.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SubscriptionMapper implements RowMapper<Subscription> {

	public Subscription mapRow(ResultSet resultSet, int i) throws SQLException {

		Subscription subscribe = new Subscription();
		subscribe.setUser_id(resultSet.getInt("user_id"));
		subscribe.setSubscription_id(resultSet.getInt("subscription_id"));
		return subscribe;
	}
}
