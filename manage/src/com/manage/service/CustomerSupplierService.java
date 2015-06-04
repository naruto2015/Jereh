package com.manage.service;

import java.util.Date;

import java.util.List;


import com.manage.entity.CustomerSupplier;
import com.manage.entity.PageBean;

public interface CustomerSupplierService {
  public PageBean getCustomerSupplier(int pageNo,int pageSize);
  public int addCustomerSupplier(CustomerSupplier supplier);
  public int updateCustomerSupplier(CustomerSupplier supplier);
  public int deleteCustomerSupplier(String code);
  public int deleteBatch(String code);
  public CustomerSupplier searchByCode(String code);
  public PageBean findList(CustomerSupplier bcs,int pageNo, int pageSize);
}
