package com.root.base.entity;
/**
 * 
 * @author 王亚军
 * @date 2015-06-09
 * @parms 出库详细 实体
 */
public class StockOutDetail {

	private String code;
	private String outCode;
	private String xsCode;
	private String pcode;
	private int nums;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOutCode() {
		return outCode;
	}
	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}
	public String getXsCode() {
		return xsCode;
	}
	public void setXsCode(String xsCode) {
		this.xsCode = xsCode;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	public int getwNums() {
		return wNums;
	}
	public void setwNums(int wNums) {
		this.wNums = wNums;
	}
	public float getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(float lastPrice) {
		this.lastPrice = lastPrice;
	}
	public String getReamrks() {
		return reamrks;
	}
	public void setReamrks(String reamrks) {
		this.reamrks = reamrks;
	}
	private float price;
	private String wareHouse;
	private int wNums;
	private float lastPrice;
	private String reamrks; 
}
