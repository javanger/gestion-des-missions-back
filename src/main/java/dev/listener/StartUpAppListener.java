package dev.listener;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import dev.entity.Collaborateur;
import dev.model.ApiCollaborateur;
import dev.model.Role;
import dev.repository.CollaborateurRepository;
import dev.service.InitialiserDonneesService;

@Component
public class StartUpAppListener {

	@Autowired
	private InitialiserDonneesService initService;
	
	@Autowired
	private CollaborateurRepository collaborateurRepo;

	private final String URL = "http://collegues-api.cleverapps.io/collegues";

	@EventListener(ContextRefreshedEvent.class)
	@Transactional
	public void contextRefreshedEvent() {
		if (collaborateurRepo.count() == 0) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ApiCollaborateur[]> response = restTemplate.getForEntity(URL, ApiCollaborateur[].class);
			List<ApiCollaborateur> listApiCollaborateur = Arrays.asList(response.getBody());

			listApiCollaborateur.forEach(c -> {
				if (c.getSubalternes().isEmpty()) {
					Collaborateur collaborateur = new Collaborateur(c.getMatricule(), c.getPassword(),
							Role.ROLE_EMPLOYE);
					this.collaborateurRepo.save(collaborateur);
				} else {
					if (c.getDepartement().equals("DSI")) {
						Collaborateur collaborateur = new Collaborateur(c.getMatricule(), c.getPassword(),
								Role.ROLE_ADMINISTRATEUR);
						this.collaborateurRepo.save(collaborateur);
					} else {
						Collaborateur collaborateur = new Collaborateur(c.getMatricule(), c.getPassword(),
								Role.ROLE_MANAGER);
						this.collaborateurRepo.save(collaborateur);
					}
				}
			});
		}

		initService.initialiser();

	}

}