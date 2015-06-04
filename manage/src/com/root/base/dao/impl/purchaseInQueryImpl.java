package com.root.base.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import manage.dao.BaseDao;

import org.junit.Test;

import com.root.base.dao.purchaseInQueryDao;
import com.root.base.entity.purchaseInQuery;
import com.root.base.entity.purchaseQuery;

public class purchaseInQueryImpl implements purchaseInQueryDao{

	
	static BaseDao bd=new BaseDao();
	static purchaseInQueryDao pqd=new purchaseInQueryImpl();
	public void insertpurchase(purchaseInQuery pcq) {
		String sql="insert into PURCHASEINQUERY values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Date date =new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		String code=sdf.format(date);
		code="XJ"+code;
		System.out.println(code);
		java.sql.Date date2=new java.sql.Date(date.getTime());
		//pcq.setAddDate(date2);
		pcq.setNumsPrice(0.00);
		pcq.setCode(code);
		Object[] params={pcq.getCode(),date2,pcq.getSupplierCode(),pcq.getContacter(),
				pcq.getTelphone(),pcq.getFax(),pcq.getRemarks(),pcq.getIsShow(),pcq.getNums(),pcq.getNumsPrice()
				,pcq.getState(),pcq.getAddUser(),pcq.getAddUserName(),pcq.getAddIp(),pcq.getCompCode()};
	    bd.executeUpdate(sql, params);
	    
	    bd.closeAll();
		
	}

	@Test
	public void test(){
		purchaseInQuery pcq=new purchaseInQuery();
		pqd.insertpurchase(pcq);
	}

	public void findAndbyConditions(int pageNO,int pageSize,purchaseQuery pq) {
		
		String sql="select * from PURCHASEINQUERY where and 1=1";
		if(pq.getCode()!=null||pq.getCode()!=""){
			
		}
	    
		
		
		
	}
	
	 
	
	
}
