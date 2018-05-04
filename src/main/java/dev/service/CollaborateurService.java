/**
 * 
 */
package dev.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.entity.Collaborateur;
import dev.model.ApiCollaborateur;
import dev.model.ViewCollaborateur;
import dev.repository.CollaborateurRepository;

/**
 * @author Alexis Darcy
 *
 */
@Service
public class CollaborateurService {

	@Autowired
	private CollaborateurRepository collaborateurRepository;

	private final String URL = "http://collegues-api.cleverapps.io/collegues";

	public ViewCollaborateur collaborateurIdentifier(String matricule) {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ApiCollaborateur[]> response = restTemplate.getForEntity(URL, ApiCollaborateur[].class);
		List<ApiCollaborateur> listApiCollaborateur = Arrays.asList(response.getBody());
		
		Optional<ApiCollaborateur> collaborateurTrouve = listApiCollaborateur.stream()
				.filter((ApiCollaborateur col) -> col.getMatricule().equals(matricule)).findFirst();

		Collaborateur collaborateur = collaborateurRepository.findByMatricule(matricule);

		ViewCollaborateur viewCollaborateur = collaborateurTrouve
				.map(c -> new ViewCollaborateur(c.getMatricule(), c.getPrenom(), c.getNom(), collaborateur.getRole()))
				.orElse(null);

		return viewCollaborateur;
	}
}
