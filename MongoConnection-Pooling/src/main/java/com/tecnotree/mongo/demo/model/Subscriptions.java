package com.tecnotree.mongo.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="Subscriptions")
public class Subscriptions {

	@Id
	String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	SalesDetails salesDetails;
	Offering offering;
	CustomerInfo customerInfo;
	
	

	public Offering getOffering() {
		return offering;
	}

	public void setOffering(Offering offering) {
		this.offering = offering;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public SalesDetails getSalesDetails() {
		return salesDetails;
	}

	public void setSalesDetails(SalesDetails salesDetails) {
		this.salesDetails = salesDetails;
	}
	
	
	
}
