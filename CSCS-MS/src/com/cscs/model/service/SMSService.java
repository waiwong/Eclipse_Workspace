package com.cscs.model.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cscs.model.bo.Computer;
import com.cscs.model.bo.SMS;
import com.cscs.model.dao.SMSDao;

public class SMSService {
	private SMSDao smsDao;

	public void setSmsDao(SMSDao newValue) {
		this.smsDao = newValue;
	}

	public void saveAll(final List<SMS> entities) throws Exception {
		try {
			smsDao.delTable();
			smsDao.saveOrUpdateAll(entities);
		} catch (Exception e) {
			throw e;
		}
	}

	public void update(SMS instance) throws Exception {
		try {
			smsDao.update(instance);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<SMS> findByComputer(Computer computer) {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		detachedCriteria.addOrder(Order.asc("softwareName"));
		detachedCriteria.add(Restrictions.eq("computer", computer));
		detachedCriteria.add(Restrictions.isNotNull("folder"));
		return smsDao.findAllByCriteria(detachedCriteria);
	}

	private DetachedCriteria getMyDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SMS.class);
		return detachedCriteria;
	}

}