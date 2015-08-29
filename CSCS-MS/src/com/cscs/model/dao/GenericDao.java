package com.cscs.model.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface GenericDao<T> {

	T findById(int id);

	T findByStr(String str);

	int delTable();

	int getCountByCriteria(DetachedCriteria criteria);

	void update(T entity);

	void saveOrUpdateAll(List<T> entities);

	void deleteAll(List<T> entities);

	List<T> findAllByCriteria(DetachedCriteria criteria);

	List<T> findPageByCriteria(DetachedCriteria criteria, int firstResult,
			int size);

	List<String> getGroupByCriteria(final DetachedCriteria criteria,
			final String tableName);

	List<Object[]> getPageByCriteria(final DetachedCriteria detachedCriteria,
			final String[] tableName, final int firstRow, final int maxSize);

	List<Object[]> getGroupByProjection(final DetachedCriteria criteria,
			final String[] tableName);

}
