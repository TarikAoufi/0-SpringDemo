package fr.aoufi.annuaire.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import fr.aoufi.annuaire.dao.NumeroDao;
import fr.aoufi.annuaire.exception.DAOException;
import fr.aoufi.annuaire.exception.ServiceException;
import fr.aoufi.annuaire.model.Numero;
import fr.aoufi.annuaire.model.Personne;
import fr.aoufi.annuaire.service.NumeroService;

public class NumeroServiceImpl implements NumeroService {
	
	private NumeroDao numeroDao;
	
	public NumeroServiceImpl() {

	}

	public NumeroServiceImpl(NumeroDao numeroDao) {
		this.numeroDao = numeroDao;
	}

	public NumeroDao getNumeroDAO() {
		return numeroDao;
	}

	public void setNumeroDAO(NumeroDao numeroDao) {
		this.numeroDao = numeroDao;
	}
	
	@Override
	@Transactional
	public Numero create(Numero numero) throws ServiceException {
		Numero num;
		try{
			num = numeroDao.create(numero);
		}catch(DAOException e){
			throw new ServiceException("Erreur : NumeroServiceImpl - create()" + e.getMessage(), e);
		}
		return num;
	}
	
	@Override	
	public Numero findById(Integer id) throws ServiceException {
		try {
			return numeroDao.findById(id);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : NumeroServiceImpl - findById()" + e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional
	public void deleteById(Integer id) throws ServiceException {
		try {
			numeroDao.deleteById(id);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : NumeroServiceImpl - deleteById()" + e.getMessage(), e);
		}	
	}
	
	@Override
	@Transactional
	public void delete(Numero numero) throws ServiceException {
		Numero num;
		try {
			num = numeroDao.findById(numero.getId());
			numeroDao.delete(num);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : NumeroServiceImpl - delete()" + e.getMessage(), e);
		}		
	}
	
	@Override
	@Transactional
	public Numero update(Numero numero) throws ServiceException {
		Numero num;
		try{
			num = numeroDao.update(numero);
		}catch(DAOException e){
			throw new ServiceException("Erreur : NumeroServiceImpl - update()" + e.getMessage(), e);
		}
		return num;
	}
	
	@Override
	public List<Numero> findAll() throws ServiceException {
		try {
			return numeroDao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("Erreur : NumeroServiceImpl - findAll()" + e.getMessage(), e);
		}
	}

	@Override
	public List<Numero> findAllByPersonne(Personne personne) throws ServiceException {
		try {
			return numeroDao.findAllByPersonne(personne);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : NumeroServiceImpl - findAllByPersonne()" + e.getMessage(), e);
		}
	}
	
	@Override
	public Numero findByType(String type) throws ServiceException {
		try {
			return numeroDao.findByType(type);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : NumeroServiceImpl - findByType()" + e.getMessage(), e);
		}
	}

	@Override
	public List<Numero> findWithParam(Map<String, Object> param) throws ServiceException {
		try {
			return numeroDao.findWithParam(param);			
		} catch (DAOException e) {
			throw new ServiceException("Erreur: NumeroServiceImpl - findWithParam() "+e.getMessage(), e);
		}
	}

}
