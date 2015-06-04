package com.manage.dao;

import java.util.Date;

import java.util.List;


import com.manage.entity.CustomerSupplier;
import com.manage.entity.PageBean;

public interface CustomerSupplierDao {
  public PageBean findCustomerSupplier(int pageNo,int pageSize);
  public int insertCustomerSupplier(CustomerSupplier supplier);
  public int updateSupplier(CustomerSupplier supplier);
  public int deleteSupplier(String code);
  public CustomerSupplier findByCode(String code);
 
  public PageBean findList(CustomerSupplier bcs,int pageNo, int pageSize);
}
