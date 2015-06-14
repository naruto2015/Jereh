package manage.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.manage.entity.CustomerSupplier;
import com.root.base.entity.BaseParts;

import manage.dao.BaseDao;
import manage.dao.PoDao;
import manage.entity.PageBean;
import manage.entity.Po;
import manage.entity.PoDetail;
import manage.entity.PurInQuery;

public class PoDaoImpl extends BaseDao implements PoDao {

	@Override
	public PageBean findAll(int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		String sql="select * from v_purorder";
		ResultSet rs=super.executeQueryForPage(sql, pageNo, pageSize);
		List<Po> poList=new ArrayList<Po>();
		PageBean pb=new PageBean();
		Po po=null;
		CustomerSupplier cs=null;
		try {
			while(rs.next()){
				po=new Po();
				cs=new CustomerSupplier();
				po.setCode(rs.getString("code"));
				po.setOdate(rs.getString("orderdate"));
				cs.setCode(rs.getString("suppliercode"));
				cs.setCsName(rs.getString("csname"));
				po.setSupplier(cs);
				po.setLinkman(rs.getString("contacter"));
				po.setTel(rs.getString("telphone"));
				po.setFax(rs.getString("fax"));
				po.setTrans(rs.getString("trans"));
				po.setDdate(rs.getString("deliverydate"));
				po.setBusinesser(rs.getString("businesser"));
				po.setRemark(rs.getString("remarks"));
				po.setIsshow(rs.getString("isshow"));
				po.setNums(rs.getInt("nums"));
				po.setAmount(rs.getDouble("numsprice"));
				String state=rs.getString("state");
				if(state.equals("1")){
					state="已审核";
				}else{
					state="未审核";
				}
				po.setState(state);
				po.setAdate(rs.getString("adddate"));
				po.setOperator(rs.getString("adduser"));
				po.setOptname(rs.getString("addusername"));
				po.setOptip(rs.getString("addip"));
				poList.add(po);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="select count(code) from v_purorder";
		pb.setData(poList);
		pb.setRecordCount(super.executeTotalCount(sql));
		return pb;
	}

	@Override
	public PageBean findByConditions(Po po,int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		String sql="";
		String sql1="";
		ResultSet rs=null;
		int total=0;
		if(po.getCode()!=null){
			sql="select * from v_purorder where code=?";
			sql1="select count(code) from v_purorder where code=?";
			rs=super.executeQueryForPage(sql, new Object[]{po.getCode(),pageNo*pageSize,(pageNo-1)*pageSize});
			total=super.executeTotalCount(sql1, new Object[]{po.getCode()});
		}else{
			if(po.getOdate()!=null&&po.getDdate()==null){
				sql="select * from v_purorder where orderdate=?";
				sql1="select count(code) from v_purorder where orderdate=?";
				rs=super.executeQueryForPage(sql, new Object[]{po.getOdate(),pageNo*pageSize,(pageNo-1)*pageSize});
				total=super.executeTotalCount(sql1, new Object[]{po.getOdate()});
			}
			if(po.getOdate()!=null&&po.getDdate()!=null){
				sql="select * from v_purorder where orderdate=? and deliverydate=?";
				sql1="select count(code) from v_purorder where orderdate=? and deliverydate=?";
				rs=super.executeQueryForPage(sql, new Object[]{po.getOdate(),po.getDdate(),pageNo*pageSize,(pageNo-1)*pageSize});
				total=super.executeTotalCount(sql1, new Object[]{po.getOdate(),po.getDdate()});
			}
			if(po.getOdate()==null&&po.getDdate()!=null){
				sql="select * from v_purorder where deliverydate=?";
				sql1="select count(code) from v_purorder where deliverydate=?";
				rs=super.executeQueryForPage(sql, new Object[]{po.getDdate(),pageNo*pageSize,(pageNo-1)*pageSize});
				total=super.executeTotalCount(sql1, new Object[]{po.getDdate()});
			}
			if(po.getOdate()==null&&po.getDdate()==null&&po.getSupplier().getCsName()!=null){
				sql="select * from v_purorder where csname=?";
				sql1="select count(code) from v_purorder where csname=?";
				rs=super.executeQueryForPage(sql, new Object[]{po.getSupplier().getCsName(),pageNo*pageSize,(pageNo-1)*pageSize});
				total=super.executeTotalCount(sql1, new Object[]{po.getSupplier().getCsName()});
			}
		}
		List<Po> poList=new ArrayList<Po>();
		PageBean pb=new PageBean();
		Po po1=null;
		CustomerSupplier cs=null;
		try {
			while(rs.next()){
				po1=new Po();
				cs=new CustomerSupplier();
				po1.setCode(rs.getString("code"));
				po1.setOdate(rs.getString("orderdate"));
				cs.setCode(rs.getString("suppliercode"));
				cs.setCsName(rs.getString("csname"));
				po1.setSupplier(cs);
				po1.setLinkman(rs.getString("contacter"));
				po1.setTel(rs.getString("telphone"));
				po1.setFax(rs.getString("fax"));
				po1.setTrans(rs.getString("trans"));
				po1.setDdate(rs.getString("deliverydate"));
				po1.setBusinesser(rs.getString("businesser"));
				po1.setRemark(rs.getString("remarks"));
				po1.setIsshow(rs.getString("isshow"));
				po1.setNums(rs.getInt("nums"));
				po1.setAmount(rs.getDouble("numsprice"));
				String state=rs.getString("state");
				if(state.equals("1")){
					state="已审核";
				}else{
					state="未审核";
				}
				po1.setState(state);
				po1.setAdate(rs.getString("adddate"));
				po1.setOperator(rs.getString("adduser"));
				po1.setOptname(rs.getString("addusername"));
				po1.setOptip(rs.getString("addip"));
				poList.add(po1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		pb.setData(poList);
		pb.setRecordCount(total);
		return pb;
	}

	@Override
	public List<PoDetail> findByCode(String code) {
		// TODO Auto-generated method stub
		String sql="select * from v_purorder_detail where ocode=?";
		ResultSet rs=super.executeQuery(sql, code);
		List<PoDetail> pdList=new ArrayList<PoDetail>();
		PoDetail pd=null;
		BaseParts part=null;
		try {
			while(rs.next()){
				pd=new PoDetail();
				part=new BaseParts();
				pd.setDcode(rs.getString("dcode"));
				pd.setOcode(rs.getString("ocode"));
				pd.setXcode(rs.getString("xcode"));
				pd.setNums(rs.getInt("nums"));
				pd.setRkstate(rs.getString("rkstate"));
				pd.setRknums(rs.getInt("rknums"));
				pd.setPrice(rs.getInt("nums")*rs.getInt("saleprice"));
				pd.setLastPrice(0.00);
				part.setPartsCode(rs.getString("partscode"));
				part.setPartsName(rs.getString("partsname"));
				part.setPartsNo(rs.getString("partsNo"));
				part.setPartsBrand(rs.getString("partsbrand"));
				part.setPartsModel(rs.getString("partsmodel"));
				part.setSalePrice(rs.getString("saleprice"));
				pd.setPart(part);
				pdList.add(pd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return pdList;
	}

	@Override
	public List<PoDetail> findByXcode(String xcode,String ocode) {
		// TODO Auto-generated method stub
		String sql="select * from purchaseinquery_detail where xcode=?";
		ResultSet rs=super.executeQuery(sql, xcode);
		List<PoDetail> pdList=new ArrayList<PoDetail>();
		PoDetail pd=null;
		BaseParts part=null;
		try {
			while(rs.next()){
				pd=new PoDetail();
				part=new BaseParts();
				pd.setOcode(ocode);
				pd.setXcode(rs.getString("xcode"));
				pd.setNums(rs.getInt("nums"));
				part.setPartsCode(rs.getString("pcode"));
				part.setSalePrice(rs.getString("price"));
				pd.setPart(part);
				pdList.add(pd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return pdList;
	}
	@Override
	public int insertOrder(Po po) {
		// TODO Auto-generated method stub
		int ret=0;
		String sql="insert into purchaseorder(code,suppliercode,contacter,telphone,fax,trans,deliverydate,"
				+ "businesser,remarks,nums,numsprice,state,adduser,isshow,orderdate,adddate)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,'1',sysdate,sysdate)";
		ret=super.executeUpdate(sql, new Object[]{po.getCode(),po.getSupplier().getCode(),po.getLinkman(),
				po.getTel(),po.getFax(),po.getTrans(),po.getDdate(),po.getBusinesser(),po.getRemark(),
				po.getNums(),po.getAmount(),po.getState(),po.getOperator()});
		return ret;
	}

	@Override
	public int insertDetail(List<PoDetail> pdList) {
		// TODO Auto-generated method stub
		int ret=0;
		for(PoDetail pd:pdList){
			String sql="insert into purchaseorder_detail(dcode,ocode,xcode,pcode,nums,price,rkstate,rknums,remarks) "
					+ "values(seq_dcode.nextval,?,?,?,?,?,?,?,?)";
			ret=super.executeUpdate(sql, new Object[]{pd.getOcode(),pd.getXcode(),pd.getPart().getPartsCode(),
					pd.getNums(),pd.getPart().getSalePrice(),pd.getRkstate(),pd.getRknums(),pd.getRemark()});
		}
		return ret;
	}

	@Override
	public int update(Po po) {
		// TODO Auto-generated method stub
		int ret=0;
		String sql="update purchaseorder set suppliercode=?,contacter=?,telphone=?,fax=?,trans=?,deliverydate=?,"
				+ "businesser=?,remarks=?,nums=?,numsprice=?,state=?,adduser=? where code=?";
		ret=super.executeUpdate(sql, new Object[]{po.getSupplier().getCode(),po.getLinkman(),
				po.getTel(),po.getFax(),po.getTrans(),po.getDdate(),po.getBusinesser(),po.getRemark(),
				po.getNums(),po.getAmount(),po.getState(),po.getOperator(),po.getCode()});
		return ret;
	}

	@Override
	public int delete(String code) {
		// TODO Auto-generated method stub
		int ret=0;
		String sql1="delete from purchaseorder where code="+"'"+code+"'";
		String sql2="delete from purchaseorder_detail where ocode="+"'"+code+"'";
		Connection conn=super.getConnection();
		Statement state=null;
		try {
			state=conn.createStatement();
			state.addBatch(sql1);
			state.addBatch(sql2);
			state.executeBatch();
			ret=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)  conn.close();
				if(state!=null) state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public List<CustomerSupplier> findSuplier() {
		// TODO Auto-generated method stub
		String sql="select * from basecustomersupplier";
		ResultSet rs=super.executeQuery(sql);
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
			cs.setFax(rs.getString("fax"));
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
		return csList;
	}

	@Override
	public List<BaseParts> findPart() {
		// TODO Auto-generated method stub
		String sql="select * from baseparts";
		ResultSet rs=super.executeQuery(sql);
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
		return partsList;
	}

	@Override
	public List<PurInQuery> findQueryOrder() {
		// TODO Auto-generated method stub
		String sql="select * from v_purinquery";
		ResultSet rs=super.executeQuery(sql);
		List<PurInQuery> piList=new ArrayList<PurInQuery>();
		PurInQuery pi=null;
		CustomerSupplier cs=null;
		try {
			while(rs.next()){
				pi=new PurInQuery();
				cs=new CustomerSupplier();
				pi.setCode(rs.getString("code"));
				pi.setAddDate(rs.getString("adddate"));
				cs.setCsName(rs.getString("csname"));
				pi.setSupplier(cs);
				pi.setNums(rs.getInt("nums"));
				pi.setNumsPrice(rs.getInt("numsprice"));
				pi.setNum(2);
				pi.setNumPrice(440);
				String state=rs.getString("state");
				if(state.equals("1")){
					state="完成";
				}else{
					state="未完成";
				}
				pi.setState(state);
				pi.setRemarks(rs.getString("remarks"));
				piList.add(pi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return piList;
	}

	@Override
	public int deleteDetail(String dcode) {
		// TODO Auto-generated method stub
		String sql="delete from purchaseorder_detail where dcode=?";
		return super.executeUpdate(sql, new Object[]{dcode});
	}


}
