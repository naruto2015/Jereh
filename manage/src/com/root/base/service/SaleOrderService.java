package com.root.base.service;

import com.root.base.entity.PageBean;
import com.root.base.entity.SaleOrder;

public interface SaleOrderService {

	//显示订单列表
	public PageBean getSaleOrderBy(int pageNo,int pageSize,SaleOrder saleOrder);
	
	//获得订单by 订单号
	public SaleOrder getSaleOrderByCode(String code);
	//添加订单
	public int addSaleOrder(SaleOrder order);
	
	//删除订单
	public int delOrder(String code);
	
	//修改订单
	public int updateOrder(SaleOrder order);
}
