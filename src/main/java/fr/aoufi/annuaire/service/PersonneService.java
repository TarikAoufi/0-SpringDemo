package fr.aoufi.annuaire.service;

import java.util.List;

import fr.aoufi.annuaire.exception.ServiceException;
import fr.aoufi.annuaire.model.Numero;
import fr.aoufi.annuaire.model.Personne;

public interface PersonneService extends GenericService<Personne, Integer> {

	List<Personne> findByName(String nom) 			throws ServiceException;
	List<Personne> findAllByNumero (Numero numero) 	throws ServiceException;
	
}
