package com.root.base.dao.impl;

import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.manage.dao.CustomerSupplierDao;
import com.manage.dao.impl.CustomerSupplierDaoImpl;
import com.manage.entity.CustomerSupplier;
import com.root.base.dao.BaseDao;
import com.root.base.dao.SaleOrderDao;
import com.root.base.entity.PageBean;
import com.root.base.entity.SaleOrder;
import com.root.util.DateUtil;

public class SaleOrderDaoImpl extends BaseDao implements SaleOrderDao {

	ResultSet rs=null;
	PageBean pageBean=new PageBean();
	private DateUtil dateUtil=new DateUtil();
	private CustomerSupplierDao csd=new CustomerSupplierDaoImpl();
	//获取订单列表
	@Override
	public PageBean findSaleOrder(int pageNo,int pageSize,SaleOrder saleOrder) {  
		// TODO Auto-generated method stub
		String sql="select * from saleorder where 1=1 ";
		String sql2="select count(*) from saleorder where 1=1";
		
		if(saleOrder.getCode()!=null && !saleOrder.getCode().equals("")){
			sql+=" and coce="+"'"+saleOrder.getCode()+"'";
			sql2+=" and coce="+"'"+saleOrder.getCode()+"'";
		}
		if(saleOrder.getOrderDate()!=null && !saleOrder.getOrderDate().equals("")){
			sql+=" and orderdate="+"'"+saleOrder.getOrderDate()+"'";
			sql2+=" and orderdate="+"'"+saleOrder.getOrderDate()+"'";
		}
		if(saleOrder.getDeliveryDate()!=null && !saleOrder.getDeliveryDate().equals("")){
			sql+=" and deliverydate="+"'"+saleOrder.getDeliveryDate()+"'";
			sql2+=" and deliverydate="+"'"+saleOrder.getDeliveryDate()+"'";
		}
		int recount=super.executeTotalCount(sql2); 
		pageBean.setRecordCount(recount);
		
		List<SaleOrder> list=new ArrayList<SaleOrder>();
		SaleOrder order=null;
		CustomerSupplier supplier=null;
		rs=super.executeQueryForPage(sql,new Object[]{pageNo*pageSize,(pageNo-1)*pageSize});
		try {
			while(rs.next()){
			order=new SaleOrder();
			supplier=new CustomerSupplier();
			order.setCode(rs.getString("code"));
			supplier=csd.findByCode(rs.getString("customercode"));
			order.setCustomerCode(supplier);
			order.setContActer(rs.getString("contacter"));
			order.setTel(rs.getString("telphone"));
			order.setFax(rs.getString("fax"));
			order.setTrans(rs.getString("trans"));
			order.setBusinesser(rs.getString("businesser"));
			order.setOrderDate(rs.getString("orderdate"));
			order.setDeliveryDate(rs.getString("deliverydate"));
			order.setRemarks(rs.getString("remarks"));
			order.setIsShow(rs.getString("isshow"));
			order.setNums(rs.getInt("nums"));
			order.setNumsPrice(rs.getInt("numsprice"));
			order.setState(rs.getString("state"));
			order.setAddDate(rs.getDate("adddate"));
			order.setAddUser(rs.getString("adduser"));
			order.setAddUserName(rs.getString("addusername"));
			order.setAddIp(rs.getString("addip"));
			list.add(order);
			}
			pageBean.setData(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		
		
		return pageBean;
	}
	
	//获取订单
	public SaleOrder findSaleOrderByCode(String code){ 
		// TODO Auto-generated method stub
		String sql="select * from saleorder where code=?";
		SaleOrder order=null;
		CustomerSupplier supplier=null;
		rs=super.executeQuery(sql,code);
		try {
			if(rs.next()){
				order=new SaleOrder();
				supplier=new CustomerSupplier();
				order.setCode(rs.getString("code"));
				supplier=csd.findByCode(rs.getString("customercode"));
				order.setCustomerCode(supplier);
				order.setContActer(rs.getString("contacter"));
				order.setTel(rs.getString("telphone"));
				order.setFax(rs.getString("fax"));
				order.setTrans(rs.getString("trans"));
				order.setBusinesser(rs.getString("businesser"));
				order.setOrderDate(rs.getString("orderdate"));
				order.setDeliveryDate(rs.getString("deliverydate"));
				order.setRemarks(rs.getString("remarks"));
				order.setIsShow(rs.getString("isshow"));
				order.setNums(rs.getInt("nums"));
				order.setNumsPrice(rs.getInt("numsprice"));
				order.setState(rs.getString("state"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return order;
	}
	//添加订单
	@Override
	public int addOrder(SaleOrder order) {
		// TODO Auto-generated method stub
		String sql="insert into saleorder(code, orderdate, customercode, contacter, telphone, fax, trans, "
										+ "businesser, deliverydate, remarks)"
										+ "values(?,?,?,?,?,?,?,?,?,?)";
		
		return super.executeUpdate(sql,new Object[]{order.getCode(),order.getOrderDate(),
				order.getCustomerCode(),order.getContActer(),order.getTel(),order.getFax(),order.getTrans(),order.getBusinesser(),
										order.getDeliveryDate(),order.getRemarks()});
	}

	
	//删除订单
	@Override
	public int delOrder(String code) {
		// TODO Auto-generated method stub
		String sql="delete from saleorder where code=?";
		return super.executeUpdate(sql,code);
	}


	//修改订单
	@Override
	public int updateOrder(SaleOrder order) {
		// TODO Auto-generated method stub
		String sql="update saleorder set customercode=?,nums=?,numsprice=?,"
				+ "contacter=?,telphone=?,state=?,businesser=? where code=?";
		return super.executeUpdate(sql,new Object[]{order.getNums(),order.getNumsPrice(),order.getContActer(),
									order.getTel(),order.getState(),order.getBusinesser(),order.getCode()});
	}

//	public static void main(String[] args){
//		SaleOrderDao sd=new SaleOrderDaoImpl();
//		SaleOrder order=sd.findSaleOrderByCode("5");
//		System.out.print(order.getCustomerCode().getCity());
//	}
}
