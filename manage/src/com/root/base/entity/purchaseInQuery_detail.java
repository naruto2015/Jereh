package com.root.base.entity;

public class purchaseInQuery_detail {

	private String dcode;
	private String xcode;
	private String pcode;
	private int nums;
	private double price;
	private String deliveryMode;
	private String remarks;
	
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	public String getXcode() {
		return xcode;
	}
	public void setXcode(String xcode) {
		this.xcode = xcode;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public purchaseInQuery_detail(String dcode, String xcode, String pcode,
			int nums, double price, String deliveryMode, String remarks) {
		super();
		this.dcode = dcode;
		this.xcode = xcode;
		this.pcode = pcode;
		this.nums = nums;
		this.price = price;
		this.deliveryMode = deliveryMode;
		this.remarks = remarks;
	}
	public purchaseInQuery_detail() {
		super();
	}
	
	 
	
	
	
}
