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
import com.root.base.entity.BaseParts;
import com.root.base.entity.PageBean;
import com.root.base.entity.SaleOrder;
import com.root.base.entity.Stock;
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
			sql+=" and code="+"'"+saleOrder.getCode()+"'";
			sql2+=" and code="+"'"+saleOrder.getCode()+"'";
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
			String state=rs.getString("state");
			if(state.equals("1")){
				state="已审核";
			}else{
				state="未审核";
			}
			order.setState(state);
			order.setAddDate(rs.getString("adddate"));
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

	/**
	 * 王亚军
	 * 查找配件信息列表
	 * 2015-06-09
	 */
	@Override
	public PageBean findPartStockOpt(int pageNo, int pageSize, Stock parts) {
		// TODO Auto-generated method stub
		String sql="select * from v_stockparts where 1=1";
		String sql2="select count(*) from v_stockparts where 1=1 ";
		if(parts.getPcode().getPartsCode()!=null && !parts.getPcode().getPartsCode().equals("")){
			sql+=" and partscode="+"'"+parts.getPcode().getPartsCode()+"'";
			sql2+=" and partscode="+"'"+parts.getPcode().getPartsCode()+"'";
		}
		if(parts.getPcode().getPartsName()!=null && !parts.getPcode().getPartsName().equals("")){
			sql+=" and partsname=" + "'" +parts.getPcode().getPartsName() + "'" ;
			sql2+=" and partsname=" + "'" +parts.getPcode().getPartsName() + "'" ;
		}
		if(parts.getHcode()!=null && !parts.getHcode().equals("")){
			sql+=" and hcode=" + "'" + parts.getHcode() + "'";
			sql2+=" and hcode=" + "'" + parts.getHcode() + "'";
		}
		
		int recount=super.executeTotalCount(sql2);
		pageBean.setRecordCount(recount);
		
		List<Stock> slist=new ArrayList<Stock>();
		Stock stock=null;
		BaseParts part=null;
		rs=super.executeQueryForPage(sql,new Object[]{pageNo*pageSize,(pageNo-1)*pageSize});
		try {
			while(rs.next()){
				stock=new Stock();
				part=new BaseParts();
				part.setPartsNo(rs.getString("partsno"));
				part.setPartsName(rs.getString("partsname"));
				part.setPartsBrand(rs.getString("partsbrand"));
				part.setPartsModel(rs.getString("partsmodel"));
				part.setRemarks(rs.getString("remarks"));
				part.setSalePrice(rs.getString("price"));
				
				stock.setPcode(part);
				stock.setNums(rs.getInt("nums"));
				stock.setLastPrice(rs.getFloat("lastprice"));
				stock.setHcode(rs.getString("hcode"));
				slist.add(stock);
				}
		} catch (SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pageBean.setData(slist);
		return pageBean;
	}

	
//	public static void main(String[] args){
//		SaleOrderDao sd=new SaleOrderDaoImpl();
////		SaleOrder order=sd.findSaleOrderByCode("5");
////		System.out.print(order.getCustomerCode().getCity());
//		Stock stock=new Stock();
//		BaseParts part=new BaseParts();
//		part.setPartsCode("");
//		part.setPartsName("");
//		stock.setPcode(part);
//		stock.setHcode("");
//		PageBean pagebean=sd.findPartStockOpt(1,2, stock);
//		List<Stock> slist=pagebean.getData();
//		for(Stock s:slist){
//			
//			System.out.println(s.getHcode()+s.getPcode().getPartsCode());
//		}
//	}
}
