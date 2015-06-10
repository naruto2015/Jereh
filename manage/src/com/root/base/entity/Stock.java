package com.root.base.entity;

/**
 * 
 * @author 王亚军
 * @date 2015-06-09
 * @库存信息
 *
 */
public class Stock {
	private String hcode;
	private BaseParts pcode;
	private int nums;
	private int maxNums;
	private int minNums;
	private float costprice;
	private String remarks;
	private float lastPrice;
	
	public float getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(float lastPrice) {
		this.lastPrice = lastPrice;
	}
	public String getHcode() {
		return hcode;
	}
	public void setHcode(String hcode) {
		this.hcode = hcode;
	}
	public BaseParts getPcode() {
		return pcode;
	}
	public void setPcode(BaseParts pcode) {
		this.pcode = pcode;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public int getMaxNums() {
		return maxNums;
	}
	public void setMaxNums(int maxNums) {
		this.maxNums = maxNums;
	}
	public int getMinNums() {
		return minNums;
	}
	public void setMinNums(int minNums) {
		this.minNums = minNums;
	}
	public float getCostprice() {
		return costprice;
	}
	public void setCostprice(float costprice) {
		this.costprice = costprice;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
