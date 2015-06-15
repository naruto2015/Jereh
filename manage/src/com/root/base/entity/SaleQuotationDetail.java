package com.root.base.entity;

public class SaleQuotationDetail {
	private String code;
	private SaleOrder sCode;
	private BaseParts pCode;
	private int nums;
	private float price;
	private String deliveryMode;
	private String remarks;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public SaleOrder getsCode() {
		return sCode;
	}
	public void setsCode(SaleOrder sCode) {
		this.sCode = sCode;
	}
	public BaseParts getpCode() {
		return pCode;
	}
	public void setpCode(BaseParts pCode) {
		this.pCode = pCode;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
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
	
	
}
