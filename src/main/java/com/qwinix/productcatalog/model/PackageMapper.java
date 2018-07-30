package com.qwinix.productcatalog.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PackageMapper implements RowMapper<PackageBean> {

	public PackageBean mapRow(ResultSet resultSet, int i) throws SQLException {

		PackageBean pack = new PackageBean();
		pack.setPackageId(resultSet.getInt("id"));
		pack.setName(resultSet.getString("name"));
		pack.setDescription(resultSet.getString("description"));
		pack.setPriority(resultSet.getInt("priority"));
		pack.setAmount(resultSet.getFloat("amount"));
		pack.setEnabled(resultSet.getBoolean("enabled"));
		return pack;
	}
}