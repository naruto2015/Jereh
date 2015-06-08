package com.root.base.dao;

import com.root.base.entity.PageBean;

public interface IPurchaseReturnDao {
	public PageBean findAll(int pageNo,int pageSize);
}