package dev.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entity.Nature;
import dev.model.NewNature;
import dev.repository.NatureRepository;
import dev.utils.ValidateDate;

@CrossOrigin
@RestController
@RequestMapping("/api/natures")
public class NatureController {

	/** natureRepository : NatureRepository */
	@Autowired
	private NatureRepository natureRepository;

	/**
	 * @return Liste de toutes les natures
	 */
	@GetMapping
	public ResponseEntity<?> searchJAll() {
		return ResponseEntity.ok(this.natureRepository.findAll());
	}

	/**
	 * @param id de la nature
	 * @return La nature correspondante
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> search(@PathVariable Integer id) {

		Optional<Nature> nature = natureRepository.findById(id);

		if (nature.isPresent()) {
			return ResponseEntity.ok(nature.get());
		} else {
			return ResponseEntity.badRequest().body("Aucune nature trouvée pour l'id " + id);
		}
	}

	/**
	 * @param newNature l'objet Nature à créer
	 * @return La nature crée ou un message de conflit
	 */
	@PostMapping
	public ResponseEntity<?> createNature(@RequestBody NewNature newNature) {

		Nature nature = new Nature();

		// Vérification de la date
		if (ValidateDate.validateInputDate(newNature.getDateFin())) {
			nature.setDateFin(LocalDate.parse(newNature.getDateFin()));
		} else {
			nature.setDateFin(LocalDate.MAX);
		}

		// Vérification de la prime
		if (newNature.isaUnePrime() && newNature.getPourcentagePrime() == null || !newNature.isaUnePrime()) {
			nature.setaUnePrime(false);
		} else {
			nature.setaUnePrime(true);
			nature.setPourcentagePrime(Integer.parseInt(newNature.getPourcentagePrime()));
		}

		// Vérification de la facturation
		if (newNature.isEstFacturee() && newNature.getTjm() == null || !newNature.isEstFacturee()) {
			nature.setEstFacturee(false);
		} else {
			nature.setEstFacturee(true);
			nature.setTjm(Integer.parseInt(newNature.getTjm()));
		}

		// Renvoie l'object Nature crée et le sauvegarde si un object semblable
		// n'existe pas déjà sinon renvoie une status conflict
		if (!natureRepository.existsByLibelleAndDateFinGreaterThan(newNature.getLibelle(), LocalDate.now())) {

			nature.setLibelle(newNature.getLibelle());

			natureRepository.save(nature);
			return ResponseEntity.ok(nature);

		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Nature déjà existante");
		}
	}
	
	/**
	 * @param id de la nature à supprimer
	 * @return Message de suppression ou d'erreur si nature non trouvée
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteNature(@PathVariable Integer id) {
		
		Optional<Nature> optionalNature = natureRepository.findById(id);
		
		if(optionalNature.isPresent()) {
			
			natureRepository.delete(optionalNature.get());
			
			return ResponseEntity.accepted().body("Nature " + id + " supprimée");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nature non trouvé");
		}
	}

}
