package dev.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import dev.entity.Collaborateur;
import dev.entity.LigneDeFrais;
import dev.entity.Mission;
import dev.entity.Nature;
import dev.entity.NoteDeFrais;
import dev.repository.CollaborateurRepository;

@Service
public class InitialiserDonneesService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CollaborateurRepository collaborateurRepo;
	
	@SuppressWarnings("resource")
	@Transactional
	public void initialiser() {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("configWeb.xml");
		
		Stream.of(Collaborateur.class)
		.flatMap(classe -> context.getBeansOfType(classe).values().stream())
		.forEach(em::persist);
		
		Stream.of(Nature.class)
		.flatMap(classe -> context.getBeansOfType(classe).values().stream())
		.map(n -> {
			n.setDateFin(LocalDate.parse("2100-01-01"));
			return n;
		}).forEach(em::persist);
		
		Stream.of(Mission.class)
		.flatMap(classe -> context.getBeansOfType(classe).values().stream())
		.map(m -> {
			m.setCollaborateur(collaborateurRepo.getOne(20));
			m.setDateDebut(LocalDate.parse("2018-02-15"));
			m.setDateFin(LocalDate.parse("2018-06-23"));
			return m;
		}).forEach(em::persist);
		

		Stream.of(NoteDeFrais.class)
		.flatMap(classe -> context.getBeansOfType(classe).values().stream())
		.map(n -> {
			n.setDateCreation(LocalDateTime.now());
			return n;
		}).forEach(em::persist);
		
		Stream.of(LigneDeFrais.class)
		.flatMap(classe -> context.getBeansOfType(classe).values().stream())
		.map(l -> {
			l.setDate(LocalDate.now());
			return l;
		}).forEach(em::persist);
	
		context.close();

	}

}
