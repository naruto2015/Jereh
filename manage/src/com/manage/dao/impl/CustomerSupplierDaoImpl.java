package com.manage.dao.impl;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.manage.dao.BaseDao;
import com.manage.dao.CustomerSupplierDao;
import com.manage.entity.CustomerSupplier;
import com.manage.entity.PageBean;
import com.manage.util.GetCode;

public class CustomerSupplierDaoImpl extends BaseDao implements
		CustomerSupplierDao {

	public PageBean findCustomerSupplier(int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from basecustomersupplier";
		ResultSet rs=null;
		rs=super.executeQueryForPage(sql, pageNo, pageSize);
		PageBean pageBean=new PageBean();
		List<CustomerSupplier> csList=new ArrayList<CustomerSupplier>();
		CustomerSupplier cs=null;
		try {
			while(rs.next()){
			cs=new CustomerSupplier();
			cs.setCode(rs.getString("code"));
			cs.setCsName(rs.getString("csname"));
			cs.setType(rs.getString("categorycode"));
			cs.setLinkMan(rs.getString("contacter"));
			cs.setPhone(rs.getString("telephone"));
			cs.setAddress(rs.getString("address"));
			cs.setIsShow(rs.getString("isshow"));
			csList.add(cs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pageBean.setData(csList);
		pageBean.setRecordCount(super.executeTotalCount(sql));
		return pageBean;
	}
     
	public int insertCustomerSupplier(CustomerSupplier supplier) {
		// TODO Auto-generated method stub
		
		String sql="insert into basecustomersupplier" +
				"(" +
				"code,adddate,csname,contacter,telephone," +
				"address,isshow,postcode,province,city," +
				"legaler,qq,aliwang,bank,tax,categorycode," +
				"addusername,fax,email,url,msn," +
				"agent,account,spell,mobilephone,remarks," +
				"adduser,addip,compcode) " +
				"values(?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int ret=super.executeUpdate(sql,new Object[]{
				supplier.getCode(),supplier.getCsName(),
				supplier.getLinkMan(),supplier.getPhone(),supplier.getAddress(),supplier.getIsShow(),supplier.getPostCode(),
				supplier.getProvince(),supplier.getCity(),supplier.getLegaler(),supplier.getQq(),supplier.getWangWang(),
				supplier.getBank(),supplier.getTax(),supplier.getType(),supplier.getUname(),supplier.getFax(),
				supplier.getEmail(),supplier.getNetAddress(),supplier.getMsn(),supplier.getAgent(),
				supplier.getAccount(),supplier.getSpell(),supplier.getMobile(),supplier.getRemarks(),supplier.getUser(),
				supplier.getIp(),supplier.getCompcode()});
		     return ret;
	}

	public int updateSupplier(CustomerSupplier supplier) {
		// TODO Auto-generated method stub

		String sql="update basecustomersupplier set csname=?,categorycode=?,contacter=?,telephone=?," +
				"address=?,isshow=?,postcode=?,province=?,city=?,legaler=?,qq=?,aliwang=?,bank=?,tax=?,adddate=?," +
				"addusername=?,fax=?,email=?,url=?,msn=?,agent=?,account=?,remarks=?  where code=?";
		int ret=super.executeUpdate(sql,new Object[]{supplier.getCsName(),supplier.getType(),
				supplier.getLinkMan(),supplier.getPhone(),supplier.getAddress(),supplier.getIsShow(),supplier.getPostCode(),
				supplier.getProvince(),supplier.getCity(),supplier.getLegaler(),supplier.getQq(),supplier.getWangWang(),
				supplier.getBank(),supplier.getTax(),supplier.getAddDate(),supplier.getUname(),supplier.getFax(),
				supplier.getEmail(),supplier.getNetAddress(),supplier.getMsn(),supplier.getAgent(),
				supplier.getAccount(),supplier.getRemarks(),supplier.getCode()
				});
		     return ret;
	}

	public int deleteSupplier(String code) {
		// TODO Auto-generated method stub
		
		String sql="delete  basecustomersupplier where code=?";
		return super.executeUpdate(sql,new Object[]{code});
	}

	public PageBean findList(CustomerSupplier cs,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from basecustomersupplier where 1=1";
		if(cs.getCode()!=null&&!cs.getCode().equals("")){
			sql+=" and code="+"'"+cs.getCode()+"'";
		}
		if(cs.getCsName()!=null&&!cs.getCsName().equals("")){
			sql+=" and csName="+"'"+cs.getCsName()+"'";
		}
		if(cs.getAddDate()!=null&&!cs.getAddDate().equals("")){
			sql+=" and adddate="+"'"+cs.getAddDate()+"'";
		}
		
		ResultSet rs=super.executeQueryForPage(sql, pageNo, pageSize);
		PageBean pageBean=new PageBean();
		List<CustomerSupplier> list=new ArrayList<CustomerSupplier>();
		CustomerSupplier baseCustomerSupplier=null;
		try {
			while(rs.next()){
				baseCustomerSupplier=new CustomerSupplier();
				baseCustomerSupplier.setCode(rs.getString("code"));
				baseCustomerSupplier.setCsName(rs.getString("csname"));
				baseCustomerSupplier.setType(rs.getString("categorycode"));
				baseCustomerSupplier.setLinkMan(rs.getString("contacter"));
				baseCustomerSupplier.setPhone(rs.getString("telephone"));
				baseCustomerSupplier.setAddress(rs.getString("address"));
				baseCustomerSupplier.setIsShow(rs.getString("isshow"));
				list.add(baseCustomerSupplier);
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

	public CustomerSupplier findByCode(String code) {
		// TODO Auto-generated method stub
		String sql="select * from basecustomersupplier where code=?";
		ResultSet rs=null;
		rs=super.executeQuery(sql,code);
		CustomerSupplier cs=null;
		try {
			if(rs.next()){
				cs=new CustomerSupplier();
				cs.setCode(rs.getString("code"));
				cs.setCsName(rs.getString("csname"));
				cs.setType(rs.getString("categorycode"));
				cs.setLinkMan(rs.getString("contacter"));
				cs.setPhone(rs.getString("telephone"));
				cs.setAddress(rs.getString("address"));
				cs.setIsShow(rs.getString("isshow"));
				cs.setAddDate(rs.getDate("adddate"));
				cs.setFax(rs.getString("fax"));
				cs.setPostCode(rs.getString("postcode"));
				cs.setEmail(rs.getString("email"));
				cs.setProvince(rs.getString("province"));
				cs.setCity(rs.getString("city"));
				cs.setLegaler(rs.getString("legaler"));
				cs.setNetAddress(rs.getString("url"));
				cs.setQq(rs.getString("qq"));
				cs.setMsn(rs.getString("msn"));
				cs.setWangWang(rs.getString("aliwang"));
				cs.setAgent(rs.getString("agent"));
				cs.setBank(rs.getString("bank"));
				cs.setAccount(rs.getString("account"));
				cs.setTax(rs.getString("tax"));
				cs.setRemarks(rs.getString("remarks"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return cs;
	}

	public CustomerSupplier findByCsName(String csName) {
		// TODO Auto-generated method stub
		String sql="select * from basecustomersupplier where csname=?";
		ResultSet rs=null;
		rs=super.executeQuery(sql,csName);
		CustomerSupplier cs=null;
		try {
			if(rs.next()){
				cs=new CustomerSupplier();
				cs.setCode(rs.getString("code"));
				cs.setCsName(rs.getString("csname"));
				cs.setType(rs.getString("categorycode"));
				cs.setLinkMan(rs.getString("contacter"));
				cs.setPhone(rs.getString("telephone"));
				cs.setAddress(rs.getString("address"));
				cs.setIsShow(rs.getString("isshow"));	
				cs.setAddDate(rs.getDate("adddate"));
				cs.setFax(rs.getString("fax"));
				cs.setPostCode(rs.getString("postcode"));
				cs.setEmail(rs.getString("email"));
				cs.setProvince(rs.getString("province"));
				cs.setCity(rs.getString("city"));
				cs.setLegaler(rs.getString("legaler"));
				cs.setNetAddress(rs.getString("url"));
				cs.setQq(rs.getString("qq"));
				cs.setMsn(rs.getString("msn"));
				cs.setWangWang(rs.getString("aliwang"));
				cs.setAgent(rs.getString("agent"));
				cs.setBank(rs.getString("bank"));
				cs.setAccount(rs.getString("account"));
				cs.setTax(rs.getString("tax"));
				cs.setRemarks(rs.getString("remarks"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return cs;
	}

	public CustomerSupplier findByAddDate(Date addDate) {
		// TODO Auto-generated method stub
		String  sql="select * from basecustomersupplier where adddate=?";
		ResultSet rs=null;
		rs=super.executeQuery(sql,addDate);
		CustomerSupplier cs=null;
		try {
			if(rs.next()){
				cs=new CustomerSupplier();
				cs.setCode(rs.getString("code"));
				cs.setCsName(rs.getString("csname"));
				cs.setType(rs.getString("categorycode"));
				cs.setLinkMan(rs.getString("contacter"));
				cs.setPhone(rs.getString("telephone"));
				cs.setAddress(rs.getString("address"));
				cs.setIsShow(rs.getString("isshow"));
				cs.setAddDate(rs.getDate("adddate"));
				cs.setFax(rs.getString("fax"));
				cs.setPostCode(rs.getString("postcode"));
				cs.setEmail(rs.getString("email"));
				cs.setProvince(rs.getString("province"));
				cs.setCity(rs.getString("city"));
				cs.setLegaler(rs.getString("legaler"));
				cs.setNetAddress(rs.getString("url"));
				cs.setQq(rs.getString("qq"));
				cs.setMsn(rs.getString("msn"));
				cs.setWangWang(rs.getString("aliwang"));
				cs.setAgent(rs.getString("agent"));
				cs.setBank(rs.getString("bank"));
				cs.setAccount(rs.getString("account"));
				cs.setTax(rs.getString("tax"));
				cs.setRemarks(rs.getString("remarks"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return cs;
	}
	

}
