package fr.aoufi.annuaire.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.aoufi.annuaire.dao.Hquery;
import fr.aoufi.annuaire.dao.NumeroDao;
import fr.aoufi.annuaire.exception.DAOException;
import fr.aoufi.annuaire.model.Numero;
import fr.aoufi.annuaire.model.Personne;
import fr.aoufi.annuaire.util.ClassUtils;

public class NumeroDaoImpl extends GenericDaoImpl<Numero, Integer> implements NumeroDao {

	private EntityManager em;

	public NumeroDaoImpl() {
		super(Numero.class);
	}
	
	@Override
	public Numero findByType(String type) throws DAOException {
		try {
			String jpq = "SELECT n FROM Numero n WHERE n.type = ?1";
			TypedQuery<Numero> query = em.createQuery(jpq, Numero.class);
			query.setParameter(1, type);
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new DAOException("Erreur: NumeroDaoImpl - findByType() " + e.getMessage(), e);
		}
	}
	
	@Override
	public List<Numero> findAllByPersonne(Personne personne) throws DAOException {
		try {
			List<Numero> liste = null;
			if (personne != null) {
				Query query = em.createQuery(Hquery.SELECT_NUMEROS_PERSONNE);
				query.setParameter(1,personne.getId());
				liste = ClassUtils.recupObjet(query, Numero.class);
			} else {
				liste = new ArrayList<Numero>();
			}
			return liste;	
		} catch (PersistenceException e) {
			throw new DAOException("Erreur: NumeroDaoImpl - findAllByPersonne() " + e.getMessage(), e);
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	

}
