package com.root.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.root.base.dao.BaseDao;
import com.root.base.dao.IReturnDao;
import com.root.base.entity.PageBean;
import com.root.base.entity.PurchaseReturn;

public class ReturnDao extends BaseDao implements IReturnDao {

	@Override
	public PageBean findAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from purchasereturn";
		ResultSet rs=super.executeQueryForPage(sql, pageNo, pageSize);
		PageBean pb=new PageBean();
		List<PurchaseReturn> prList=new ArrayList<PurchaseReturn>();
		PurchaseReturn pr=null;
		try {
			while(rs.next()){
				pr=new PurchaseReturn();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int delReturn(String code) {
		// TODO Auto-generated method stub
		return 0;
	}
}
