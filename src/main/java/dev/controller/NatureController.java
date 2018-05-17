package dev.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import dev.entity.Mission;
import dev.entity.Nature;
import dev.model.NewNature;
import dev.repository.MissionRepository;
import dev.repository.NatureRepository;
import dev.utils.ValidateDate;

@CrossOrigin
@RestController
@RequestMapping("/api/natures")
public class NatureController {

	@Autowired
	private NatureRepository natureRepository;
	@Autowired
	private MissionRepository missionRepository;

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
			return ResponseEntity.ok(this.natureRepository.findAll());

		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Nature déjà existante");
		}
	}

	@PutMapping(path = { "/{libelle}" })
	public ResponseEntity<?> modifiedNature(@RequestBody NewNature modifiedNature) {

		Nature nature = new Nature();

		// Vérification de la date
		if (ValidateDate.validateInputDate(modifiedNature.getDateFin())) {
			nature.setDateFin(LocalDate.parse(modifiedNature.getDateFin()));
		} else {
			nature.setDateFin(LocalDate.MAX);
		}

		// Vérification de la prime
		if (modifiedNature.isaUnePrime() && modifiedNature.getPourcentagePrime() == null || !modifiedNature.isaUnePrime()) {
			nature.setaUnePrime(false);
		} else {
			nature.setaUnePrime(true);
			nature.setPourcentagePrime(Integer.parseInt(modifiedNature.getPourcentagePrime()));
		}

		// Vérification de la facturation
		if (modifiedNature.isEstFacturee() && modifiedNature.getTjm() == null || !modifiedNature.isEstFacturee()) {
			nature.setEstFacturee(false);
		} else {
			nature.setEstFacturee(true);
			nature.setTjm(Integer.parseInt(modifiedNature.getTjm()));
		}

		// Renvoie l'object Nature crée et le sauvegarde si un object semblable
		// n'existe pas déjà sinon renvoie une status conflict
		if (!natureRepository.existsByLibelleAndDateFinGreaterThan(modifiedNature.getLibelle(), LocalDate.now())) {

			nature.setLibelle(modifiedNature.getLibelle());

			natureRepository.save(nature);
			return ResponseEntity.ok(nature);

		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Nature déjà existante");
		}
	}
	
	@DeleteMapping(path = { "/{libelle}" })
	public ResponseEntity<?> deleteNature(@PathVariable("libelle") String libelle) {
		Nature nature = new Nature();
		if (natureRepository.existsByLibelleAndDateFinGreaterThan(libelle, LocalDate.now())) {
			nature = natureRepository.findByLibelleAndDateFinGreaterThan(libelle, LocalDate.now()).get();
			List<Mission> listMission = missionRepository.findByNature(nature);
			if (listMission.isEmpty()) {
				natureRepository.delete(nature);
			} else {
				nature.setDateFin(LocalDate.now());
				natureRepository.save(nature);
			}
			return ResponseEntity.ok(this.natureRepository.findAll());
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Impossible de supprimer la nature");
		}
	}
}
