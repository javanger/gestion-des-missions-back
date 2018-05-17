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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entity.LigneDeFrais;
import dev.entity.Mission;
import dev.entity.NoteDeFrais;
import dev.exception.NoteDeFraisApiException;
import dev.model.LigneDeFraisFlat;
import dev.model.NoteDeFraisFlat;
import dev.repository.LigneDeFraisRepository;
import dev.repository.MissionRepository;
import dev.repository.NoteDeFraisRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/notes")
public class NoteDeFraisController {

	private static final String KEY_MESSAGE_HEADER = "response_message";
	@Autowired
	private NoteDeFraisRepository noteDeFraisRepo;

	@Autowired
	private LigneDeFraisRepository ligneDeFraisRepo;

	@Autowired
	private MissionRepository missionRepo;


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
	public ResponseEntity<LigneDeFraisFlat> lireUnFrais(@PathVariable Integer id) throws NoteDeFraisApiException {
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
		throw new NoteDeFraisApiException(VALUE_MESSAGE_HEADER);
	}

	/**
	 * Retourne une note de frais en fonction de l'id de la mission
	 * 
	 * @param id
	 * @return {@link ResponseEntity<NoteDeFraisFlat>} |
	 */
	@GetMapping("/missions/{id}")
	public ResponseEntity<NoteDeFraisFlat> findNoteFraisById(@PathVariable Integer id) throws NoteDeFraisApiException {
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
			throw new NoteDeFraisApiException(VALUE_MESSAGE_HEADER);
		}
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
			@RequestBody LigneDeFraisFlat fraisFlat) throws NoteDeFraisApiException {
		// récupérer la note de frais
		if (id > 0) {
			NoteDeFrais note = noteDeFraisRepo.findOne(id);
			if (note != null) {
				// vérifier que le frais est unique
				if (false == estUniqueDateAndNatureAndMission(id, fraisFlat)) {
					final String VALUE_MESSAGE_HEADER = "Impossible d'ajout le frais : Un frais de même nature existe dèja pour cette date !";
					throw new NoteDeFraisApiException(VALUE_MESSAGE_HEADER);
				}
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
		throw new NoteDeFraisApiException(VALUE_MESSAGE_HEADER);
	}

	/**
	 * Vérifier que le frais est unique (couple nature + date)
	 * 
	 * @param id
	 * @param fraisFlat
	 * @return ResponseEntity<Boolean>
	 */
	private Boolean estUniqueDateAndNatureAndMission(Integer id, LigneDeFraisFlat fraisFlat) {

		LocalDate date = LocalDate.parse(fraisFlat.getDate(), DateTimeFormatter.ISO_DATE);
		List<LigneDeFrais> items = ligneDeFraisRepo.findByDateAndNatureAndNoteDeFraisMissionId(date,
				fraisFlat.getNature(), id);
		if (items.isEmpty()) {
			// le frais est unique
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Modifier la ligne de frais
	 * 
	 * @param id
	 *            Interger : id de la note de frais
	 * @return ResponseEntity<LigneDeFraisFlat>
	 */
	@PutMapping("/frais")
	public ResponseEntity<LigneDeFraisFlat> creerLigneDeFrais(@RequestBody LigneDeFraisFlat fraisFlat)
			throws NoteDeFraisApiException {
		// récupérer la note de frais

		LigneDeFrais frais = ligneDeFraisRepo.findOne(Integer.parseInt(fraisFlat.getId()));
		if (frais != null) {

			// parser le model en entité
			frais.setNature(fraisFlat.getNature());
			frais.setMontant(new BigDecimal(fraisFlat.getMontant()));
			frais.setDate(LocalDate.parse(fraisFlat.getDate(), DateTimeFormatter.ISO_DATE));
			
			// parser l'entité en model
			frais = this.ligneDeFraisRepo.save(frais);
		
			return ResponseEntity.ok(fraisFlat);
		}

		final String VALUE_MESSAGE_HEADER = "Pas de frais trouvé pour l'id : " + fraisFlat.getId();
		throw new NoteDeFraisApiException(VALUE_MESSAGE_HEADER);
	}

	@DeleteMapping("frais/{id}")
	public String supprimerFrais(@PathVariable Integer id) throws NoteDeFraisApiException {
		// récupérer la ligne de frais
		LigneDeFrais frais = ligneDeFraisRepo.findOne(id);
		if (null == frais)
			throw new NoteDeFraisApiException("La ligne de frais avec l'id " + id + " n'existe pas !");
		ligneDeFraisRepo.delete(id);
		return "Ligne de frais supprimées avec succès !";
	}

}
