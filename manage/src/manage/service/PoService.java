package manage.service;

import java.util.List;

import com.manage.entity.CustomerSupplier;
import com.root.base.entity.BaseParts;

import manage.entity.PageBean;
import manage.entity.Po;
import manage.entity.PoDetail;
import manage.entity.PurInQuery;

public interface PoService {
	public PageBean getByConditions(Po po,int pageNo,int pageSize);
	public List<PoDetail> getDetailByCode(String code);
	public List<PoDetail> getDetailByXcode(String xcode,String ocode);
	public List<CustomerSupplier> getSupplier();
	public List<BaseParts> getPart();
	public List<PurInQuery> getQueryOrder();
	public int addOrder(Po po);
	public int addDetial(List<PoDetail> pdList);
	public int update(Po po);
	public int delete(String code);
	public int delDetail(String dcode);
}
