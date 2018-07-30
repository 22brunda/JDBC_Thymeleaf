package com.qwinix.productcatalog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qwinix.productcatalog.model.Subscription;
import com.qwinix.productcatalog.respository.SubscriptionRepo;

@Service
public class SubscriptionService {
	
	@Autowired
	SubscriptionRepo subscriptionRepo;

	public Subscription createSubscription(Subscription subscribe) {
		subscriptionRepo.createSubscription(subscribe);
		return subscribe;
	}

	public Subscription findSubscription(int subscribeId) {
		return subscriptionRepo.getSubscriptionById(subscribeId);
	}
		
	public void subscribeId(int deleteSubscribeId) {
		subscriptionRepo.deleteSubscription(deleteSubscribeId);
		
	}

	public void updateSubscription(Subscription subscriptionUpdate) {
		subscriptionRepo.updateSubscription(subscriptionUpdate);

		
	}
}