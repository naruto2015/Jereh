package com.root.base.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDao {
	private static final String DRIVER_CLASS="oracle.jdbc.driver.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@localhost:1525:orcl";
	private static final String USER="root";
	private static final String PWD="123";
	
	private Connection conn=null;
	private PreparedStatement pstm=null;
	private ResultSet rs=null;
	
	/**
	 * ��ȡ���ӵķ���
	 * @return
	 */
	public Connection getConnection(){
//		Context context=null;
//		try {
//			context = new InitialContext();
//			DataSource ds=(DataSource)context.lookup("java:comp/env/jdbc/news");
//			conn=ds.getConnection();
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			Class.forName(DRIVER_CLASS);
			conn=DriverManager.getConnection(URL,USER,PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * ʹ��JDBCʵ����ɾ��,����ռλ��
	 * @param sql
	 * @return
	 */
	public int executeUpdate(String sql){
//		return this.executeUpdate(sql,new Object[]{});
		
		int ret=0;
		//�������
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			ret=pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	/**
	 * ʹ��JDBCʵ����ɾ��,��һ��ռλ��
	 * @param sql
	 * @param params ����ռλ������
	 * @return
	 */
	public int executeUpdate(String sql,Object params){
		return this.executeUpdate(sql,new Object[]{params});
	}
	
	/**
	 * ʹ��JDBCʵ����ɾ��,����ռλ��
	 * @param sql
	 * @param params ����ռλ������
	 * @return
	 */
	public int executeUpdate(String sql,Object[] params){
		int ret=0;
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstm.setObject(i+1,params[i]);
				}
			}
			ret=pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	/**
	 * ʹ��JDBCʵ����ɾ��,��ռλ��
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeUpdate(String sql,List params){
		int ret=0;
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.size();i++){
					pstm.setObject(i+1,params.get(i));
				}
			}
			ret=pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * ʵ�ֲ�ѯ������ռλ��
	 * @param sql
	 * @return
	 */
	public ResultSet executeQuery(String sql){
		return executeQuery(sql,new Object[]{});
	}

	/**
	 * ʵ�ֲ�ѯ����һ��ռλ��
	 * @param sql
	 * @param params
	 * @return
	 */
	public ResultSet executeQuery(String sql,Object params){
		return executeQuery(sql,new Object[]{params});
	}
	
	/**
	 * ʵ�ֲ�ѯ,��ռλ��
	 * @param sql
	 * @param params
	 * @return
	 */
	public ResultSet executeQuery(String sql,List params){
		return executeQuery(sql,params.toArray());
	}
	
	/**
	 * ʵ�ֲ�ѯ����ռλ��
	 * @param sql
	 * @param params
	 * @return
	 */
	public ResultSet executeQuery(String sql,Object[] params){
		conn=this.getConnection();
		try {
			pstm=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstm.setObject(i+1, params[i]);
				}
			}
			rs=pstm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * �ر���Դ
	 */
	public void closeStatement(){
		try {
			if(pstm!=null)pstm.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQueryForPage(String sql,int pageNo,int pageSize){
		String pageSql="select * from(select rownum r,x.* from(";pageSql+=sql+") x where rownum<=?)where r>?";
		return this.executeQuery(pageSql, new Object[]{pageNo*pageSize,(pageNo-1)*pageSize});
	}
	
	public ResultSet executeQueryForPage(String sql,Object[] params){
		String pageSql="select * from (select rownum r,x.* from (";pageSql+=sql+") x where rownum<=?) where r>?";
		return this.executeQuery(pageSql, params);
	}

	//�����²����id
	public int executeQueryForNewId(String sql){
		rs=this.executeQuery(sql);
		int newId=0;
		try {
			if(rs.next()){
				newId=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll();
		}
		return newId;
	}
	
	//�����¼��
	public int executeTotalCount(String sql){
		return executeTotalCount(sql,null);
	}
	
	//�����¼��
	public int executeTotalCount(String sql,Object[] params){
		rs=this.executeQuery(sql, params);
		int total=0;
		try {
			if(rs.next()){
				total=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll();
		}
		return total;
	}
	
	/**
	 * �ر���Դ
	 */
	public void closeAll(){
		try {
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
