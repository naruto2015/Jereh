package manage.dao;

import java.util.List;

import com.manage.entity.CustomerSupplier;
import com.root.base.entity.BaseParts;

import manage.entity.PageBean;
import manage.entity.Po;
import manage.entity.PoDetail;
import manage.entity.PurInQuery;

public interface PoDao {
	
	public PageBean findAll(int pageNo,int pageSize);
	public PageBean findByConditions(Po po,int pageNo,int pageSize);
	public List<PoDetail> findByCode(String code);
	public List<PoDetail> findByXcode(String xcode,String ocode);
	public List<CustomerSupplier> findSuplier();
	public List<BaseParts> findPart();
	public List<PurInQuery> findQueryOrder();
	public int insertOrder(Po po);
	public int insertDetail(List<PoDetail> pdList);
	public int update(Po po);
	public int delete(String code);
	public int deleteDetail(String dcode);

}
