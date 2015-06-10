package manage.dao;

import java.util.List;

import com.manage.entity.CustomerSupplier;
import com.root.base.entity.BaseParts;
import com.root.base.entity.purchaseInQuery;

import manage.entity.PageBean;
import manage.entity.Po;
import manage.entity.PoDetail;

public interface PoDao {
	
	public PageBean findAll(int pageNo,int pageSize);
	public PageBean findByConditions(Po po,int pageNo,int pageSize);
	public List<PoDetail> findByCode(String code);
	public List<CustomerSupplier> findSuplier();
	public List<BaseParts> findPart();
	public List<purchaseInQuery> findQueryOrder();
	public int insert(Po po,List<PoDetail> pdList);
	public int update(Po po,List<PoDetail> pdList);
	public int delete(String code);

}
