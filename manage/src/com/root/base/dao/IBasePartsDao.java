package com.root.base.dao;

import com.root.base.entity.BaseParts;
import com.root.base.entity.PageBean;

public interface IBasePartsDao {
	public PageBean findAll(int pageNo,int pageSize);
	
	public int update(BaseParts part);
	
	public int add(BaseParts part);
	
	public int delete(String partscode);
	
	public PageBean findByNoNameCategory(BaseParts bp,int pageNo,int pageSize);
}
