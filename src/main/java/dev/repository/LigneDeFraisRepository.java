package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.LigneDeFrais;

public interface LigneDeFraisRepository extends JpaRepository<LigneDeFrais, Integer> {
	List<LigneDeFrais> findByNoteDeFrais(Integer id);
}
