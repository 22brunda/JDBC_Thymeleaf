package com.qwinix.productcatalog.respository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.qwinix.productcatalog.model.PackageBean;
import com.qwinix.productcatalog.model.PackageMapper;
import com.qwinix.productcatalog.model.Subscription;
import com.qwinix.productcatalog.service.PackageService;

@Component
public class SubscriptionRepo implements ISubscription{
	@Autowired	
	JdbcTemplate jdbcTemplate;

	@Autowired
	PackageService packageService;

	//	private final String SQL_FIND_SUBSCRIBE = "select * from subscriptions where id = ?";
	private final String SQL_DELETE_SUBSCRIBE = "delete from subscriptions_packagebean where subscription_subscription_id = ?";
	//	private final String SQL_GET_ALL = "select * from subscriptions";
	private final String SQL_INSERT_SUBSCRIBE = "insert into subscriptions(subscription_id, user_id) values(?,?)";
	private final String SQL_SELECT_SUBSCRIPTIONS_PACKAGEBEAN = "select id, name, description, amount, priority, enabled"
																+ " from package p "
																+ "inner join subscriptions_packagebean sp "
																+ "on p.id = sp.packagebean_id"
																+ " and sp.subscription_subscription_id=?"; 
	private final String SQL_UPDATE_SUBSCRIBE = "update subscriptions set user_id = ? where subscription_id = ?";

	@Autowired
	public SubscriptionRepo(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//Create Subscription
	@Override
	public boolean createSubscription(Subscription subscribe) {
		//Write code to select from seq_table.
		Integer id = jdbcTemplate.queryForObject("select id from seq_table where entity=?", 
				new Object[] {"subscription"}, Integer.class);
		id += 1;
		subscribe.setSubscription_id(id);

		int rowsAffected = jdbcTemplate.update(SQL_INSERT_SUBSCRIBE, subscribe.getSubscription_id(), 
				subscribe.getUser_id());
		if(rowsAffected == 0)
			return false;

		rowsAffected = jdbcTemplate.update("update seq_table set id=? where entity=?",
				subscribe.getSubscription_id(), "subscription");
		if(rowsAffected == 0)
			return false;
		addPackages(subscribe.getPackagebean(), subscribe.getSubscription_id());
		return true;
	}

	//Add packages into subscription
	private final String SQL_INSERT_SUBSCRIPTIONS_PACKAGEBEAN = "insert into subscriptions_packagebean(subscription_subscription_id, packagebean_id) VALUES(?,?)";
	private boolean addPackages(List<PackageBean> packages, int subscribeId) {
		int rowsAffected = 0;
		for (PackageBean packageBean : packages) {
			rowsAffected = jdbcTemplate.update(SQL_INSERT_SUBSCRIPTIONS_PACKAGEBEAN, 
					subscribeId, packageBean.getPackageId());
		}
		return true;
	}

	//Get Subscription by Id
	@Override
	public Subscription getSubscriptionById(int id) {
		List<PackageBean> packages =  jdbcTemplate.query(SQL_SELECT_SUBSCRIPTIONS_PACKAGEBEAN, 
				new Object[] { id }, new PackageMapper());
		List<PackageBean> newPackages = new ArrayList<PackageBean>();

		for(PackageBean packageBean : packages) {
			packageBean = packageService.findById(packageBean.getPackageId());
			newPackages.add(packageBean);
		}

		Subscription subscription = new Subscription();
		subscription.setSubscription_id(id);
		subscription.setPackagebean(newPackages);

		return subscription;
	}

	//Delete Subscription
	public boolean deleteSubscription(int subscribeId) {
		return jdbcTemplate.update(SQL_DELETE_SUBSCRIBE, subscribeId) > 0;
	}

	//Update Subscription
	@Override
	public boolean updateSubscription(Subscription subscriptionUpdate) {
		int rowsAffected =	jdbcTemplate.update(SQL_UPDATE_SUBSCRIBE, subscriptionUpdate.getUser_id(), subscriptionUpdate.getSubscription_id());
		if(rowsAffected == 0)
			return false;
		addPackages(subscriptionUpdate.getPackagebean(), subscriptionUpdate.getSubscription_id());
		return true;
	}
}
