package com.cscs.model.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cscs.model.bo.SMSView;
import com.cscs.model.dao.SMSViewDao;

public class SMSViewService {
	private SMSViewDao smsViewDao;

	public void setSmsViewDao(SMSViewDao smsViewDao) {
		this.smsViewDao = smsViewDao;
	}

	public List<SMSView> findPageBySoftware(String softwareName, int firstRow) {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		detachedCriteria.addOrder(Order.asc("flag"));
		if (softwareName != null && !softwareName.equals(""))
			detachedCriteria.add(Restrictions.ilike("softwareName", softwareName,
					MatchMode.ANYWHERE));
		return smsViewDao.findPagedByCriteria(detachedCriteria, firstRow, 20);
	}

	public int getCountOfSoftware(String softwareName) {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		if (softwareName != null && !softwareName.equals(""))
			detachedCriteria.add(Restrictions.ilike("softwareName", softwareName,
					MatchMode.ANYWHERE));
		return smsViewDao.getCountByCriteria(detachedCriteria);
	}

	private DetachedCriteria getMyDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SMSView.class);
		return detachedCriteria;
	}

}