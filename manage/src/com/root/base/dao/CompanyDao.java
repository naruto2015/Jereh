package com.root.base.dao;

import com.root.base.entity.BaseCompany;

/**
 * ��˾��Ϣ����
 * @author ���Ǿ�
 * @date 2015-05-28
 */
public interface CompanyDao {
	
	 
	//��ȡ��˾��Ϣ
	public BaseCompany findBaseCompany(int code);
	//�����Ϣ
	public int addBaseCompany(BaseCompany company);
	
	
}
