package com.manage.dao;

import com.manage.entity.PageBean;
import com.manage.entity.SalePrice;

public interface SalePriceDao {

	 public PageBean findSalePriceList(int pageNo,int pageSize);
	 public int insertSalePrice(SalePrice saleprice);
	 public int updateSalePrice(SalePrice saleprice);
	 public int delSalePrice(String code);
	 public SalePrice findByCode(String code);
	 public PageBean findList(SalePrice sp,int pageNo,int pageSize);
	 public PageBean findDetailList(String code,int pageNo,int pageSize);
}
