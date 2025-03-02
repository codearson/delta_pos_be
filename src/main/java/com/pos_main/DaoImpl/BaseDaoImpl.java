package com.pos_main.DaoImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pos_main.Dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {

	Class<T> type;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void create(T model) {
		getCurrentSession().save(model);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public T read(Long id) {
		return (T) getCurrentSession().get(type, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> readAll() {
		Criteria criteria = getCurrentSession().createCriteria(type);
		return criteria.list();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void update(T model) {
		getCurrentSession().update(model);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(T model) {
		getCurrentSession().delete(model);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public T merge(T model) {
		return (T) getCurrentSession().merge(model);
	}

	public Session getCurrentSession() {
		Session session = entityManager.unwrap(org.hibernate.Session.class);
		return session;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public T saveOrUpdate(T model) {
		getCurrentSession().saveOrUpdate(model);
		return model;
	}

}
