package dev.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.Nature;

public interface NatureRepository extends JpaRepository<Nature, Integer> {

	Optional<Nature> findByLibelle(String libelle);
	
	Boolean existsByLibelle(String libelle);

	// Renvoie True si une nature avec un même libellé et une date de fin ultérieure existe déjà
	Boolean existsByLibelleAndDateFinGreaterThan(String libelle, LocalDate dateDebut);
	
	// Renvoie la nature si une nature avec un même libellé et une date de fin
	// ultérieure existe déjà
	Optional<Nature> findByLibelleAndDateFinGreaterThan(String libelle, LocalDate dateDebut);

}