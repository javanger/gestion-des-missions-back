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
import dev.entity.NoteDeFrais;
import dev.model.LigneDeFraisFlat;
import dev.model.NoteDeFraisFlat;
import dev.repository.LigneDeFraisRepository;
import dev.repository.NoteDeFraisRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/notes")
public class NoteDeFraisController {

	@Autowired
	private NoteDeFraisRepository noteDeFraisRepo;

	@Autowired
	private LigneDeFraisRepository ligneDeFraisRepo;

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
