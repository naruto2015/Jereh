package com.manage.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.manage.dao.BaseDao;
import com.manage.dao.SalePriceDao;
import com.manage.entity.CustomerSupplier;
import com.manage.entity.PageBean;
import com.manage.entity.SalePrice;
import com.manage.entity.SalePriceDetail;

public class SalePriceDaoImpl extends BaseDao implements SalePriceDao {

	@Override
	public PageBean findSalePriceList(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from SALEQUOTATION";
		ResultSet rs=null;
		rs=super.executeQueryForPage(sql, pageNo,pageSize);
		PageBean pageBean=new PageBean();
		List<SalePrice> spList=new ArrayList<SalePrice>();
		SalePrice sp=null;
		
		try {
			while(rs.next()){
			sp=new SalePrice();
			sp.setCode(rs.getString("code"));
			sp.setSqdate(rs.getDate("sqdate"));
			CustomerSupplier cs=new CustomerSupplier();
		    cs.setCsName(rs.getString("csname"));
		    sp.setCs(cs);
			sp.setNums(rs.getInt("nums"));
			sp.setNumsPrice(rs.getInt("numsprice"));
			sp.setContacter(rs.getString("contacter"));
			sp.setTelphone(rs.getString("telphone"));
			sp.setState(rs.getString("state"));
			sp.setAddusername(rs.getString("addusername"));
			spList.add(sp);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pageBean.setData(spList);
		pageBean.setRecordCount(super.executeTotalCount(sql));
		return pageBean ;
	}

	@Override
	public int insertSalePrice(SalePrice saleprice) {
		// TODO Auto-generated method stub
		String sql="insert into SALEQUOTATION (code,sqdate,customercode,contacter," +
				"telphone,fax,nums,numsprice,isshow,state,remarks,adddate,adduser," +
				"addusername,addip,compcode) values(?,sysdate,?,?,?,?,?,?,?,?,?,sysdate," +
				"?,?,?,?) ";
		int ret=super.executeUpdate(sql,new Object[]{saleprice.getCode(),saleprice.getCustomerCode(),
				saleprice.getContacter(),saleprice.getTelphone(),saleprice.getFax(),saleprice.getNums(),saleprice.getNumsPrice(),
				saleprice.getIsShow(),saleprice.getState(),saleprice.getRemarks(),saleprice.getAdduser(),
				saleprice.getAddusername(),saleprice.getAddip(),saleprice.getCompcode()});
		
		
		return ret;
	}

	@Override
	public int updateSalePrice(SalePrice saleprice) {
		// TODO Auto-generated method stub
		String sql="update SALEQUOTATION set csname=?,contacter=?,telphone=? ,fax=?,remarks=? where code=?";
		int ret=super.executeUpdate(sql,new Object[]{saleprice.getCs().getCsName(),saleprice.getContacter(),
				saleprice.getTelphone(),saleprice.getFax(),saleprice.getRemarks(),saleprice.getCode()});
		return ret;
	}

	@Override
	public int delSalePrice(String code) {
		// TODO Auto-generated method stub
		String sql="delete SALEQUOTATION where code=?";
		return super.executeUpdate(sql,new Object[]{code});
	}

	@Override
	public SalePrice findByCode(String code) {
		// TODO Auto-generated method stub
		String sql="select * from SALEQUOTATION where code=?";
		ResultSet rs=null;
		rs=super.executeQuery(sql,code);
		SalePrice sp=null;
		try {
			if(rs.next()){
				sp=new SalePrice();
				sp.setCode(rs.getString("code"));
				CustomerSupplier cs=new CustomerSupplier();
				cs.setCsName(rs.getString("csname"));
				sp.setCs(cs);
				sp.setContacter(rs.getString("contacter"));
				sp.setTelphone(rs.getString("telphone"));
				sp.setFax(rs.getString("fax"));
				sp.setRemarks(rs.getString("remarks"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return sp;
	}

	@Override
	public PageBean findList(SalePrice sp, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from SALEQUOTATION where 1=1";
		if(sp.getCode()!=null&&!sp.getCode().equals("")){
			sql+=" and code="+"'"+sp.getCode()+"'";
		}
		if(sp.getCs().getCsName()!=null&&!sp.getCs().getCsName().equals("")){
			sql+=" and csName="+"'"+sp.getCs().getCsName()+"'";
		}
		if(sp.getSqdate()!=null&&!sp.getSqdate().equals("")){
			sql+=" and sqdate="+"'"+sp.getSqdate()+"'";
		}
		if(sp.getAdddate()!=null&&!sp.getAdddate().equals("")){
			sql+=" and adddate="+"'"+sp.getAdddate()+"'";
		}
		
		ResultSet rs=super.executeQueryForPage(sql, pageNo, pageSize);
		PageBean pageBean=new PageBean();
		List<SalePrice> list=new ArrayList<SalePrice>();
		SalePrice saleprice=null;
		try {
			while(rs.next()){
				saleprice=new SalePrice();
				saleprice.setCode(rs.getString("code"));
				saleprice.setSqdate(rs.getDate("sqdate"));
				CustomerSupplier cs=new CustomerSupplier();
				cs.setCsName(rs.getString("csname"));
				saleprice.setCs(cs);
				saleprice.setNums(rs.getInt("nums"));
				saleprice.setNumsPrice(rs.getInt("numsprice"));
				saleprice.setContacter(rs.getString("contacter"));
				saleprice.setTelphone(rs.getString("telphone"));
				saleprice.setState(rs.getString("state"));
				saleprice.setAddusername(rs.getString("addusername"));
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pageBean.setData(list);
		pageBean.setRecordCount(super.executeTotalCount(sql));

		return pageBean;
	}

	@Override
	public PageBean findDetailList(String code,int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from SALEQUOTATION_DETAIL where code=?";
		ResultSet rs=null;
		rs=super.executeQueryForPage(sql,pageNo,pageSize);
		PageBean pageBean=new PageBean();
		List<SalePriceDetail> detailList=new ArrayList<SalePriceDetail>();
		SalePriceDetail spd=null;
		try {
			while(rs.next()){
			spd=new SalePriceDetail();
			spd.setCode(rs.getString("code"));
			spd.setDeliverymode(rs.getString("deliverymode"));
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
