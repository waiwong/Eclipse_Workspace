package com.cscs.model.service;

import java.util.List;

import com.cscs.model.bo.SYS_DEFAULT;
import com.cscs.model.dao.SystemDefaultDao;

public class SystemDefaultService {
	private SystemDefaultDao defaultDao;

	public void setDefaultDao(SystemDefaultDao newValue) {
		this.defaultDao = newValue;
	}

	public void saveAll(final List<SYS_DEFAULT> entities) throws Exception {
		try {
			defaultDao.saveOrUpdateAll(entities);
		} catch (Exception e) {
			throw e;
		}
	}

}