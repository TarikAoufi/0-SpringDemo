package fr.aoufi.annuaire.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
//@SQLDelete("UPDATE Personne SET deleted = true WHERE id = ?") private Boolean deleted;
//WHERE(clause="deleted='false'")    elle marche avec la m�thode remove() de em
public class Personne implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String prenom;	
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "personne", cascade = CascadeType.ALL)
	private Adresse adresse;
	
	@ManyToMany(cascade = {CascadeType.PERSIST},fetch=FetchType.LAZY)
	private Set<Numero> numeros;

	public Personne() {

	}
	
	public Personne(String nom, String prenom, Date dateNaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}

	public Personne(Integer id, String nom, String prenom, Date dateNaissance) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}



	public Personne(Integer id, String nom, String prenom, Date dateNaissance, Adresse adresse) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
	}
	
	public Personne(Integer id, String nom, String prenom, Date dateNaissance, Adresse adresse, Set<Numero> numeros) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.numeros = numeros;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Set<Numero> getNumeros() {
		return numeros;
	}

	public void setNumeros(Set<Numero> numeros) {
		this.numeros = numeros;
	}


	@Override
	public String toString() {
		return "Personne: [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ "]";
	}
		
	

}
