package com.cscs.model.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.cscs.model.bo.TB_LOG;
import com.cscs.model.dao.RecordDao;

public class RecordService {
	private RecordDao recordDao;

	public void setRecordDao(RecordDao newValue) {
		this.recordDao = newValue;
	}

	public void update(TB_LOG instance) throws Exception {
		try {
			recordDao.update(instance);
		} catch (Exception e) {
			throw e;
		}
	}

	public TB_LOG findByTableName(String tableName) {
		return recordDao.findByStr(tableName);
	}

	public List<TB_LOG> findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TB_LOG.class);
		detachedCriteria.addOrder(Order.asc("index"));
		return recordDao.findAllByCriteria(detachedCriteria);
	}

}