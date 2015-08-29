package com.cscs.model.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cscs.model.dao.GenericDao;

public class GenericHibernateDaoImpl<T> extends HibernateDaoSupport implements
		GenericDao<T> {
	private Class<T> clazz;
	private Projection myProjection;

	@SuppressWarnings("unchecked")
	public GenericHibernateDaoImpl() {
		this.clazz = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T findById(int id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}

	public T findByStr(String str) {
		return (T) getHibernateTemplate().get(clazz, str);
	}

	public void update(T entity) {
		try {
			getHibernateTemplate().saveOrUpdate(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria) {
		try {
			List<T> results = getHibernateTemplate().findByCriteria(criteria);
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void saveOrUpdateAll(final List<T> entities) {
		try {
			getHibernateTemplate().saveOrUpdateAll(entities);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public int delTable() {
		return getHibernateTemplate().bulkUpdate(
				"delete from " + clazz.getSimpleName());
	}

	public void deleteAll(final List<T> entities) {
		try {
			getHibernateTemplate().deleteAll(entities);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAllByCriteria(DetachedCriteria criteria) {
		List<T> li = getHibernateTemplate().findByCriteria(criteria);
		return li;
	}

	@SuppressWarnings("unchecked")
	public List<T> findPageByCriteria(DetachedCriteria criteria, int firstResult,
			int size) {
		try {
			List<T> results = getHibernateTemplate().findByCriteria(criteria,
					firstResult, size);
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		Number count = (Number) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria.getExecutableCriteria(session);
						return criteria.setProjection(Projections.rowCount())
								.uniqueResult();
					}
				});
		return (count == null) ? 0 : count.intValue();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> getGroupByCriteria(
			final DetachedCriteria detachedCriteria, final String tableName) {
		List<String> groupList = getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria.getExecutableCriteria(session);
						return criteria.setProjection(Projections.groupProperty(tableName))
								.list();
					}
				});
		return groupList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object[]> getGroupByProjection(
			final DetachedCriteria detachedCriteria, final String[] tableName) {
		List<Object[]> groupList = getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria.getExecutableCriteria(session);
						ProjectionList myList = Projections.projectionList();
						for (int i = 0; i < tableName.length; i++)
							myProjection = myList.add(Projections.groupProperty(tableName[i]));
						return criteria.setProjection(myProjection).list();
					}
				});
		return groupList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object[]> getPageByCriteria(
			final DetachedCriteria detachedCriteria, final String[] tableName,
			final int firstRow, final int maxSize) {
		List<Object[]> groupList = getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria.getExecutableCriteria(session);
						ProjectionList myList = Projections.projectionList();
						for (int i = 0; i < tableName.length; i++)
							myProjection = myList.add(Projections.groupProperty(tableName[i]));
						return criteria.setProjection(myProjection)
								.setFirstResult(firstRow).setMaxResults(maxSize).list();
					}
				});
		return groupList;
	}

}