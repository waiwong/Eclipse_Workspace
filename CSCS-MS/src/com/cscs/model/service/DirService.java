package com.cscs.model.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.cscs.model.bo.DIR_DISPLAY;
import com.cscs.model.dao.DirDao;

public class DirService {
	private DirDao dirDao;

	public void setDirDao(DirDao newValue) {
		this.dirDao = newValue;
	}

	public void saveAll(final List<DIR_DISPLAY> entities) throws Exception {
		try {
			dirDao.delTable();
			dirDao.saveOrUpdateAll(entities);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<DIR_DISPLAY> findAll() {
		DetachedCriteria detachedCriteria = getMyDetachedCriteria();
		return dirDao.findAllByCriteria(detachedCriteria);
	}
	
	private DetachedCriteria getMyDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DIR_DISPLAY.class);
		return detachedCriteria;
	}

}