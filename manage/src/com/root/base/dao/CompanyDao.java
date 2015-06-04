package com.root.base.dao;

import com.root.base.entity.BaseCompany;

/**
 * 公司新息管理
 * @author 王亚军
 * @date 2015-05-28
 */
public interface CompanyDao {
	
	 
	//获取公司信息
	public BaseCompany findBaseCompany(int code);
	//添加信息
	public int addBaseCompany(BaseCompany company);
	
	
}
