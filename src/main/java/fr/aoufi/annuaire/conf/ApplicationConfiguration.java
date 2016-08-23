package fr.aoufi.annuaire.conf;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import fr.aoufi.annuaire.dao.AdresseDao;
import fr.aoufi.annuaire.dao.NumeroDao;
import fr.aoufi.annuaire.dao.PersonneDao;
import fr.aoufi.annuaire.dao.impl.AdresseDaoImpl;
import fr.aoufi.annuaire.dao.impl.NumeroDaoImpl;
import fr.aoufi.annuaire.dao.impl.PersonneDaoImpl;
import fr.aoufi.annuaire.service.AdresseService;
import fr.aoufi.annuaire.service.NumeroService;
import fr.aoufi.annuaire.service.PersonneService;
import fr.aoufi.annuaire.service.impl.AdresseServiceImpl;
import fr.aoufi.annuaire.service.impl.NumeroServiceImpl;
import fr.aoufi.annuaire.service.impl.PersonneServiceImpl;



@Configuration 
@Import(value = { DataConfiguration.class })
@ComponentScan(basePackages={"fr.aoufi.annuaire.service.impl"})
@PropertySource(value = "classpath:/config.properties")  
public class ApplicationConfiguration {
	
	@PersistenceContext
	private EntityManager em;
	

	@Bean 
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}


	@Bean(initMethod="init", destroyMethod="destroy")
	public PersonneService personneService(PersonneDao personneDao) {
		PersonneServiceImpl personneServiceImpl = new PersonneServiceImpl();
		personneServiceImpl.setPersonneDAO(personneDao);
		return personneServiceImpl;
	}
	
	@Bean
	public AdresseService adresseService(AdresseDao adresseDao) {
		AdresseServiceImpl adresseServiceImpl = new AdresseServiceImpl();
		adresseServiceImpl.setAdresseDAO(adresseDao);
		return adresseServiceImpl;
	}
	
	@Bean
	public NumeroService numeroService(NumeroDao numeroDao) {
		NumeroServiceImpl numeroServiceImpl = new NumeroServiceImpl();
		numeroServiceImpl.setNumeroDAO(numeroDao);
		return numeroServiceImpl;
	}

	@Bean(name = "personneDAO") // Renommage du bean en personneDAO
	public PersonneDao personneDaoImpl() {
		PersonneDaoImpl personneDaoImpl = new PersonneDaoImpl();
		personneDaoImpl.setEm(em);
		return personneDaoImpl;
	}
	
	@Bean(name = "adresseDAO") 
	public AdresseDao adresseDaoImpl() {
		AdresseDaoImpl adresseDaoImpl = new AdresseDaoImpl();
		adresseDaoImpl.setEm(em);
		return adresseDaoImpl;
	}
	
	@Bean(name = "numeroDAO") 
	public NumeroDao numeroDaoImpl() {
		NumeroDaoImpl numeroDaoImpl = new NumeroDaoImpl();
		numeroDaoImpl.setEm(em);
		return numeroDaoImpl;
	}

}