/**
 * 
 */
package dev.listener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import dev.entity.Collaborateur;
import dev.entity.LigneDeFrais;
import dev.entity.Mission;
import dev.entity.Nature;
import dev.entity.NoteDeFrais;
import dev.model.ApiCollaborateur;
import dev.model.Role;
import dev.model.Status;
import dev.model.Transport;
import dev.repository.CollaborateurRepository;
import dev.repository.LigneDeFraisRepository;
import dev.repository.MissionRepository;
import dev.repository.NatureRepository;
import dev.repository.NoteDeFraisRepository;
import dev.utils.Date;

/**
 * @author Alexis Darcy
 *
 */
@Component
public class StartUpAppListener {

	@Autowired
	private NoteDeFraisRepository noteDeFraisRepo;
	@Autowired
	private MissionRepository missionRepo;
	@Autowired
	private NatureRepository natureRepo;
	@Autowired
	private LigneDeFraisRepository ligneDeFraisRepo;
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

			Optional<Collaborateur> cOptional = collaborateurRepo.findByMatricule("26a79080");
			Collaborateur collaborateur = cOptional.get();

			// new mission
			Mission mission = new Mission(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 3, 5),
					natureRepo.save(new Nature("Negos", false, true, 40, 50)), "Paris", "Nantes",
					Transport.VOITURE_DE_SERVICE, Status.INITIALE);
			mission.setCollaborateur(cOptional.get());
			Mission newMission = this.missionRepo.save(mission);
			// new note de frais
			NoteDeFrais note = new NoteDeFrais(newMission);
			note = this.noteDeFraisRepo.save(note);
			// ligne de frais
			LigneDeFrais[] frais = {
					new LigneDeFrais("Hôtel", Date.stringToDate("22/05/2017"), new BigDecimal("75"), note),
					new LigneDeFrais("Hôtel", Date.stringToDate("23/05/2017"), new BigDecimal("75"), note),
					new LigneDeFrais("Petit-déjeuner", Date.stringToDate("23/05/2017"), new BigDecimal("9"), note),
					new LigneDeFrais("Restaurant", Date.stringToDate("23/05/2017"), new BigDecimal("12.5"), note),
					new LigneDeFrais("Restaurant", Date.stringToDate("23/05/2017"), new BigDecimal("12.5"), note),
					new LigneDeFrais("Hôtel", Date.stringToDate("24/05/2017"), new BigDecimal("75"), note) };

			Arrays.stream(frais).forEach(item -> ligneDeFraisRepo.save(item));

			// autre mission
			mission = new Mission(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 6, 30),
					natureRepo.save(new Nature("Expertise", false, true, 5, 4)), "Paris", "Orlean", Transport.TRAIN,
					Status.INITIALE);
			mission.setCollaborateur(collaborateur);

			this.missionRepo.save(mission);

			// autre mission
			mission = new Mission(LocalDate.of(2018, 9, 1), LocalDate.of(2018, 10, 30),
					natureRepo.save(new Nature("Formation", false, false, 0, 0)), "Lyon", "Paris", Transport.TRAIN,
					Status.INITIALE);
			mission.setCollaborateur(collaborateur);
			this.missionRepo.save(mission);

			// autre mission
			mission = new Mission(LocalDate.of(2018, 1, 1), LocalDate.now().minusDays(1),
					natureRepo.save(new Nature("Expertise", false, true, 6, 6)), "Paris", "Tokyo", Transport.AVION,
					Status.INITIALE);
			mission.setCollaborateur(collaborateur);
			this.missionRepo.save(mission);
		}
	}
}
