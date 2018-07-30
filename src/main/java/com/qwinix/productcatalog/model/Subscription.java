package com.qwinix.productcatalog.model;

import java.util.List;

public class Subscription{	
   
	private int subscription_id; 
    private int user_id;
    
	private List<PackageBean> packagebean;
	
	public int getSubscription_id() {
		return subscription_id;
	}
	public void setSubscription_id(int subscription_id) {
		this.subscription_id = subscription_id;
	}
	public List<PackageBean> getPackagebean() {
		return packagebean;
	}
	public void setPackagebean(List<PackageBean> packagebean) {
		this.packagebean = packagebean;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}