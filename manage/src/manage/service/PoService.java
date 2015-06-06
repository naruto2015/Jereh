package manage.service;

import java.util.List;

import manage.entity.PageBean;
import manage.entity.Po;
import manage.entity.PoDetail;

public interface PoService {
	public PageBean getByConditions(Po po,int pageNo,int pageSize);
	public List<PoDetail> getDetailByCode(String code);
	public int add(Po po,PoDetail pd);
	public int update(Po po,PoDetail pd);
	public int delete(String code);
}
