package fr.aoufi.annuaire.service;

import fr.aoufi.annuaire.exception.ServiceException;
import fr.aoufi.annuaire.model.Adresse;

public interface AdresseService extends GenericService<Adresse, Integer> {
	
	Adresse findByVille(String ville) 		throws ServiceException;

}
