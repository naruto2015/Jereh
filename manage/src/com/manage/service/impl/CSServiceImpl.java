package com.manage.service.impl;

import java.util.Date;

import java.util.List;

import com.manage.dao.CustomerSupplierDao;
import com.manage.dao.impl.CustomerSupplierDaoImpl;
import com.manage.entity.CustomerSupplier;
import com.manage.entity.PageBean;
import com.manage.service.CustomerSupplierService;

public class CSServiceImpl implements CustomerSupplierService {
	private CustomerSupplierDao csd=new CustomerSupplierDaoImpl();

	public PageBean getCustomerSupplier(int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		return csd.findCustomerSupplier(pageNo, pageSize);
	}

	public int addCustomerSupplier(CustomerSupplier supplier) {
		// TODO Auto-generated method stub
		return csd.insertCustomerSupplier(supplier);
	}

	public int updateCustomerSupplier(CustomerSupplier supplier) {
		// TODO Auto-generated method stub
		return csd.updateSupplier(supplier);
	}

	public int deleteCustomerSupplier(String code) {
		// TODO Auto-generated method stub
		return csd.deleteSupplier(code);
	}

	public int deleteBatch(String code) {
		// TODO Auto-generated method stub
		return 0;
	}

	public CustomerSupplier searchByCode(String code) {
		// TODO Auto-generated method stub
		return csd.findByCode(code);
	}

	public PageBean findList(CustomerSupplier bcs, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return csd.findList(bcs, pageNo, pageSize);
	}

	

}
