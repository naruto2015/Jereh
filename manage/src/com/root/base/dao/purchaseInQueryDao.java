package com.root.base.dao;

import com.root.base.entity.purchaseInQuery;
import com.root.base.entity.purchaseQuery;
 

public interface purchaseInQueryDao {

	
	public void insertpurchase(purchaseInQuery pcq);
	
	public void findAndbyConditions(int pageNO,int pageSize,purchaseQuery pq);
}
