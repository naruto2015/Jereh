package com.root.base.service.impl;


import com.root.base.dao.impl.BasePartsDao;
import com.root.base.entity.BaseParts;
import com.root.base.entity.PageBean;
import com.root.base.service.IBasePartsService;

public class BasePartsService implements IBasePartsService {
	private BasePartsDao basepart=new  BasePartsDao();
	@Override
	public PageBean getBasePartsList(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return basepart.findAll(pageNo, pageSize);
	}

	@Override
	public int updatePart(BaseParts part) {
		// TODO Auto-generated method stub
		return basepart.update(part);
	}

	@Override
	public int addPart(BaseParts part) {
		// TODO Auto-generated method stub
		return basepart.add(part);
	}

	@Override
	public int deletePart(String partscode) {
		// TODO Auto-generated method stub
		return basepart.delete(partscode);
	}

	@Override
	public PageBean getByNoNameCategory(BaseParts bp, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return basepart.findByNoNameCategory(bp, pageNo, pageSize);
	}

}
