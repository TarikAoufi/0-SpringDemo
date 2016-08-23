package fr.aoufi.annuaire.service;

import java.util.List;
import java.util.Map;

import fr.aoufi.annuaire.exception.ServiceException;

public interface GenericService<T, k> {
	
	T create(T entity) 								 throws ServiceException;
	void deleteById(k key) 							 throws ServiceException;
	void delete(T entity) 							 throws ServiceException;
	T update(T entity) 								 throws ServiceException;
	T findById(k key) 								 throws ServiceException;
	List<T> findAll() 								 throws ServiceException;	
	List<T> findWithParam(Map<String, Object> param) throws ServiceException;

}
