package manage.dao;

import java.util.List;

import manage.entity.PageBean;
import manage.entity.Po;
import manage.entity.PoDetail;

public interface PoDao {
	
	public PageBean findAll(int pageNo,int pageSize);
	public PageBean findByConditions(Po po,int pageNo,int pageSize);
	public List<PoDetail> findByCode(String code);
	public int insert(Po po,PoDetail pd);
	public int update(Po po,PoDetail pd);
	public int delete(String code);

}
