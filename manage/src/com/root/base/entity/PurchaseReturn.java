package com.root.base.entity;

import java.util.Date;

public class PurchaseReturn {
	private String code;//采购退货编号
	private Date rdate;//采购退货日期
	private String suppliercode;//供应商编号
	private String contacter;//联系人
	private String telphone;//电话
	private String fax;//传真
	private String remarks;//备注
	private String isshow;//是否显示
	private int nums;//配件数量
	private double numsprice;//退货配件总价值
	private String state;//单据审核状态
	private String adduser;//操作人
	private String addusername;//操作人姓名
	private String addip;//操作IP
	private String compcode;//所属公司
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getSuppliercode() {
		return suppliercode;
	}
	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
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
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public double getNumsprice() {
		return numsprice;
	}
	public void setNumsprice(double numsprice) {
		this.numsprice = numsprice;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAdduser() {
		return adduser;
	}
	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}
	public String getAddusername() {
		return addusername;
	}
	public void setAddusername(String addusername) {
		this.addusername = addusername;
	}
	public String getAddip() {
		return addip;
	}
	public void setAddip(String addip) {
		this.addip = addip;
	}
	public String getCompcode() {
		return compcode;
	}
	public void setCompcode(String compcode) {
		this.compcode = compcode;
	}
}
