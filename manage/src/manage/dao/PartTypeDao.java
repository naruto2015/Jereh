package manage.dao;

import java.util.List;

import manage.entity.PageBean;
import manage.entity.PartType;

public interface PartTypeDao {
	public PageBean findAll(int pageNo,int pageSize);
	public PageBean findByName(String name,int pageNo,int pageSize);
	public List<String> findAllName();
	public int insert(PartType part);
	public int update(PartType part);
	public int delete(String id);
}
