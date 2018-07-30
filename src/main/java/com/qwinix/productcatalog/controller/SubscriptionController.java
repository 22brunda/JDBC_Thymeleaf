package com.qwinix.productcatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qwinix.productcatalog.model.PackageBean;
import com.qwinix.productcatalog.model.Subscription;
import com.qwinix.productcatalog.service.SubscriptionService;

@RestController
@EnableAutoConfiguration
public class SubscriptionController {
	@Autowired
	SubscriptionService subscriptionService;
	
	@PostMapping("/subscription")
	public void createSubscription(@RequestBody Subscription subscribe) {
		subscriptionService.createSubscription(subscribe);
	}
	
	@PostMapping("/subscription/get")
	public Subscription findSubscription(@RequestBody int subscribeId) {
		return subscriptionService.findSubscription(subscribeId);
	}
	
	@PutMapping("/subscription/{id}")
	public void updateSubscription(@RequestBody Subscription subscriptionUpdate) {
		subscriptionService.updateSubscription(subscriptionUpdate);
	}

	@DeleteMapping("/subscription/{id}")
	public void deleteSubscription(@PathVariable(value = "id") int deleteSubscribeId) {
		subscriptionService.subscribeId(deleteSubscribeId);
	}
}
