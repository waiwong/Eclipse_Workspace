package com.cscs.model.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cscs.model.bo.Department;
import com.cscs.model.dao.DepartmentDao;

public class DepartmentService {
	private DepartmentDao departmentDao;

	public void setDepartmentDao(DepartmentDao newValue) {
		this.departmentDao = newValue;
	}

	public List<Department> findByDeptNameList(List<String> deptNameList) {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		detachedCriteria.add(Restrictions.in("name", deptNameList));
		return departmentDao.findAllByCriteria(detachedCriteria);
	}

	public List<Department> findAllDept(String tableName, boolean sort) {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		if (sort)
			detachedCriteria.addOrder(Order.asc(tableName));
		else
			detachedCriteria.addOrder(Order.desc(tableName));
		return departmentDao.findAllByCriteria(detachedCriteria);
	}

	private DetachedCriteria getMyDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Department.class);
		return detachedCriteria;
	}

}