package com.root.base.entity;

public class SaleOrderDetail {
	private String code;
	private SaleOrder sCode;
	private SaleQuotation sqCode;
	private BaseParts pCode;
	private int nums;
	private float price;
	private String state;
	private int ckNums;
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
	public SaleQuotation getSqCode() {
		return sqCode;
	}
	public void setSqCode(SaleQuotation sqCode) {
		this.sqCode = sqCode;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getCkNums() {
		return ckNums;
	}
	public void setCkNums(int ckNums) {
		this.ckNums = ckNums;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
