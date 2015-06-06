package com.root.base.service.impl;

import com.root.base.dao.SaleOrderDao;
import com.root.base.dao.impl.SaleOrderDaoImpl;
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

}
