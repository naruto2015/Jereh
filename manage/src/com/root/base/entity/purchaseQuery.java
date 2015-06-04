package com.root.base.entity;

import java.util.Date;



public class purchaseQuery {

	private String code;
	private Date beginTime;
	private Date endTime;
	private String compCode;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	public purchaseQuery(String code, Date beginTime, Date endTime,
			String compCode) {
		super();
		this.code = code;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.compCode = compCode;
	}
	public purchaseQuery() {
		super();
	}
 
	
	
	
	
}
