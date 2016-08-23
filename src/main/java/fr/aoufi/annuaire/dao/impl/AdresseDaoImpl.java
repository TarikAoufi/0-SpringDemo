package fr.aoufi.annuaire.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.aoufi.annuaire.dao.AdresseDao;
import fr.aoufi.annuaire.exception.DAOException;
import fr.aoufi.annuaire.model.Adresse;

public class AdresseDaoImpl extends GenericDaoImpl<Adresse, Integer> implements AdresseDao {
	
	private EntityManager em;

	public AdresseDaoImpl() {
		super(Adresse.class);		
	}
	
	public EntityManager getEm() {
		return em;
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Adresse findByVille(String ville) throws DAOException {
		try {
			String jpq = "SELECT a FROM Adresse a WHERE a.ville = ?1";
			TypedQuery<Adresse> query = em.createQuery(jpq, Adresse.class);
			query.setParameter(1, ville);
			return query.getSingleResult();
		} catch(PersistenceException e){
			throw new DAOException("Erreur: AdresseDaoImpl - findByVille() "+e.getMessage(), e);
			
		}
	}

}
