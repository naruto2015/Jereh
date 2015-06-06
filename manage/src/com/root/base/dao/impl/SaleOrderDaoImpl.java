package com.root.base.dao.impl;

import java.util.List;

import com.root.base.dao.BaseDao;
import com.root.base.dao.SaleOrderDao;
import com.root.base.entity.SaleOrder;

public class SaleOrderDaoImpl extends BaseDao implements SaleOrderDao {

	//获取订单
	@Override
	public List<SaleOrder> findSaleOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	//根据订单编号获取
	@Override
	public SaleOrder findSaleOrderByOrderCode(String orderCode) {
		// TODO Auto-generated method stub
		return null;
	}

	//根据开始日期获取订单
	@Override
	public SaleOrder findSaleOrderByStartDate(String startDate) {
		// TODO Auto-generated method stub
		return null;
	}

	//根据结束时间获取订单
	@Override
	public SaleOrder findSaleOrderByEndDate(String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	//添加订单
	@Override
	public int addOrder(SaleOrder order) {
		// TODO Auto-generated method stub
		String sql="insert into saleorder(code, orderdate, customercode, contacter, telphone, fax, trans, businesser, deliverydate, remarks)"
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		return super.executeUpdate(sql,new Object[]{order.getCode(),order.getOrderDate(),order.getCustomerCode(),order.getContActer(),
				order.getTel(),order.getFax(),order.getTrans(),order.getBusinesser(),order.getDeliveryDate(),order.getRemarks()});
	}

}
