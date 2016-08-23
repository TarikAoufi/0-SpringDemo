package fr.aoufi.annuaire.dao;

import java.util.List;
import java.util.Map;

import fr.aoufi.annuaire.exception.DAOException;


public interface GenericDao<T, k> {

	T create(T entity) 								 throws DAOException;
	void deleteById(k key) 							 throws DAOException;
	void delete(T entity) 							 throws DAOException;
	T update(T entity) 								 throws DAOException;
	T findById(k key) 								 throws DAOException;
	List<T> findAll() 								 throws DAOException;	
	List<T> findWithParam(Map<String, Object> param) throws DAOException;
	
}