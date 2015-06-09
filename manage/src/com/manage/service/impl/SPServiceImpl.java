package com.manage.service.impl;

import com.manage.dao.SalePriceDao;
import com.manage.dao.impl.SalePriceDaoImpl;
import com.manage.entity.PageBean;
import com.manage.entity.SalePrice;
import com.manage.service.SalePriceService;

public class SPServiceImpl implements SalePriceService {
 private SalePriceDao spd=new SalePriceDaoImpl();
	@Override
	public PageBean getSalePriceList(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return spd.findSalePriceList(pageNo, pageSize);
	}

	@Override
	public int addSalePrice(SalePrice saleprice) {
		// TODO Auto-generated method stub
		return spd.insertSalePrice(saleprice);
	}

	@Override
	public int updateSalePrice(SalePrice saleprice) {
		// TODO Auto-generated method stub
		return spd.updateSalePrice(saleprice);
	}

	@Override
	public int deleteSalePrice(String code) {
		// TODO Auto-generated method stub
		return spd.delSalePrice(code);
	}

	@Override
	public SalePrice searchByCode(String code) {
		// TODO Auto-generated method stub
		return spd.findByCode(code);
	}

	@Override
	public PageBean getList(SalePrice sp, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return spd.findList(sp, pageNo, pageSize);
	}

	@Override
	public PageBean getDetailList(String code,int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
