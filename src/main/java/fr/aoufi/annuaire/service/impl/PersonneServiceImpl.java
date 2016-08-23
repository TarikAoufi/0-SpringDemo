package fr.aoufi.annuaire.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import fr.aoufi.annuaire.dao.PersonneDao;
import fr.aoufi.annuaire.exception.DAOException;
import fr.aoufi.annuaire.exception.ServiceException;
import fr.aoufi.annuaire.model.Numero;
import fr.aoufi.annuaire.model.Personne;
import fr.aoufi.annuaire.service.PersonneService;


public class PersonneServiceImpl implements PersonneService {

	private PersonneDao personneDao;

	public PersonneServiceImpl() {
	}

	public PersonneServiceImpl(PersonneDao personneDao) {
		this.personneDao = personneDao;
	}

	public void init() {
		System.out.println("Methode d'initialisation");
	}
	
	public void destroy() {
		System.out.println("Methode de destruction");
	}
	
	public PersonneDao getPersonneDAO() {
		return personneDao;
	}

	public void setPersonneDAO(PersonneDao personneDao) {
		this.personneDao = personneDao;
	}
	
	@Transactional
	public Personne create(Personne personne) throws ServiceException {		
		Personne pers;
		try{
			pers = personneDao.create(personne);
		}catch(DAOException e){
			throw new ServiceException("Erreur : PersonneServiceImpl - create()" + e.getMessage(), e);
		}
		return pers;
	}
	
	@Transactional
	public Personne update(Personne personne) throws ServiceException {
		Personne pers;
		try{
			pers = personneDao.update(personne);
		}catch(DAOException e){
			throw new ServiceException("Erreur : PersonneServiceImpl - update()" + e.getMessage(), e);
		}
		return pers;
	}
	
	@Transactional
	public void deleteById(Integer id) throws ServiceException {
		try {
			 personneDao.deleteById(id);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : PersonneServiceImpl - deleteById()" + e.getMessage(), e);
		}		
	}
	
	@Transactional
	public void delete(Personne personne) throws ServiceException {
		Personne pers;
		try {
			 pers = personneDao.findById(personne.getId());
			 personneDao.delete(pers);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : PersonneServiceImpl - delete()" + e.getMessage(), e);
		}		
	}
	
	@Override
	public List<Personne> findAll() throws ServiceException {
		try {
			return personneDao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("Erreur : PersonneServiceImpl - findAll()" + e.getMessage(), e);
		}
	}
	
	@Override
	public List<Personne> findAllByNumero(Numero numero) throws ServiceException {
		try {
			return personneDao.findAllByNumero(numero);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : PersonneServiceImpl - findAllByNumero()" + e.getMessage(), e);
		}
	}
	
	@Override
	public Personne findById(Integer id) throws ServiceException {
		try {
			return personneDao.findById(id);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : PersonneServiceImpl - findById()" + e.getMessage(), e);
		}
	}
	
	@Override
	public List<Personne> findByName(String nom) throws ServiceException {
		try {
			return personneDao.findByName(nom);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : PersonneServiceImpl - findByName()" + e.getMessage(), e);
		}
	}

	@Override
	public List<Personne> findWithParam(Map<String, Object> param) throws ServiceException {
		try {
			return personneDao.findWithParam(param);			
		} catch (DAOException e) {
			throw new ServiceException("Erreur: PersonneServiceImpl - findWithParam() "+e.getMessage(), e);
		}
	}

}
