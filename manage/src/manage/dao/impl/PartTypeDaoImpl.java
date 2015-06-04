package manage.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import manage.dao.BaseDao;
import manage.dao.PartTypeDao;
import manage.entity.PageBean;
import manage.entity.PartType;

public class PartTypeDaoImpl extends BaseDao implements PartTypeDao {

	@Override
	public PageBean findAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from basepartscategory order by code asc";
		ResultSet rs=super.executeQueryForPage(sql, pageNo, pageSize);
		PageBean pb=new PageBean();
		List<PartType> list=new ArrayList<PartType>();
		PartType pt=null;
		try {
			while(rs.next()){
				pt=new PartType();
				pt.setId(rs.getString("code"));
				pt.setParentId(rs.getString("pcode"));
				pt.setName(rs.getString("categoryname"));
				String state=rs.getString("isshow");
				if(state.equals("1")){
					state="显示";
				}else{
					state="隐藏";
				}
				pt.setState(state);
				pt.setRemark(rs.getString("remarks"));
				pt.setCompany(rs.getString("compcode"));
				pt.setDate(rs.getDate("adddate"));
				pt.setOperator(rs.getString("adduser"));
				pt.setOptname(rs.getString("addusername"));
				pt.setOptip(rs.getString("addip"));
				list.add(pt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pb.setData(list);
		sql="select count(code) from basepartscategory order by code asc";
		pb.setRecordCount(super.executeTotalCount(sql));
		return pb;
	}

	@Override
	public PageBean findByName(String name, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from basepartscategory where categoryname=? order by code asc";
		ResultSet rs=super.executeQueryForPage(sql, new Object[]{name,pageNo*pageSize,(pageNo-1)*pageSize});
		PageBean pb=new PageBean();
		List<PartType> list=new ArrayList<PartType>();
		PartType pt=null;
		try {
			while(rs.next()){
				pt=new PartType();
				pt.setId(rs.getString("code"));
				pt.setParentId(rs.getString("pcode"));
				pt.setName(rs.getString("categoryname"));
				String state=rs.getString("isshow");
				if(state.equals("1")){
					state="显示";
				}else{
					state="隐藏";
				}
				pt.setState(state);
				pt.setRemark(rs.getString("remarks"));
				pt.setCompany(rs.getString("compcode"));
				pt.setDate(rs.getDate("adddate"));
				pt.setOperator(rs.getString("adduser"));
				pt.setOptname(rs.getString("addusername"));
				pt.setOptip(rs.getString("addip"));
				list.add(pt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pb.setData(list);
		sql="select count(code) from v_categoryname order by code asc";
		pb.setRecordCount(super.executeTotalCount(sql));
		return pb;
	}

	@Override
	public int insert(PartType part) {
		// TODO Auto-generated method stub
		String sql="insert into basepartscategory(code,pcode,categoryname,isshow,"
				+ "remarks,compcode,adduser,addusername,addip,adddate) values(?,?,?,?,?,?,?,?,?,sysdate)";
		int ret=super.executeUpdate(sql,new Object[]{part.getId(),part.getParentId(),part.getName(),part.getState(),
				part.getRemark(),part.getCompany(),part.getOperator(),part.getOptname(),part.getOptip()});
		return ret;
	}

	@Override
	public int update(PartType part) {
		// TODO Auto-generated method stub
		String sql="update basepartscategory set categoryname=?,isshow=?,remarks=?,adddate=sysdate where code=?";
		int ret=super.executeUpdate(sql,new Object[]{part.getName(),part.getState(),part.getRemark(),part.getId()});
		return ret;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		String sql="delete from basepartscategory where code=?";
		return super.executeUpdate(sql, new Object[]{id});
	}

	@Override
	public List<String> findAllName() {
		// TODO Auto-generated method stub
		String sql="select  distinct categoryname from basepartscategory";
		ResultSet rs=super.executeQuery(sql);
		List<String> nameList=new ArrayList<String>();
		try {
			while(rs.next()){
				String name=rs.getString("categoryname");
				nameList.add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nameList;
	}

}
