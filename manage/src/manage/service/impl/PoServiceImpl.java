package manage.service.impl;

import java.util.List;

import manage.dao.PoDao;
import manage.dao.impl.PoDaoImpl;
import manage.entity.PageBean;
import manage.entity.Po;
import manage.entity.PoDetail;
import manage.service.PoService;

public class PoServiceImpl implements PoService {

	private PoDao poDao=new PoDaoImpl();
	@Override
	public PageBean getByConditions(Po po, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageBean pb=null;
		if(po==null){
			pb=poDao.findAll(pageNo, pageSize);
		}else{
			pb=poDao.findByConditions(po, pageNo, pageSize);
		}
		return pb;
	}

	@Override
	public List<PoDetail> getDetailByCode(String code) {
		// TODO Auto-generated method stub
		return poDao.findByCode(code);
	}

	@Override
	public int add(Po po, List<PoDetail> pdList) {
		// TODO Auto-generated method stub
		return poDao.insert(po, pdList);
	}

	@Override
	public int update(Po po, List<PoDetail> pdList) {
		// TODO Auto-generated method stub
		return poDao.update(po, pdList);
	}

	@Override
	public int delete(String code) {
		// TODO Auto-generated method stub
		return poDao.delete(code);
	}

}
