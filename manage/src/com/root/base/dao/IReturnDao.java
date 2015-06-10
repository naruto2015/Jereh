package com.root.base.dao;

import com.root.base.entity.PageBean;

public interface IReturnDao {
	public PageBean findAll(int pageNo,int pageSize);
	
	public int delReturn(String code);
}