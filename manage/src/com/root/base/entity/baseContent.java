package com.root.base.entity;

import java.util.Date;

public class baseContent {

	private String code;
	private String codeName;
	private String categoryCode;
	private String orderNo;
	private String isShow;
	private String remarks;
	private Date addDate;
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
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
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
	public baseContent(String code, String codeName, String categoryCode,
			String orderNo, String isShow, String remarks, Date addDate,
			String addUser, String addUserName, String addIp, String compCode) {
		super();
		this.code = code;
		this.codeName = codeName;
		this.categoryCode = categoryCode;
		this.orderNo = orderNo;
		this.isShow = isShow;
		this.remarks = remarks;
		this.addDate = addDate;
		this.addUser = addUser;
		this.addUserName = addUserName;
		this.addIp = addIp;
		this.compCode = compCode;
	}
	public baseContent() {
		super();
	}
	

	
	
	
}
