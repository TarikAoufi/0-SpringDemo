package fr.aoufi.annuaire.dao;

public class Hquery {
	public static final String SELECT_PERSONNE = 
			"SELECT p, a FROM Personne p LEFT OUTER JOIN FETCH p.adresse a  WHERE p.id=?" ;
	
	public static final String SELECT_PERSONNES_ID =
			"SELECT p FROM Personne p LEFT OUTER JOIN FETCH p.adresse ORDER BY p.id ASC" ;
	
	public static final String SELECT_PERSONNES_NOM =
			"SELECT p FROM Personne p LEFT OUTER JOIN FETCH p.adresse ORDER BY p.nom ASC" ;
	
	public static final String SELECT_PERSONNES_NUMERO 	= 
			"SELECT p FROM Personne p LEFT OUTER JOIN FETCH p.adresse "
					+ " JOIN p.numeros AS numero WHERE numero.id = ? ORDER BY p.nom ASC";
	
	public static final String SELECT_ADRESSES_ID = "SELECT a FROM Adresse a ORDER BY a.id ASC" ;
	
	public static final String SELECT_ADRESSES_VILLE = "SELECT a FROM Adresse a ORDER BY a.ville ASC" ;
	
	public static final String SELECT_NUMEROS_ID = 
			"SELECT DISTINCT(n) FROM Numero AS n LEFT OUTER JOIN n.personnes AS p  ORDER BY n.id ASC";
	
	public static final String SELECT_NUMEROS_TYPE = 
			"SELECT DISTINCT(n FROM Numero AS n LEFT OUTER JOIN n.personnes AS p  ORDER BY n.type ASC";
	
	public static final String SELECT_NUMEROS_PERSONNE = 
			"SELECT DISTINCT(n) FROM Numero AS n JOIN n.personnes AS p WHERE p.id = ? ORDER BY n.id ASC";

}
