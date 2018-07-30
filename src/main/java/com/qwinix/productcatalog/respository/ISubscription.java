package com.qwinix.productcatalog.respository;

import com.qwinix.productcatalog.model.Subscription;

public interface ISubscription {
	Subscription getSubscriptionById(int id);
	
	boolean createSubscription(Subscription subscribe);

	boolean deleteSubscription(int subscribeId);
	
	boolean updateSubscription(Subscription subscriptionUpdate);

	
}
