package com.root.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.root.base.dao.BaseDao;
import com.root.base.dao.IBasePartsDao;
import com.root.base.entity.BaseParts;
import com.root.base.entity.PageBean;
import com.root.base.entity.StockiInDetail;

public class BasePartsDao extends BaseDao implements IBasePartsDao {

	//查找所有的配件信息
	@Override
	public PageBean findAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from baseparts";
		ResultSet rs=super.executeQueryForPage(sql,pageNo,pageSize);
		PageBean pageBean=new PageBean();
		List<BaseParts> partsList=new ArrayList<BaseParts>();
		BaseParts part=null;
		try {
			while(rs.next()){
				part=new BaseParts();
				part.setPartsCode(rs.getString("partscode"));
				part.setPartsName(rs.getString("partsname"));
				part.setSpell(rs.getString("spell"));
				part.setPartsCategory(rs.getString("partscategory"));
				part.setPartsBrand(rs.getString("partsbrand"));
				part.setPartsNo(rs.getString("partsno"));
				part.setPartsGeneralPartsNo(rs.getString("partsgeneralpartsno"));
				part.setPartsModel(rs.getString("partsmodel"));
				part.setPartsModelOld(rs.getString("partsmodelold"));
				part.setPartsSize(rs.getString("partssize"));
				part.setPartsWeight(rs.getString("partsweight"));
				part.setPartsImg(rs.getString("partsimg"));
				part.setPartsUnit(rs.getString("partsunit"));
				part.setSalePrice(rs.getString("saleprice"));
				part.setCostPrice(rs.getInt("costprice"));
				part.setIsShow(rs.getString("isshow"));
				part.setRemarks(rs.getString("remarks"));
				part.setAddDate(rs.getDate("adddate"));
				part.setAddUser(rs.getString("adduser"));
				part.setAddUserName(rs.getString("addusername"));
				part.setAddIp(rs.getString("addip"));
				part.setCompcode(rs.getString("compcode"));
				
				partsList.add(part);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pageBean.setData(partsList);
		sql="select count(partscode) from baseparts";
		pageBean.setRecordCount(super.executeTotalCount(sql));
		return pageBean;
	}

	public static void main(String[] args){
		BasePartsDao bpd=new BasePartsDao();
		PageBean pageBean=bpd.findAll(1, 0);
		pageBean.getData();
	}
	@Override
	public int update(BaseParts part) {
		// TODO Auto-generated method stub
		String sql="update baseparts set partsname=?,partscategory=?,partsbrand=?,partsno=?,partsgeneralpartsno=?, "
				+ " partsmodel=?,partsmodelold=?,partssize=?,partsweight=?,partsimg=?,partsunit=?,saleprice=?,isshow=?,remarks=? "
				+ " where partscode=?";
		int ret=0;
		ret=super.executeUpdate(sql,new Object[]{part.getPartsName(),part.getPartsCategory(),
				part.getPartsBrand(),part.getPartsNo(),part.getPartsGeneralPartsNo(),part.getPartsModel(),
				part.getPartsModelOld(),part.getPartsSize(),part.getPartsWeight(),part.getPartsImg(),
				part.getPartsUnit(),part.getSalePrice(),part.getIsShow(),part.getRemarks(),part.getPartsCode()});
		return ret;
	}

	@Override
	public int add(BaseParts part) {
		// TODO Auto-generated method stub
		String sql="insert into baseparts(partscode,partsname,partscategory,partsbrand,partsno,partsgeneralpartsno, "
				+ " partsmodel,partsmodelold,partssize,partsweight,partsimg,partsunit,saleprice,isshow,remarks,adddate) "
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		int ret=0;
		ret=super.executeUpdate(sql, new Object[]{part.getPartsCode(),part.getPartsName(),part.getPartsCategory(),
				part.getPartsBrand(),part.getPartsNo(),part.getPartsGeneralPartsNo(),part.getPartsModel(),
				part.getPartsModelOld(),part.getPartsSize(),part.getPartsWeight(),part.getPartsImg(),
				part.getPartsUnit(),part.getSalePrice(),part.getIsShow(),part.getRemarks()});
		return ret;
	}

	@Override
	public int delete(String partscode) {
		// TODO Auto-generated method stub
		String sql="delete baseparts where partscode=?";
		return super.executeUpdate(sql,new Object[]{partscode});
	}

	@Override
	public PageBean findByNoNameCategory(BaseParts bp,int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from baseparts where partsno=? and partsname=? and partscategory=?";
		ResultSet rs=super.executeQueryForPage(sql,new Object[]{bp.getPartsNo(),bp.getPartsName(),bp.getPartsCategory(),pageNo*pageSize,(pageNo-1)*pageSize});
		PageBean pageBean=new PageBean();
		List<BaseParts> partsList=new ArrayList<BaseParts>();
		BaseParts part=null;
		try {
			while(rs.next()){
				part=new BaseParts();
				part.setPartsCode(rs.getString("partscode"));
				part.setPartsName(rs.getString("partsname"));
				part.setSpell(rs.getString("spell"));
				part.setPartsCategory(rs.getString("partscategory"));
				part.setPartsBrand(rs.getString("partsbrand"));
				part.setPartsNo(rs.getString("partsno"));
				part.setPartsGeneralPartsNo(rs.getString("partsgeneralpartsno"));
				part.setPartsModel(rs.getString("partsmodel"));
				part.setPartsModelOld(rs.getString("partsmodelold"));
				part.setPartsSize(rs.getString("partssize"));
				part.setPartsWeight(rs.getString("partsweight"));
				part.setPartsImg(rs.getString("partsimg"));
				part.setPartsUnit(rs.getString("partsunit"));
				part.setSalePrice(rs.getString("saleprice"));
				part.setCostPrice(rs.getInt("costprice"));
				part.setIsShow(rs.getString("isshow"));
				part.setRemarks(rs.getString("remarks"));
				part.setAddDate(rs.getDate("adddate"));
				part.setAddUser(rs.getString("adduser"));
				part.setAddUserName(rs.getString("addusername"));
				part.setAddIp(rs.getString("addip"));
				part.setCompcode(rs.getString("compcode"));
				partsList.add(part);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pageBean.setData(partsList);
		sql="select count(partscode) from baseparts";
		pageBean.setRecordCount(super.executeTotalCount(sql));
		return pageBean;
	}
}
