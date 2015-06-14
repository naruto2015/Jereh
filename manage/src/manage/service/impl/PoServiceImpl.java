package manage.service.impl;

import java.util.List;

import com.manage.entity.CustomerSupplier;
import com.root.base.entity.BaseParts;

import manage.dao.PoDao;
import manage.dao.impl.PoDaoImpl;
import manage.entity.PageBean;
import manage.entity.Po;
import manage.entity.PoDetail;
import manage.entity.PurInQuery;
import manage.service.PoService;

public class PoServiceImpl implements PoService {

	private PoDao poDao=new PoDaoImpl();
	@Override
	public PageBean getByConditions(Po po, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageBean pb=null;
		if(po.getCode()==null&&po.getOdate()==null&&po.getDdate()==null&&po.getSupplier().getCsName()==null){
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
	public int addOrder(Po po) {
		// TODO Auto-generated method stub
		return poDao.insertOrder(po);
	}

	@Override
	public int addDetial(List<PoDetail> pdList) {
		// TODO Auto-generated method stub
		return poDao.insertDetail(pdList);
	}

	@Override
	public int update(Po po) {
		// TODO Auto-generated method stub
		return poDao.update(po);
	}

	@Override
	public int delete(String code) {
		// TODO Auto-generated method stub
		return poDao.delete(code);
	}

	@Override
	public List<CustomerSupplier> getSupplier() {
		// TODO Auto-generated method stub
		return poDao.findSuplier();
	}

	@Override
	public List<BaseParts> getPart() {
		// TODO Auto-generated method stub
		return poDao.findPart();
	}

	@Override
	public List<PurInQuery> getQueryOrder() {
		// TODO Auto-generated method stub
		return poDao.findQueryOrder();
	}

	@Override
	public List<PoDetail> getDetailByXcode(String xcode, String ocode) {
		// TODO Auto-generated method stub
		return poDao.findByXcode(xcode, ocode);
	}

	@Override
	public int delDetail(String dcode) {
		// TODO Auto-generated method stub
		return poDao.deleteDetail(dcode);
	}


}
