package com.root.base.entity;

import java.util.Date;

public class purchaseInQuery {

	private String code;
	private Date addDate;
	private String supplierCode;
	private String contacter;
	private String telphone;
	private String fax;
	private String remarks;
	private String isShow;
	private int nums;
	private double numsPrice;
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	private String state;
	private String addUser;
	private String addUserName;
	private String addIp;
	private String compCode;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getContacter() {
		return contacter;
	}
	public void setContacter(String contacter) {
		this.contacter = contacter;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public double getNumsPrice() {
		return numsPrice;
	}
	public void setNumsPrice(double numsPrice) {
		this.numsPrice = numsPrice;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddUserName() {
		return addUserName;
	}
	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	public String getAddIp() {
		return addIp;
	}
	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	public purchaseInQuery(String code, Date addDate, String supplierCode,
			String contacter, String telphone, String fax, String remarks,
			String isShow, int nums, double numsPrice, String state,
			String addUser, String addUserName, String addIp, String compCode) {
		super();
		this.code = code;
		this.addDate = addDate;
		this.supplierCode = supplierCode;
		this.contacter = contacter;
		this.telphone = telphone;
		this.fax = fax;
		this.remarks = remarks;
		this.isShow = isShow;
		this.nums = nums;
		this.numsPrice = numsPrice;
		this.state = state;
		this.addUser = addUser;
		this.addUserName = addUserName;
		this.addIp = addIp;
		this.compCode = compCode;
	}
	public purchaseInQuery() {
		super();
	}
 
	
	
	
	
}
