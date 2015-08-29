package com.cscs.model.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.cscs.model.bo.GAD_SOFT;
import com.cscs.model.dao.GadSoftwareDao;

public class GadSoftwareService {
	private GadSoftwareDao gadSoftwareDao;

	public void setGadSoftwareDao(GadSoftwareDao newValue) {
		this.gadSoftwareDao = newValue;
	}

	public void saveAll(final List<GAD_SOFT> entities) throws Exception {
		try {
			gadSoftwareDao.saveOrUpdateAll(entities);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<String> findAllGaNo() {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		detachedCriteria.add(Restrictions.ilike("gaNo", "3-", MatchMode.START));
		return gadSoftwareDao.getGroupByCriteria(detachedCriteria, "gaNo");
	}

	public List<GAD_SOFT> findByGaNo(List<String> gaNoList) {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		detachedCriteria.add(Restrictions.in("gaNo", gaNoList));
		return gadSoftwareDao.findAllByCriteria(detachedCriteria);
	}

	private DetachedCriteria getMyDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(GAD_SOFT.class);
		return detachedCriteria;
	}

}