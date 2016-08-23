package fr.aoufi.annuaire.runtime;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.aoufi.annuaire.conf.ApplicationConfiguration;
import fr.aoufi.annuaire.model.Adresse;
import fr.aoufi.annuaire.model.Numero;
import fr.aoufi.annuaire.model.Personne;
import fr.aoufi.annuaire.service.AdresseService;
import fr.aoufi.annuaire.service.NumeroService;
import fr.aoufi.annuaire.service.PersonneService;
import fr.aoufi.annuaire.util.DateUtils;
import fr.aoufi.annuaire.util.Lire;

public class Runtime {

	public static PersonneService personneService;
	public static AdresseService  adresseService;
	public static NumeroService   numeroService;
	public static Scanner 		  sc;

	public static void main(String[] args) {

		System.setProperty("spring.profiles.active", "jpa");

		ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ApplicationConfiguration.class);

		for (String string : applicationContext.getBeanDefinitionNames()) {
			System.out.println(string);
		}

		personneService = applicationContext.getBean("personneService", PersonneService.class);
		adresseService  = applicationContext.getBean(AdresseService.class);
		numeroService   = applicationContext.getBean("numeroService", NumeroService.class);

		sc = new Scanner(System.in);

		do {		
			System.out.println("\n\t\t\t\t\t\t=====================================================");
			System.out.println("\t\t\t\t\t\t| Sélectionnez un numéro parmi les choix suivants : |");
			System.out.println("\t\t\t\t\t\t|---------------------------------------------------|");		
			System.out.println("\t\t\t\t\t\t| 1 - Créer une personne                            |");
			System.out.println("\t\t\t\t\t\t| 2 - Modifier une personne                         |");
			System.out.println("\t\t\t\t\t\t| 3 - Lister les personnes                          |");
			System.out.println("\t\t\t\t\t\t| 4 - Supprimer une peronne                         |");
			System.out.println("\t\t\t\t\t\t|---------------------------------------------------|");	
			System.out.println("\t\t\t\t\t\t| 5 - Lister les personnes avec adresse             |");	
			System.out.println("\t\t\t\t\t\t| 6 - Afficher les numeros d'une personne           |");
			System.out.println("\t\t\t\t\t\t| 7 - Afficher les personnes qui ont le même numero |");
			System.out.println("\t\t\t\t\t\t| 8 - Quitter                                       |");
			System.out.println("\t\t\t\t\t\t=====================================================\n");
			menuPrincipale();
		} while (veutContinuer());

		applicationContext.close();
	}

	public static void menuPrincipale() {

		int choix = sc.nextInt();

		switch (choix) {
		case 1:
			System.out.println("1 - Créer une personne :");			
			Personne pers = ajouterPersonne();
			ajouterAdresse(pers);
			ajouterNumeros(pers);
			afficherPersonnes();
			break;
		case 2:
			System.out.println("2 - Modifier une personne :");
			modifierPersonne();			
			break;
		case 3:
			System.out.println("3 - Lister les personnes   :");
			afficherPersonnes();
			break;
		case 4:
			System.out.println("4 - Supprimer une peronne :");
			supprimerPersonne();
			break;
		case 5:
			System.out.println("5 - Lister les personnes avec adresse :");
			afficherPersonnesByAdresse();
			break;
		case 6:
			System.out.println("6 - Afficher les numeros d'une personne :");
			afficherPersonnes();
			afficherNumeros();
			break;
		case 7:
			System.out.println("7 - Afficher les personnes d'un numero :");
			afficherPersonnesNumero();
			break;
		case 8:
			//System.out.println("8 - Quitter :");
			break;
		default:

		}
	}

	public static void afficherPersonnes() {
		List<Personne> list = personneService.findAll();
		System.out.println("******** Liste des personnes ******** : ");
		for (Personne pers : list) {
			System.out.println("[ id: " + pers.getId() + " \tnom: " + pers.getNom() + " \tprenom: " + pers.getPrenom()
					+ "      \t dateNaissance: " + DateUtils.dateToString(pers.getDateNaissance(), "dd/MM/yyyy")
					+ "] ");
		}
	}

	public static void afficherPersonnesByAdresse() {
		List<Personne> list = personneService.findAll();
		System.out.println("******** Liste des personnes ******** : ");
		for (Personne pers : list) {
			System.out.println("[ id: " + pers.getId() + " \tnom: " + pers.getNom() + " \tprenom: " + pers.getPrenom()
					+ "      \t dateNaissance: " + DateUtils.dateToString(pers.getDateNaissance(), "dd/MM/yyyy")
					+ " \tAdresse [ id : " + pers.getAdresse().getId() + "\true:" + pers.getAdresse().getRue()
					+ "      \t\tville:" + pers.getAdresse().getVille() + "] ");
		}
	}

	public static Personne ajouterPersonne() {

		Personne per = new Personne();

		System.out.println("========================= Saisir les informations de la personne ====================== ");
		System.out.println(" - Nom : ");
		per.setNom(sc.next());
		System.out.println(" - Prenom : ");
		per.setPrenom(sc.next());
		System.out.println(" - Date de naissance (jj/mm/aaaa): ");
		per.setDateNaissance(DateUtils.stringToDate(sc.next(), "dd/MM/yyyy"));

		return personneService.create(per);
	}

	public static Adresse ajouterAdresse(Personne personne) {

		Adresse adr = new Adresse();

		System.out.println("========================= Saisir l'adresse de la personne ====================== ");
		System.out.println(" - Rue : ");
		adr.setRue(sc.nextLine());
		System.out.println(" - Ville : ");
		adr.setVille(sc.nextLine());
		adr.setPersonne(personne);

		return adresseService.create(adr);
	}

	public static void ajouterNumeros(Personne personne) {
		Set<Numero> numeros = new HashSet<Numero>();
		do {

			Numero num = new Numero();
			System.out.println("========================= Saisir le numero de la personne ====================== ");
			System.out.println(" - Tél. : ");
			num.setTel(sc.nextLine());
			System.out.println(" - Type : ");
			num.setType(sc.nextLine());
			numeros.add(num);
			personne.setNumeros(numeros);
			numeroService.create(num);
			personneService.update(personne);

		} while (autreNumero());
	}

	public static void modifierPersonne() {
		afficherPersonnes();
		System.out.println("Entrer l'id de la personne à modifier:");
		Personne personne = personneService.findById(sc.nextInt());
		System.out.println(" - Nom : ");
		personne.setNom(sc.next());
		System.out.println(" - Prenom : ");
		personne.setPrenom(sc.next());
		System.out.println(" - Date de naissance (jj/mm/aaaa): ");
		personne.setDateNaissance(DateUtils.stringToDate(sc.next(), "dd/MM/yyyy"));
		personneService.update(personne);
		afficherPersonnes();
	}

	public static void supprimerPersonne() {
		afficherPersonnes();
		System.out.println("Renseigner l'id de la personne à supprimer:");
		personneService.deleteById(sc.nextInt());
		afficherPersonnes();

	}

	public static void afficherNumeros() {
		System.out.println("Renseigner l'id de la personne :");
		Personne personne = personneService.findById(sc.nextInt());
		List<Numero> list = numeroService.findAllByPersonne(personne);
		System.out.println("******** Liste des numeros de " + personne.getNom() + " :");
		for (Numero num : list) {
			System.out.println(
					"  \t[ id: " + num.getId() + ", tel: " + num.getTel() + ",\t\t type: " + num.getType() + " ]");
		}
	}

	public static void afficherPersonnesNumero() {
		System.out.println("Renseigner l'id du numero :");
		Numero numero = numeroService.findById(sc.nextInt());
		List<Personne> list = personneService.findAllByNumero(numero);

		System.out.println("******** Liste des personnes ayant le numero " + numero.getTel() + " :");
		for (Personne pers : list) {
			System.out.println("[ id: " + pers.getId() + " \tnom: " + pers.getNom() + " \tprenom: " + pers.getPrenom()
					+ "      \t dateNaissance: " + DateUtils.dateToString(pers.getDateNaissance(), "dd/MM/yyyy")
					+ "] ");
		}
	}

	public static boolean autreNumero() {
		boolean reponse = false;
		char decision;

		do {
			System.out.println("\nAjouter un autre numero ( O / N ) ?");
			decision = Lire.c();
		} while (decision != 'O' && decision != 'o' && decision != 'N' && decision != 'n');

		if (decision == 'O' || decision == 'o') {
			reponse = true;
		}
		return reponse;
	}

	public static boolean veutContinuer() {
		boolean reponse = false;
		char decision;

		do {
			System.out.println("\nVoulez-vous continuer ( O / N ) ?");
			decision = Lire.c();
		} while (decision != 'O' && decision != 'o' && decision != 'N' && decision != 'n');

		if (decision == 'O' || decision == 'o') {
			reponse = true;
		}
		return reponse;
	}
}