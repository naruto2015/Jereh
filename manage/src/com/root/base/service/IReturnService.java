package com.root.base.service;

import com.root.base.entity.PageBean;

public interface IReturnService {
	public PageBean getReturnList(int pageNo,int pageSize);
	
	public int delReturn(String code);
}
