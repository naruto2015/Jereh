package com.manage.service;

import com.manage.entity.PageBean;
import com.manage.entity.SalePrice;

public interface SalePriceService {
 public PageBean getSalePriceList(int pageNo,int pageSize);
 public int addSalePrice(SalePrice saleprice);
 public int updateSalePrice(SalePrice saleprice);
 public int deleteSalePrice(String code);
 public SalePrice searchByCode(String code);
 public PageBean getList(SalePrice sp,int pageNo,int pageSize);
 public PageBean getDetailList(String code,int pageNo,int paggeSize);
 
}
