package fr.aoufi.annuaire.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.aoufi.annuaire.dao.GenericDao;
import fr.aoufi.annuaire.exception.DAOException;

public class GenericDaoImpl<T,k> implements GenericDao<T, k> {
	
	@PersistenceContext
	protected EntityManager em;

	protected Class<T> type;

	public GenericDaoImpl(Class<T> type) {
		this.type = type;
	}
	
	@Override
	public T findById(k id) {
		try {
			return em.find(type, id);
		} catch (NoResultException e) {
			throw new DAOException("Erreur : GenericDaoImpl - findById() " + e.getMessage(), e);
		}
	}
	
	@Override
	public List<T> findAll() {
		try {
			TypedQuery<T> query = em.createQuery("SELECT e FROM " + type.getSimpleName() + " e ", type);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("Erreur : GenericDaoImpl - findAll() " + e.getMessage(), e);
		}

	}
	
	@Override
	public T create(T entity) throws DAOException {
		try {
			em.persist(entity);
			em.flush(); //force insert to receive the id of the entity
		} catch (PersistenceException e) {
			throw new DAOException("Erreur : GenericDaoImpl - create() " + e.getMessage(), e);
		}
		return entity;
	}
	
	@Override
	public void deleteById(k id) throws DAOException {
		try {
			delete(em.find(type, id));
		} catch (PersistenceException e) {
			throw new DAOException("Erreur : GenericDaoImpl - deleteById() " + e.getMessage(), e);
		}
	}
	
	@Override
	public void delete(T entity) throws DAOException {
		try {
			em.remove(entity);
		} catch (PersistenceException e) {
			throw new DAOException("Erreur : GenericDaoImpl - delete() " + e.getMessage(), e);
		}
	}
	
	@Override
	public T update(T entity) throws DAOException {
		try {
			em.merge(entity);
		} catch (PersistenceException e) {
			throw new DAOException("Erreur : GenericDaoImpl - update() " + e.getMessage(), e);
		}
		return entity;
	}
	
	@Override
	public List<T> findWithParam(Map<String, Object> param) throws DAOException {
		try {
			StringBuilder builder = new StringBuilder("Select e from");
			builder.append(type.getSimpleName()).append(" e ");
	
			TypedQuery<T> tQuery;
	
			if (param != null && param.size() > 0) {
				builder.append(" WHERE ");
				Set<String> keySet = param.keySet();
				for (String field : keySet) {
					builder.append(field).append(" = :" + field + "AND ");
				}
	
				String query = builder.substring(0, builder.lastIndexOf("AND"));
				tQuery = em.createQuery(query, type);
	
				for (String field : keySet) {
					tQuery.setParameter(field, param.get(field));
				}
	
			} else {
				tQuery = em.createQuery(builder.toString(), type);
			}
	
			return tQuery.getResultList();
		}  catch (PersistenceException|NullPointerException pn) {
			throw new DAOException("Erreur: GenericDaoImpl - findWithParam() " + pn.getMessage(), pn);
		}
	}

}
