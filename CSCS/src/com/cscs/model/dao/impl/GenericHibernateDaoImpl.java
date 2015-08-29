package com.cscs.model.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cscs.model.dao.GenericDao;

public class GenericHibernateDaoImpl<T> extends HibernateDaoSupport implements
		GenericDao<T> {

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public List<T> findPagedByCriteria(DetachedCriteria criteria,
			int firstResult, int size) {
		try {
			List<T> results = getHibernateTemplate().findByCriteria(criteria,
					firstResult, size);
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

}