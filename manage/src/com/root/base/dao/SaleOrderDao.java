package com.root.base.dao;

import java.util.List; 

import com.root.base.entity.PageBean;
import com.root.base.entity.SaleOrder;
import com.root.base.entity.Stock;

public interface SaleOrderDao {

	//获取订单列表
	public PageBean findSaleOrder(int pageNo,int pageSizee,SaleOrder order);
	
	//获取订单by订单号
	public SaleOrder findSaleOrderByCode(String code);
	
	//添加订单
	public int addOrder(SaleOrder order);
	 
	//删除订单
	public int delOrder(String code);
	
	//修改订单
	public int updateOrder(SaleOrder order);
	
	//查找配件信息
	public PageBean findPartStockOpt(int pageNo,int pageSize,Stock parts);
}
