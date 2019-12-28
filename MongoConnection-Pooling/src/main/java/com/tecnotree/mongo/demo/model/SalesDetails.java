package com.tecnotree.mongo.demo.model;

public class SalesDetails {

	
	
	ChannelDetails  channelDetails;
	
	String location;
	
	SalesChannel salesChannel;

	public ChannelDetails getChannelDetails() {
		return channelDetails;
	}

	public void setChannelDetails(ChannelDetails channelDetails) {
		this.channelDetails = channelDetails;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public SalesChannel getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(SalesChannel salesChannel) {
		this.salesChannel = salesChannel;
	}
	
	
}
