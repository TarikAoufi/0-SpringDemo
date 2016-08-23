package fr.aoufi.annuaire.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import fr.aoufi.annuaire.dao.AdresseDao;
import fr.aoufi.annuaire.exception.DAOException;
import fr.aoufi.annuaire.exception.ServiceException;
import fr.aoufi.annuaire.model.Adresse;
import fr.aoufi.annuaire.service.AdresseService;

public class AdresseServiceImpl implements AdresseService {

	private AdresseDao adresseDao;

	public AdresseServiceImpl() {

	}

	public AdresseServiceImpl(AdresseDao adresseDao) {
		this.adresseDao = adresseDao;
	}

	public AdresseDao getAdresseDAO() {
		return adresseDao;
	}

	public void setAdresseDAO(AdresseDao adresseDao) {
		this.adresseDao = adresseDao;
	}
	
	@Override
	@Transactional
	public Adresse create(Adresse adresse) throws ServiceException {
		Adresse adrs;
		try {
			adrs = adresseDao.create(adresse);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : AdresseServiceImpl - create()" + e.getMessage(), e);
		}
		return adrs;
	}
	
	@Override
	@Transactional
	public void deleteById(Integer id) throws ServiceException {
		try {
			adresseDao.deleteById(id);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : AdresseServiceImpl - deleteById()" + e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional
	public void delete(Adresse adresse) throws ServiceException {
		Adresse adrs;
		try {
			adrs = adresseDao.findById(adresse.getId());
			adresseDao.delete(adrs);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : AdresseServiceImpl - delete()" + e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional
	public Adresse update(Adresse adresse) throws ServiceException {
		Adresse adrs;
		try {
			adrs = adresseDao.update(adresse);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : AdresseServiceImpl - update()" + e.getMessage(), e);
		}
		return adrs;
	}

	@Override
	public List<Adresse> findAll() throws ServiceException {
		try {
			return adresseDao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("Erreur : AdresseServiceImpl - findAll()" + e.getMessage(), e);
		}
	}
	
	@Override
	public Adresse findByVille(String ville) throws ServiceException {
		try {
			return adresseDao.findByVille(ville);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : AdresseServiceImpl - findByVille()" + e.getMessage(), e);
		}
	}
	
	@Override
	public Adresse findById(Integer id) throws ServiceException {
		try {
			return adresseDao.findById(id);
		} catch (DAOException e) {
			throw new ServiceException("Erreur : AdresseServiceImpl - findById()" + e.getMessage(), e);
		}
	}

	@Override
	public List<Adresse> findWithParam(Map<String, Object> param) throws ServiceException {
		try {
			return adresseDao.findWithParam(param);			
		} catch (DAOException e) {
			throw new ServiceException("Erreur: AdresseServiceImpl - findWithParam() "+e.getMessage(), e);
		}
	}

}
