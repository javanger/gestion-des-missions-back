package dev.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entity.Mission;
import dev.model.MissionDetailsFraisFlat;
import dev.model.NewMission;
import dev.repository.CollaborateurRepository;
import dev.repository.MissionRepository;
import dev.repository.NatureRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/missions")
public class MissionController {

	@Autowired
	private MissionRepository missionRepo;

	@Autowired
	private CollaborateurRepository collaborateurRepository;

	@Autowired
	private NatureRepository natureRepository;

	@GetMapping
	public List<Mission> searchAll() {
		return this.missionRepo.findAll();
	}

	/**
	 * @param id
	 *            de la mission
	 * @return la mission
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> searchById(@PathVariable Integer id) {

		Mission mission = missionRepo.getOne(id);

		if (mission != null) {

			String idMission = mission.getId().toString();
			String dateDebut = mission.getDateDebut().format(DateTimeFormatter.ISO_DATE);
			String dateFin = mission.getDateFin().format(DateTimeFormatter.ISO_DATE);
			String nature = mission.getNature().getLibelle();
			String villeDepart = mission.getVilleDepart();
			String villeArrivee = mission.getVilleArrivee();
			String transport = mission.getTransport().toString();
			String statut = mission.getStatut().toString();
			String prime = "1000";

			MissionDetailsFraisFlat missionDetailsFraisFlat = new MissionDetailsFraisFlat(idMission, dateDebut, dateFin,
					nature, prime, villeDepart, villeArrivee, transport, statut);

			return ResponseEntity.ok(missionDetailsFraisFlat);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mission inconnue");
		}
	}

	/**
	 * @param matricule
	 *            du collaborateur
	 * @return la liste des missions en fonction du matricule
	 */
	@GetMapping("/collaborateur/{matricule}")
	public ResponseEntity<?> findMissionsByCollaborateurMatricule(@PathVariable String matricule) {

		List<Mission> missions = missionRepo.findByCollaborateurMatricule(matricule);

		if (!missions.isEmpty()) {

			List<MissionDetailsFraisFlat> missionsDetailsFraisFlats = new ArrayList<>();

			for (Mission mission : missions) {

				String id = mission.getId().toString();
				String dateDebut = mission.getDateDebut().format(DateTimeFormatter.ISO_DATE);
				String dateFin = mission.getDateFin().format(DateTimeFormatter.ISO_DATE);
				String nature = mission.getNature().getLibelle();
				String villeDepart = mission.getVilleDepart();
				String villeArrivee = mission.getVilleArrivee();
				String transport = mission.getTransport().toString();
				String statut = mission.getStatut().toString();
				String prime = "1000";

				MissionDetailsFraisFlat missionDetailsFraisFlat = new MissionDetailsFraisFlat(id, dateDebut, dateFin,
						nature, prime, villeDepart, villeArrivee, transport, statut);

				missionsDetailsFraisFlats.add(missionDetailsFraisFlat);
			}
			return ResponseEntity.ok(missionsDetailsFraisFlats);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matricule inconnu");
		}

	}

	@PostMapping("/collaborateur/{matricule}")
	public ResponseEntity<?> createMission(@PathVariable String matricule, @RequestBody NewMission newMission) {

		Mission mission = new Mission();

		mission.setDateDebut(LocalDate.parse(newMission.getDateDebut()));
		mission.setDateFin(LocalDate.parse(newMission.getDateFin()));
		mission.setCollaborateur(collaborateurRepository.findByMatricule(matricule).get());
		mission.setNature(natureRepository.findByLibelle(newMission.getNature()).get());
		mission.setVilleDepart(newMission.getVilleDepart());
		mission.setVilleArrivee(newMission.getVilleArrivee());

		missionRepo.save(mission);

		return ResponseEntity.ok(mission);
	}

	/**
	 * Retourne la liste des transports disponible pour les missions
	 * 
	 * @return string[]
	 */
	@GetMapping("/transports")
	public String[] listerTransports() {
		return new String[] { "Avion", "Covoiturage", "Train", "Voiture de service" };
	}

}
