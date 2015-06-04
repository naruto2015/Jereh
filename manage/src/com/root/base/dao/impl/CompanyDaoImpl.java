package com.root.base.dao.impl;

import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;

import com.root.base.dao.BaseDao;
import com.root.base.dao.CompanyDao;
import com.root.base.entity.BaseCompany;

/**
 * ��˾��Ϣ����
 * @author ���Ǿ�
 * @date 2015-05-28
 */
public class CompanyDaoImpl extends BaseDao implements CompanyDao {

	//��ӹ�˾��Ϣ
	@Override
	public int addBaseCompany(BaseCompany company) {
		// TODO Auto-generated method stub
		String sql="insert into basecompany(code, compname, compaddress, comppostcode, compphone,"+
           " compfax, compurl, compemail,complegaler, compagent, compaccount, compbank,comptax,remarks)"+
            " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		 
		return super.executeUpdate(sql,new Object[]{company.getCode(),company.getCompName(),company.getCompAddress(),company.getCompPostCode(),
				company.getCompPhone(),company.getCompFax(),company.getCompUrl(),company.getCompEmail(),company.getCompEgaler(),
				company.getCompAgent(),company.getCompAccount(),company.getCompBank(),company.getCompTax(),company.getRemarks()});
	}

	//��ȡ��Ϣ
	@Override
	public BaseCompany findBaseCompany(int code) {
		// TODO Auto-generated method stub
		String sql="select * from basecompany where code=?";
		ResultSet rs=super.executeQuery(sql,code);
		BaseCompany comp=null;
		try {
			while(rs.next()){
				comp=new BaseCompany();
				comp.setCode(rs.getString("code"));
				comp.setCompName(rs.getString("compname"));
				comp.setCompAddress(rs.getString("compaddress"));
				comp.setCompPostCode(rs.getString("comppostcode"));
				comp.setCompPhone(rs.getString("compphone"));
				comp.setCompFax(rs.getString("compfax"));
				comp.setCompUrl(rs.getString("compurl"));
				comp.setCompEmail(rs.getString("compemail"));
				comp.setCompEgaler(rs.getString("complegaler"));
				comp.setCompAgent(rs.getString("compagent"));
				comp.setCompAccount(rs.getString("compaccount"));
				comp.setCompBank(rs.getString("compbank"));
				comp.setCompTax(rs.getString("comptax"));
				comp.setRemarks(rs.getString("remarks"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return comp;
	}

	public static void main(String[] args){
		CompanyDao cd=new CompanyDaoImpl();
		BaseCompany comp=cd.findBaseCompany(1);
		System.out.println(comp.getCode());
	}
}
