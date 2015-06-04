package com.root.base.service;

import com.root.base.entity.BaseParts;
import com.root.base.entity.PageBean;

public interface IBasePartsService {
	public PageBean getBasePartsList(int pageNo,int pageSize);
	
	public int updatePart(BaseParts part);
	
	public int addPart(BaseParts part);
	
	public int deletePart(String partscode);
	
	public PageBean getByNoNameCategory(BaseParts bp,int pageNo,int pageSize);
}
