package dev.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entity.LigneDeFrais;
import dev.entity.Mission;
import dev.entity.Nature;
import dev.entity.NoteDeFrais;
import dev.model.NoteItemFlat;
import dev.model.Status;
import dev.model.Transport;
import dev.model.VeryLigthMission;
import dev.repository.LigneDeFraisRepository;
import dev.repository.MissionRepository;
import dev.repository.NatureRepository;
import dev.repository.NoteDeFraisRepository;
import dev.utils.Date;

@RestController
@CrossOrigin
@RequestMapping("/api/notes")
public class NoteDeFraisController {

	@Autowired
	private NoteDeFraisRepository noteDeFraisRepo;
	@Autowired
	private MissionRepository missionRepo;
	@Autowired
	private NatureRepository natureRepo;
	@Autowired
	private LigneDeFraisRepository ligneDeFraisRepo;

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {
		// pour les tests
		this.missionRepo.save(
				new Mission(LocalDate.now(), LocalDate.now(), natureRepo.save(new Nature("Negos", false, true, 40, 50)),
						"Paris", "Nantes", Transport.VOITURE_DE_SERVICE, Status.INITIALE));
		this.missionRepo.save(new Mission(LocalDate.now(), LocalDate.now(),
				natureRepo.save(new Nature("Expertise", false, true, 5, 4)), "Paris", "Orlean", Transport.TRAIN,
				Status.INITIALE));
		this.missionRepo.save(new Mission(LocalDate.now(), LocalDate.now(),
				natureRepo.save(new Nature("Formation", false, false, 0, 0)), "Lyon", "Paris", Transport.TRAIN,
				Status.INITIALE));
		this.missionRepo.save(new Mission(LocalDate.now(), LocalDate.now(),
				natureRepo.save(new Nature("Expertise", false, true, 6, 6)), "Paris", "Tokyo", Transport.AVION,
				Status.INITIALE));
	}

	@GetMapping()
	public List<NoteDeFrais> searchAll() {
		return this.noteDeFraisRepo.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		NoteDeFrais noteEntity = noteDeFraisRepo.getOne(id);
		List<LigneDeFrais> noteItemsEntities = ligneDeFraisRepo.findByNoteDeFrais(id);
		Mission missionRefenrence = missionRepo.getOne(noteEntity.getMission().getId());

		// generer l'objet retouné
		String dateDebut = Date.shortDateFormat(missionRefenrence.getDateDebut());
		String dateFin = Date.shortDateFormat(missionRefenrence.getDateFin());
		String estimationPrime = "0";
		String nature = missionRefenrence.getNature().getLibelle();
		String villeDepart = missionRefenrence.getVilleDepart();
		String villeArrivee = missionRefenrence.getVilleArrivee();
		// NoteDeFraisFlat flatNote = new NoteDeFraisFlat(dateDebut, dateFin,
		// estimationPrime, nature, villeDepart,
		// villeArrivee, noteItemsEntities);

		// return ResponseEntity.ok(flatNote);
		return null;
	}

	@GetMapping("/frais/natures")
	public String[] listerNatures() {
		return new String[] { "Hôtel", "Petit-Déjeuner", "Restaurant" };
	}

	/**
	 * @param missionId
	 * @return l'ojet NoteDeFrais créer
	 */
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody VeryLigthMission mission) {
		// récupérer la mission
		Mission existingMission = missionRepo.findOne(mission.getId());

		// creer la note de frais
		NoteDeFrais note = new NoteDeFrais(LocalDateTime.now(), null, false, false, existingMission);

		return ResponseEntity.ok(noteDeFraisRepo.save(note));
	}

	/*
	 * Ajouter la ligne de frais en fonction de la mission
	 * @param idMission
	 * @return l'element ajouter ou le message d'erreur
	 */
	@PostMapping("/frais/{idMission}")
	public ResponseEntity<?> creerLigneDeFrais(@PathVariable Integer idMission) {
		// récupérer la mission
		// ajouter la ligne de frais
		NoteItemFlat fraisFlat = new NoteItemFlat("date", "nature", "montant");
		return ResponseEntity.ok(fraisFlat);
	}

}
