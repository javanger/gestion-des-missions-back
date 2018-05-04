package dev.controller;

import java.time.LocalDate;
import java.util.Optional;

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

import dev.entity.Nature;
import dev.model.NewNature;
import dev.repository.NatureRepository;
import dev.utils.ValidateDate;

@CrossOrigin
@RestController
@RequestMapping("/api/natures")
public class NatureController {

	@Autowired
	private NatureRepository natureRepository;

	@GetMapping
	public ResponseEntity<?> searchAll() {
		return ResponseEntity.ok(this.natureRepository.findAll());
	}

	@GetMapping("/{libelle}")
	public ResponseEntity<?> search(@PathVariable String libelle) {

		Optional<Nature> nature = natureRepository.findByLibelle(libelle);

		if (nature.isPresent()) {
			return ResponseEntity.ok(nature.get());
		} else {
			return ResponseEntity.badRequest().body("Aucune nature trouvée pour le libellé " + libelle);
		}
	}

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

}
