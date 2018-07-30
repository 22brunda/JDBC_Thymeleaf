package com.qwinix.productcatalog.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ChannelMapper implements RowMapper<Channel> {

	public Channel mapRow(ResultSet resultSet, int i) throws SQLException {

		Channel channel = new Channel();
		channel.setId(resultSet.getInt("id"));
		channel.setName(resultSet.getString("name"));
		channel.setDescription(resultSet.getString("description"));
		channel.setPriority(resultSet.getInt("priority"));
		channel.setTitle(resultSet.getString("title"));
		channel.setActive(resultSet.getBoolean("active"));
		return channel;
	}
}
