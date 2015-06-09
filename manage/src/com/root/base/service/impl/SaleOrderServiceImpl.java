package com.root.base.service.impl;

import com.root.base.dao.SaleOrderDao;
import com.root.base.dao.impl.SaleOrderDaoImpl;
import com.root.base.entity.PageBean;
import com.root.base.entity.SaleOrder;
import com.root.base.service.SaleOrderService;

public class SaleOrderServiceImpl implements SaleOrderService {

	private SaleOrderDao sod=new SaleOrderDaoImpl();
	//添加订单
	@Override
	public int addSaleOrder(SaleOrder order) {
		// TODO Auto-generated method stub
		return sod.addOrder(order);
	}
	
	//显示订单列表
	@Override
	public PageBean getSaleOrderBy(int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		return sod.findSaleOrder(pageNo, pageSize);
	}

	//获得订单by订单号
	@Override
	public SaleOrder getSaleOrderByCode(String code) {
		// TODO Auto-generated method stub
		return sod.findSaleOrderByCode(code);
	}

	//删除订单
	@Override
	public int delOrder(String code) {
		// TODO Auto-generated method stub
		return sod.delOrder(code);
	}

	//修改订单
	@Override
	public int updateOrder(SaleOrder order) {
		// TODO Auto-generated method stub
		return sod.updateOrder(order);
	}

}
