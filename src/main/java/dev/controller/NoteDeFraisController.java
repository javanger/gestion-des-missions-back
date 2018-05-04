package dev.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import dev.entity.Collaborateur;
import dev.entity.LigneDeFrais;
import dev.entity.Mission;
import dev.entity.Nature;
import dev.entity.NoteDeFrais;
import dev.model.LigneDeFraisFlat;
import dev.model.NoteDeFraisFlat;
import dev.model.Role;
import dev.model.Status;
import dev.model.Transport;
import dev.repository.CollaborateurRepository;
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
	@Autowired
	private CollaborateurRepository collaborateurRepo;

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {
		// pour les tests
		Collaborateur collaborateur = new Collaborateur("123456", Role.EMPLOYE);
		collaborateurRepo.save(collaborateur);
		Optional<Collaborateur> cOptional = collaborateurRepo.findByMatricule("123456");
		collaborateur = cOptional.get();

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
		LigneDeFrais[] frais = { new LigneDeFrais("Hôtel", Date.stringToDate("22/05/2017"), new BigDecimal("75"), note),
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

	/**
	 * Retourne toutes les notes de frais
	 * 
	 * @return List<NoteDeFrais>
	 */
	@GetMapping()
	public List<NoteDeFrais> searchAll() {
		return this.noteDeFraisRepo.findAll();
	}

	/**
	 * Retourne la liste des natures (type) de frais != nature de mission
	 * 
	 * @return string[]
	 */
	@GetMapping("/frais/natures")
	public String[] listerNatures() {
		return new String[] { "Hôtel", "Petit-Déjeuner", "Restaurant" };
	}

	/**
	 * Retourne la ligne de frais en fonction de l'id du frais
	 * 
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/frais/{id}")
	public ResponseEntity<?> lireUnFrais(@PathVariable Integer id) {
		if (id > 0) {
			LigneDeFrais frais = this.ligneDeFraisRepo.findOne(id);
			if (frais != null) {
				LigneDeFraisFlat fraisFlat = new LigneDeFraisFlat(Integer.toString(frais.getId()),
						frais.getDate().format(DateTimeFormatter.ISO_DATE), frais.getNature(),
						frais.getMontant().toString());
				return ResponseEntity.ok(fraisFlat);
			}
		}
		return ResponseEntity.status(404).body("Aucun frais trouvé pour l'id: " + id);
	}

	/**
	 * Retourne une note de frais en fonction de l'id de la mission son id
	 * 
	 * @param id
	 * @return {@link ResponseEntity<?>} |
	 */
	@GetMapping("/mission/{id}")
	public ResponseEntity<?> findNoteFraisById(@PathVariable Integer id) {
		// récupérer la note de frais via l'id de mission
		Optional<NoteDeFrais> noteOpt = noteDeFraisRepo.findByMissionId(id);
		if (noteOpt.isPresent()) {
			List<LigneDeFrais> noteItemsEntities = ligneDeFraisRepo.findByNoteDeFrais(noteOpt.get());

			// generer l'objet flat retouné
			List<LigneDeFraisFlat> items = noteItemsEntities.stream()
					.map(n -> new LigneDeFraisFlat(Integer.toString(n.getId()),
							n.getDate().format(DateTimeFormatter.ISO_DATE), n.getNature(), n.getMontant().toString()))
					.collect(Collectors.toList());
			return ResponseEntity.ok(new NoteDeFraisFlat(id, items));
		} else {
			return ResponseEntity.status(404).body("Pas de note de frais trouvé pour la mission : " + id);
		}
	}

	/**
	 * Retourne la note de frais et ses frais en fonction de l'id d'une note
	 * 
	 * @return ResponseEntity<?>
	 */
	/*
	 * @GetMapping("/{id}/frais") public ResponseEntity<?>
	 * lireNoteDeFrais(@PathVariable Integer id) { if (id > 0) { NoteDeFrais note =
	 * this.noteDeFraisRepo.findOne(id); if (note != null) { // récupérer les frais
	 * de la notes List<LigneDeFrais> listFrais =
	 * this.ligneDeFraisRepo.findByNoteDeFrais(note); if (!listFrais.isEmpty()) {
	 * List<LigneDeFraisFlat> items = listFrais.stream() .map(f -> new
	 * LigneDeFraisFlat(Integer.toString(f.getId()), f.getNature(),
	 * Date.shortDateFormat(f.getDate()), f.getMontant().toString()))
	 * .collect(Collectors.toList()); return ResponseEntity.ok(new
	 * NoteDeFraisFlat(id, items)); } } } return ResponseEntity.status(404).
	 * body("Aucun frais trouvé pour la note de frais id = " + id); }
	 */

	/**
	 * Ajouter la ligne de frais à une note de frais
	 * 
	 * @param id
	 *            Interger : id de la note de frais
	 * @return l'element ajouter ou le message d'erreur
	 */
	@PostMapping("/{id}/frais")
	public ResponseEntity<?> creerLigneDeFrais(@PathVariable Integer id, @RequestBody LigneDeFraisFlat fraisFlat) {
		// récupérer la note de frais
		if (id > 0) {
			NoteDeFrais note = noteDeFraisRepo.findOne(id);
			if (note != null) {
				// parser le model en entité
				LigneDeFrais frais = new LigneDeFrais(fraisFlat.getNature(),
						LocalDate.parse(fraisFlat.getDate(), DateTimeFormatter.ISO_DATE),
						new BigDecimal(fraisFlat.getMontant()), note);
				// parser l'entité en model
				frais = this.ligneDeFraisRepo.save(frais);
				LigneDeFraisFlat newFrais = new LigneDeFraisFlat(Integer.toString(frais.getId()), frais.getNature(),
						frais.getDate().format(DateTimeFormatter.ISO_DATE), frais.getMontant().toString());
				return ResponseEntity.ok(newFrais);
			}
		}
		return ResponseEntity.status(404).body("Pas de note de frais trouvé pour la mission : " + id);
	}
}
