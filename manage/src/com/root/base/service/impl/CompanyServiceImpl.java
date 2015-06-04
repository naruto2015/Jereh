package com.root.base.service.impl;

import com.root.base.dao.CompanyDao;
import com.root.base.dao.impl.CompanyDaoImpl;
import com.root.base.entity.BaseCompany;
import com.root.base.service.CompanyService;

/**
 * 公司新息管理
 * @author 王亚军
 * @date 2015-05-28
 */
public class CompanyServiceImpl implements CompanyService {
	private CompanyDao companyDao=new CompanyDaoImpl();

	//获取公司信息
	@Override
	public BaseCompany getCompanyInfo(int code) {
		// TODO Auto-generated method stub
		
		return companyDao.findBaseCompany(code);
	}

	//添加信息
	@Override
	public int addCompany(BaseCompany company) {
		// TODO Auto-generated method stub
		return companyDao.addBaseCompany(company);
	}


}
