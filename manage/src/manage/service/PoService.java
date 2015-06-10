package manage.service;

import java.util.List;

import com.manage.entity.CustomerSupplier;
import com.root.base.entity.BaseParts;
import com.root.base.entity.purchaseInQuery;

import manage.entity.PageBean;
import manage.entity.Po;
import manage.entity.PoDetail;

public interface PoService {
	public PageBean getByConditions(Po po,int pageNo,int pageSize);
	public List<PoDetail> getDetailByCode(String code);
	public List<CustomerSupplier> getSupplier();
	public List<BaseParts> getPart();
	public List<purchaseInQuery> getQueryOrder();
	public int add(Po po,List<PoDetail> pdList);
	public int update(Po po,List<PoDetail> pdList);
	public int delete(String code);
}
