package com.manage.entity;

import java.util.List;

public class PageBean {
	private List data;

	private int pageCount;//页面尺寸
	
	private int recordCount;//记录数
	
	private int pageNo;//当前页码
	
	private int pageSize;//分页尺寸

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
