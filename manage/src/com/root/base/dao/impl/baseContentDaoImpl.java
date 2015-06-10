package com.root.base.dao.impl;

import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.root.base.dao.BaseDao;
import com.root.base.dao.baseContentDao;
import com.root.base.entity.PageBean;
import com.root.base.entity.baseContent;

public class baseContentDaoImpl implements baseContentDao{
   
	static baseContentDao bcd=new baseContentDaoImpl();
	static BaseDao bd=new BaseDao();
	static PageBean pb=new PageBean();
	@Override
	public PageBean findAll(int pageNo, int pageSize,baseContent bc) {
       
		String sql="select * from (select b1.*,rownum rn from " +
				"(select a1.categorycode, a1.code, a1.codename, a2.compname, a1.orderno, a1.remarks, a1.adduser, a1.isshow " +
				"from basecontent a1 join basecompany a2 on a1.compcode= a2.code order by categorycode desc,orderno asc)" +
				"b1 where rownum<=?) where rn>? and 1=1 ";
		
	 
		if(bc.getCode()!=null&&!bc.getCode().equals("")){
			sql+=" and code=" + "'"+bc.getCode()+ "'";
		}
		if(bc.getCodeName()!=null&&!bc.getCodeName().equals("")){
			sql+=" and codeName="+ "'"+bc.getCodeName()+ "'";
		} 
		if(bc.getCategoryCode()!=null&&!bc.getCategoryCode().equals("")){
			sql+=" and categoryCode="+ "'"+bc.getCategoryCode()+"'";
		}
	 
		
		Object[] params={pageNo*pageSize,(pageNo-1)*pageSize};
		ResultSet rs=bd.executeQuery(sql, params);
		List<baseContent> list=new ArrayList();
		try {
			while(rs.next()){
				baseContent bc2=new baseContent();
				bc2.setCategoryCode(rs.getString(1));
				bc2.setCode(rs.getString(2));
				bc2.setCodeName(rs.getString(3));
				bc2.setCompCode(rs.getString(4));
				bc2.setOrderNo(rs.getString(5));
				bc2.setRemarks(rs.getString(6));
				bc2.setAddUser(rs.getString(7));
				
				System.out.println(rs.getString(7));
				if(rs.getString(8).equals("1")){
					bc2.setIsShow("显示");
				}else{
					bc2.setIsShow("隐藏");
				}
				
				list.add(bc2);
	
			}
			pb.setData(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			bd.closeAll();
		}
		
		String sql2="select count(code)as count from basecontent where 1=1";
		if(bc.getCode()!=null&&!bc.getCode().equals("")){
			sql2+=" and code=" + "'"+bc.getCode()+ "'";
		}
		if(bc.getCodeName()!=null&&!bc.getCodeName().equals("")){
			sql2+=" and codeName="+ "'"+bc.getCodeName()+ "'";
		} 
		if(bc.getCategoryCode()!=null&&!bc.getCategoryCode().equals("")){
			sql2+=" and categoryCode="+ "'"+bc.getCategoryCode()+"'";
		}
		ResultSet rs2=bd.executeQuery(sql2);
		int recordCount=0;
		try {
			while(rs2.next()){
				 recordCount=rs2.getInt(1);
			}
			pb.setRecordCount(recordCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			bd.closeAll();
		}
		 
		return pb;
	}
  
	@Test
	public void test2(){
		baseContent bc4=new baseContent();
		
		bc4.setCode("001");
		//bc4.setCategoryCode("现金");
		pb=bcd.findAll(1, 3, bc4);
		System.out.println(pb.getData());
		System.out.println("数据个数"+pb.getRecordCount());
	}
	 
	@Override
	public List findCategoryCode() {
		
		
		String sql=" select distinct categoryCode  from basecontent";
		ResultSet rs=bd.executeQuery(sql);
		List list=new ArrayList();
		try {
			while(rs.next()){
				list.add(rs.getString(1));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bd.closeAll();
		System.out.println(list);
		return list;
	}
	
 
	@Override
	public String findBycompName(String compName) {

		String sql="select code from basecompany where compName=?";
		Object[] params={compName};
		ResultSet rs=bd.executeQuery(sql, params);
		String code=null;
		try {
			if(rs.next())
			code=rs.getString(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			bd.closeAll();
		}
		System.out.println(code);
		return code;
	}
	 
	@Override
	public void insertBaseContent(baseContent bc) {
		String sql="insert into baseContent(code,codename,categoryCode,orderNo,isSHow,addUser,compCode) " +
				"values(?,?,?,?,?,?,?)";
		
		Object[] params={bc.getCode(),bc.getCodeName(),bc.getCategoryCode(),bc.getOrderNo(),bc.getIsShow(),bc.getAddUser(),bc.getCompCode()};
		int ret=bd.executeUpdate(sql, params);
		System.out.println(ret);
		
	}
	@Override
	public void updateBaseContentByCode(baseContent bc) {
		String sql="update baseContent set codeName=?,orderNo=?,isShow=?,addUser=?,compCode=? where code=? and categoryCode=?";
		Object[] params={bc.getCodeName(),bc.getOrderNo(),bc.getIsShow(),bc.getAddUser(),bc.getCompCode(),bc.getCode(),bc.getCategoryCode()};
		bd.executeUpdate(sql, params);
		System.out.println("修改成功！");
		
	}
	@Override
	public void delBaseContentByCodeAndcategoryCode(baseContent bc) {
		// TODO Auto-generated method stub

		String sql="delete from baseContent where code=? and categoryCode=?";
		
		Object[] params={bc.getCode(),bc.getCategoryCode()};
		bd.executeUpdate(sql, params);
		 
			bd.closeAll();
		 
	}
	@Override
	public baseContent findByCodeAndcodeNameCategoryCode(baseContent bc) {

		String sql="select categoryCode,code,codeName,compCode,orderNo,remarks,addUser,isShow from baseContent where code=? and codeName=? and categoryCode=?";
		Object[] params={bc.getCode(),bc.getCodeName(),bc.getCategoryCode()};
		
		ResultSet rs=bd.executeQuery(sql, params);
		baseContent bc3=new baseContent();
		List list=new ArrayList();
	    try {
			while(rs.next()){
				baseContent bc2=new baseContent();
				bc2.setCategoryCode(rs.getString(1));
				bc2.setCode(rs.getString(2));
				bc2.setCodeName(rs.getString(3));
				bc2.setCompCode("宝鸡盟泰");
				bc2.setOrderNo(rs.getString(5));
				bc2.setRemarks(rs.getString(6));
				bc2.setAddUser(rs.getString(7));
				System.out.println("后台测试addUser"+rs.getString(7));
				if(rs.getString(8).equals("1")){
					bc2.setIsShow("显示");
				}else{
					bc2.setIsShow("隐藏");
				}
				bc3=bc2;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			bd.closeAll();
		}
	  
	    return bc3;
	}
	 
	

	
	
	
	
	
	
	
	
	
	
}
