package com.cscs.model.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cscs.model.bo.TB_CSCS;
import com.cscs.model.bo.Department;
import com.cscs.model.dao.CSCSDao;

public class CSCSService {
	private CSCSDao cscsDao;

	public void setCscsDao(CSCSDao newValue) {
		this.cscsDao = newValue;
	}

	public void saveAll(final List<TB_CSCS> entities) throws Exception {
		try {
			cscsDao.delTable();
			cscsDao.saveOrUpdateAll(entities);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<TB_CSCS> findByDept(Department dept) {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		detachedCriteria.addOrder(Order.asc("pcGaNo"));
		if (dept != null)
			detachedCriteria.add(Restrictions.eq("dept", dept));
		detachedCriteria.add(Restrictions.eq("codeType", "GA"));
		return cscsDao.findAllByCriteria(detachedCriteria);
	}

	public List<TB_CSCS> matchByComputer() {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		detachedCriteria.add(Restrictions.ilike("softwareName", "Win",
				MatchMode.ANYWHERE));
		return cscsDao.findAllByCriteria(detachedCriteria);
	}

	public List<String> groupBySoftName(Department dept) {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		detachedCriteria.addOrder(Order.asc("softwareName"));
		if (dept != null)
			detachedCriteria.add(Restrictions.eq("dept", dept));
		detachedCriteria.add(Restrictions.eq("codeType", "GA"));
		return cscsDao.getGroupByCriteria(detachedCriteria, "softwareName");
	}

	public List<String> findAllGaNo() {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("codeType", "GA"));
		return cscsDao.getGroupByCriteria(detachedCriteria, "codeNo");
	}

	public List<TB_CSCS> findStdPC(String gaNo) {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("pcGaNo", gaNo));
		detachedCriteria.addOrder(Order.asc("softwareName"));
		return cscsDao.findAllByCriteria(detachedCriteria);
	}

	private DetachedCriteria getMyDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TB_CSCS.class);
		return detachedCriteria;
	}

}