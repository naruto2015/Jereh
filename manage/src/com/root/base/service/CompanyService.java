package com.root.base.service;

import com.root.base.entity.BaseCompany;

/**
 * ��˾��Ϣ����
 * @author ���Ǿ�
 * @date 2015-05-28
 */
public interface CompanyService {
	
	////��ȡ��˾��Ϣ
	public BaseCompany getCompanyInfo(int code);
	//�����Ϣ
	public int addCompany(BaseCompany company);
}
