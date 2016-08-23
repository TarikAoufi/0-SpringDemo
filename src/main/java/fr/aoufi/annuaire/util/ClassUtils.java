package fr.aoufi.annuaire.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

public class ClassUtils {
	
	/**
	 * Méthode generic pour récupérer le résultat d'un query
	 *  et de retourner une liste du type demande
	 *  
	 * @param query
	 * @param classe
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> recupObjet(Query query, Class<T> classe) {
		List<T> liste = new ArrayList<T>();
		for (Object o : query.getResultList())	{   
			try {
				liste.add((T)o);
			} catch (ClassCastException e) {}
		}
		return liste;
	}

}
