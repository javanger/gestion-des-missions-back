package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.Nature;

public interface NatureRepository extends JpaRepository<Nature, Integer> {

	Optional<Nature> findByLibelle(String libelle);
	
	Boolean existsByLibelle(String libelle);
	
}
