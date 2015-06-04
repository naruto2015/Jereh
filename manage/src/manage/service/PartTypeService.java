package manage.service;

import java.util.List;

import manage.entity.PageBean;
import manage.entity.PartType;

public interface PartTypeService {
	public PageBean getByName(String name,int pageNo,int pageSize);
	public PageBean getAll(int pageNo,int pageSize);
	public List<String> getAllName();
	public int add(PartType part);
	public int update(PartType part);
	public int delete(String id);

}
