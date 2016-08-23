package fr.aoufi.annuaire.dao;

import java.util.List;

import fr.aoufi.annuaire.exception.DAOException;
import fr.aoufi.annuaire.model.Numero;
import fr.aoufi.annuaire.model.Personne;

public interface NumeroDao extends GenericDao<Numero, Integer> {
	
	Numero findByType (String type) 					  throws DAOException;	
	List<Numero> findAllByPersonne (Personne personne) throws DAOException;

}
