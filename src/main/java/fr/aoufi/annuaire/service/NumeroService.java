package fr.aoufi.annuaire.service;

import java.util.List;

import fr.aoufi.annuaire.exception.ServiceException;
import fr.aoufi.annuaire.model.Numero;
import fr.aoufi.annuaire.model.Personne;

public interface NumeroService extends GenericService<Numero, Integer> {
	
	Numero findByType(String type) 						throws ServiceException;
	List<Numero> findAllByPersonne(Personne personne) 	throws ServiceException;

}
