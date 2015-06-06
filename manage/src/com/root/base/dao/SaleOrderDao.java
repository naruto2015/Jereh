package com.root.base.dao;

import java.util.List;

import com.root.base.entity.SaleOrder;

public interface SaleOrderDao {

	//获取订单
	public List<SaleOrder> findSaleOrder();
	
	//根据订单编号获取
	public SaleOrder findSaleOrderByOrderCode(String orderCode);
	
	//根据开始日期获取订单
	public SaleOrder findSaleOrderByStartDate(String startDate);
	
	//根据结束时间获取订单
	public SaleOrder findSaleOrderByEndDate(String endDate);
	
	//添加订单
	public int addOrder(SaleOrder order);
	 
}
