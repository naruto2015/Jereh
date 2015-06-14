package manage.entity;

import com.manage.entity.CustomerSupplier;

public class PurInQuery {

	private String code;
	private String addDate;
	private CustomerSupplier supplier;
	private String remarks;
	private String state;
	private int nums;
	private double numsPrice;
	private int num;
	private double numPrice;
	
	public PurInQuery() {
		super();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public CustomerSupplier getSupplier() {
		return supplier;
	}
	public void setSupplier(CustomerSupplier supplier) {
		this.supplier = supplier;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getNumPrice() {
		return numPrice;
	}
	public void setNumPrice(double numPrice) {
		this.numPrice = numPrice;
	}
}
