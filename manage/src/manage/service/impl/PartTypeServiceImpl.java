package manage.service.impl;

import java.util.List;

import manage.dao.PartTypeDao;
import manage.dao.impl.PartTypeDaoImpl;
import manage.entity.PageBean;
import manage.entity.PartType;
import manage.service.PartTypeService;

public class PartTypeServiceImpl implements PartTypeService {

	private PartTypeDao partTypeDao=new PartTypeDaoImpl();
	@Override
	public PageBean getByName(String name, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageBean pb=null;
		if(name==null){
			pb=partTypeDao.findAll(pageNo, pageSize);
		}else{
			pb=partTypeDao.findByName(name, pageNo, pageSize);
		}
		return pb;
	}

	@Override
	public int add(PartType part) {
		// TODO Auto-generated method stub
		return partTypeDao.insert(part);
	}

	@Override
	public int update(PartType part) {
		// TODO Auto-generated method stub
		return partTypeDao.update(part);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return partTypeDao.delete(id);
	}

	@Override
	public PageBean getAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return partTypeDao.findAll(pageNo, pageSize);
	}

	@Override
	public List<String> getAllName() {
		// TODO Auto-generated method stub
		return partTypeDao.findAllName();
	}

}
