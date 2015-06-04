package com.root.base.service;

import com.root.base.entity.BaseCompany;

/**
 * 公司新息管理
 * @author 王亚军
 * @date 2015-05-28
 */
public interface CompanyService {
	
	////获取公司信息
	public BaseCompany getCompanyInfo(int code);
	//添加信息
	public int addCompany(BaseCompany company);
}
