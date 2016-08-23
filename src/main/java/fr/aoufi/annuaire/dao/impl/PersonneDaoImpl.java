package fr.aoufi.annuaire.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.aoufi.annuaire.dao.Hquery;
import fr.aoufi.annuaire.dao.PersonneDao;
import fr.aoufi.annuaire.exception.DAOException;
import fr.aoufi.annuaire.model.Numero;
import fr.aoufi.annuaire.model.Personne;
import fr.aoufi.annuaire.util.ClassUtils;

public class PersonneDaoImpl extends GenericDaoImpl<Personne, Integer> implements PersonneDao {

	private EntityManager em;

	public PersonneDaoImpl() {
		super(Personne.class);
	}
	
	@Override
	public List<Personne> findByName(String nom) throws DAOException {
		try{
			TypedQuery<Personne> tQuery = em.createQuery(
					"SELECT DISTINCT(p) FROM Personne p "
					+ "LEFT JOIN FETCH p.numeros "
					+ "WHERE p.nom=:nom ", Personne.class);
			tQuery.setParameter("nom", nom);
			return tQuery.getResultList();
		}catch(PersistenceException e){
			throw new DAOException("Erreur: PersonneDaoImpl - findByName() "+e.getMessage(), e);
			
		}
	}
	
	@Override
	public List<Personne> findAllByNumero(Numero numero) throws DAOException {
		try {
			List<Personne> liste = null;
			if (numero != null) {
				Query query = em.createQuery(Hquery.SELECT_PERSONNES_NUMERO).setParameter(1, numero.getId());
				liste = ClassUtils.recupObjet(query, Personne.class);
			} else {
				liste = new ArrayList<Personne>();
			}
			return liste;
		} catch (PersistenceException e) {
			throw new DAOException("Erreur: PersonneDaoImpl - findAllByNumero() " + e.getMessage(), e);
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
