package manage.entity;

import com.root.base.entity.BaseParts;

public class PoDetail {
	private String dcode;
	private String ocode;
	private String xcode;
	private BaseParts part;
	private int nums;
	private double price;
	private String rkstate;
	private int rknums;
	private String remark;
	private double lastPrice;
	
	public PoDetail() {
		super();
	}
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	public String getOcode() {
		return ocode;
	}
	public void setOcode(String ocode) {
		this.ocode = ocode;
	}
	public String getXcode() {
		return xcode;
	}
	public void setXcode(String xcode) {
		this.xcode = xcode;
	}
	public BaseParts getPart() {
		return part;
	}
	public void setPart(BaseParts part) {
		this.part = part;
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
	public String getRkstate() {
		return rkstate;
	}
	public void setRkstate(String rkstate) {
		this.rkstate = rkstate;
	}
	public int getRknums() {
		return rknums;
	}
	public void setRknums(int rknums) {
		this.rknums = rknums;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}
	
}
