package com.root.base.service.impl;

import com.root.base.dao.impl.ReturnDao;
import com.root.base.entity.PageBean;
import com.root.base.service.IReturnService;

public class ReturnService implements IReturnService {
	private ReturnDao rd=new ReturnDao();
	@Override
	public PageBean getReturnList(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return rd.findAll(pageNo, pageSize);
	}

	@Override
	public int delReturn(String code) {
		// TODO Auto-generated method stub
		return 0;
	}

}
