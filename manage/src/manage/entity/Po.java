package manage.entity;

import java.util.Date;

import com.manage.entity.CustomerSupplier;

public class Po {
	private String code;
	private Date odate;
	private CustomerSupplier supplier;
	private String linkman;
	private String tel;
	private String fax;
	private String trans;
	private Date ddate;
	private String businesser;
	private String remark;
	private String isshow;
	private int nums;
	private double amount;
	private String state;
	private Date adate;
	private String operator;
	private String optname;
	private String optip;
	
	public Po() {
		super();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getOdate() {
		return odate;
	}
	public void setOdate(Date odate) {
		this.odate = odate;
	}
	public CustomerSupplier getSupplier() {
		return supplier;
	}
	public void setSupplier(CustomerSupplier supplier) {
		this.supplier = supplier;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public Date getDdate() {
		return ddate;
	}
	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}
	public String getBusinesser() {
		return businesser;
	}
	public void setBusinesser(String businesser) {
		this.businesser = businesser;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getAdate() {
		return adate;
	}
	public void setAdate(Date adate) {
		this.adate = adate;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOptname() {
		return optname;
	}
	public void setOptname(String optname) {
		this.optname = optname;
	}
	public String getOptip() {
		return optip;
	}
	public void setOptip(String optip) {
		this.optip = optip;
	}
	
}
