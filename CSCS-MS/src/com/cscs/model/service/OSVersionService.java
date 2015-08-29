package com.cscs.model.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.cscs.model.bo.OS_VER;
import com.cscs.model.dao.OSVersionDao;

public class OSVersionService {
	private OSVersionDao osVersionDao;

	public void setOsVersionDao(OSVersionDao newValue) {
		this.osVersionDao = newValue;
	}

	public List<OS_VER> findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(OS_VER.class);
		return osVersionDao.findAllByCriteria(detachedCriteria);
	}

}