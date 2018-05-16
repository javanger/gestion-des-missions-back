package dev.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import dev.entity.NoteDeFrais;
import dev.model.LigneDeFraisFlat;
import dev.model.NoteDeFraisFlat;

import dev.repository.CollaborateurRepository;
import dev.repository.LigneDeFraisRepository;
import dev.repository.NoteDeFraisRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/notes")
public class NoteDeFraisController {

	private static final String KEY_MESSAGE_HEADER = "message";
	@Autowired
	private NoteDeFraisRepository noteDeFraisRepo;

	@Autowired
	private LigneDeFraisRepository ligneDeFraisRepo;

	@Autowired
	private CollaborateurRepository collaborateurRepo;


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
	 * @return ResponseEntity<LigneDeFraisFlat>
	 */
	@GetMapping("/frais/{id}")
	public ResponseEntity<LigneDeFraisFlat> lireUnFrais(@PathVariable Integer id) {
		if (id > 0) {
			LigneDeFrais frais = this.ligneDeFraisRepo.findOne(id);
			if (frais != null) {
				LigneDeFraisFlat fraisFlat = new LigneDeFraisFlat(Integer.toString(frais.getId()),
						frais.getDate().format(DateTimeFormatter.ISO_DATE), frais.getNature(),
						frais.getMontant().toString());
				return ResponseEntity.ok(fraisFlat);
			}
		}
		final String VALUE_MESSAGE_HEADER = "Aucun frais trouvé pour l'id: " + id;
		return ResponseEntity.status(404).header(KEY_MESSAGE_HEADER, VALUE_MESSAGE_HEADER).body(null);
	}

	/**
	 * Retourne une note de frais en fonction de l'id de la mission
	 * 
	 * @param id
	 * @return {@link ResponseEntity<NoteDeFraisFlat>} |
	 */
	@GetMapping("/missions/{id}")
	public ResponseEntity<NoteDeFraisFlat> findNoteFraisById(@PathVariable Integer id) {
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
			final String VALUE_MESSAGE_HEADER = "Pas de note de frais trouvé pour la mission : " + id;
			return ResponseEntity.status(404).header(KEY_MESSAGE_HEADER, VALUE_MESSAGE_HEADER).body(null);
		}
	}

	/**
	 * Vérifier que le frais est unique
	 * 
	 * @param id
	 * @param fraisFlat
	 * @return ResponseEntity<Boolean>
	 */
	@PostMapping("/missions/{id}/frais/check")
	public ResponseEntity<Boolean> estUniqueDateAndNatureAndMission(@PathVariable Integer id,
			@RequestBody LigneDeFraisFlat fraisFlat) {

		if (id > 0) {
			// récupérer la mission
			Mission mission = this.missionRepo.getOne(id);
			if (mission != null) {
				LocalDate date = LocalDate.parse(fraisFlat.getDate(), DateTimeFormatter.ISO_DATE);
				List<LigneDeFrais> items = ligneDeFraisRepo.findByDateAndNatureAndNoteDeFraisMissionId(date,
						fraisFlat.getNature(), id);
				if (items.isEmpty()) {
					// le frais est unique
					return ResponseEntity.ok(true);
				} else {
					return ResponseEntity.ok(false);
				}
			}
		}
		final String VALUE_MESSAGE_HEADER = "Pas de mission trouvée pour l'id : " + id;
		return ResponseEntity.status(404).header(KEY_MESSAGE_HEADER, VALUE_MESSAGE_HEADER).body(null);
	}

	/**
	 * Ajouter la ligne de frais à une note de frais
	 * 
	 * @param id
	 *            Interger : id de la note de frais
	 * @return ResponseEntity<LigneDeFraisFlat>
	 */
	@PostMapping("/{id}/frais")
	public ResponseEntity<LigneDeFraisFlat> creerLigneDeFrais(@PathVariable Integer id,
			@RequestBody LigneDeFraisFlat fraisFlat) {
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
				LigneDeFraisFlat newFrais = new LigneDeFraisFlat(Integer.toString(frais.getId()),
						frais.getDate().format(DateTimeFormatter.ISO_DATE), frais.getNature(),
						frais.getMontant().toString());
				return ResponseEntity.ok(newFrais);
			}
		}
		final String VALUE_MESSAGE_HEADER = "Pas de note de frais trouvé pour la mission : " + id;
		return ResponseEntity.status(404).header(KEY_MESSAGE_HEADER, VALUE_MESSAGE_HEADER).body(null);
	}
}
