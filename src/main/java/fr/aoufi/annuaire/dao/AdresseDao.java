package fr.aoufi.annuaire.dao;

import fr.aoufi.annuaire.exception.DAOException;
import fr.aoufi.annuaire.model.Adresse;

public interface AdresseDao extends GenericDao<Adresse, Integer> {
	
	Adresse findByVille(String ville) throws DAOException;

}
