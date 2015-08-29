package com.cscs.model.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cscs.model.bo.Computer;
import com.cscs.model.bo.Department;
import com.cscs.model.dao.ComputerDao;

public class ComputerService {
	private ComputerDao computerDao;

	public void setComputerDao(ComputerDao newValue) {
		this.computerDao = newValue;
	}

	public void saveAll(final List<Computer> entities) throws Exception {
		try {
			computerDao.delTable();
			computerDao.saveOrUpdateAll(entities);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Computer> findAll() {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		return computerDao.findAllByCriteria(detachedCriteria);
	}

	public List<Computer> findByDept(Department dept) {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		detachedCriteria.addOrder(Order.asc("name"));
		if (dept != null)
			detachedCriteria.add(Restrictions.eq("dept", dept));
		return computerDao.findAllByCriteria(detachedCriteria);
	}

	private DetachedCriteria getMyDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Computer.class);
		return detachedCriteria;
	}

}