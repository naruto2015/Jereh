package com.root.base.dao;

import java.sql.ResultSet;
import java.util.List;

import com.root.base.entity.*;

public interface baseContentDao {

	public PageBean findAll(int pageNo,int pageSize,baseContent bc);
   
	public List findCategoryCode();
	
	public String findBycompName(String compName);
	
	public void insertBaseContent(baseContent bc);
	
	public void updateBaseContentByCode(baseContent bc);
	
	public void delBaseContentByCodeAndcategoryCode(baseContent bc);

	//public void delBaseContentByCodeAndcategoryCode(baseContent bc);
	public baseContent findByCodeAndcodeNameCategoryCode(baseContent bc);
	
	
	
}
