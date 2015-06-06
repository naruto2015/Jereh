package com.root.base.entity;

public class ReturnDetail {
	private String code;//采购退货明细主键
	private String ctcode;//采购退货单据编号
	private String rkcode;//入库单号
	private String pcode;//配件编号
	private int nums;//退货数量
	private double price;//退货配件单价
	private String remarks;//备注
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCtcode() {
		return ctcode;
	}
	public void setCtcode(String ctcode) {
		this.ctcode = ctcode;
	}
	public String getRkcode() {
		return rkcode;
	}
	public void setRkcode(String rkcode) {
		this.rkcode = rkcode;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
