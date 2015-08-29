package com.cscs.model.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface GenericDao<T> {
	int getCountByCriteria(final DetachedCriteria detachedCriteria);

	List<T> findPagedByCriteria(DetachedCriteria criteria, int firstResult,
			int size);

}
