package dev.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.LigneDeFrais;
import dev.entity.NoteDeFrais;

public interface LigneDeFraisRepository extends JpaRepository<LigneDeFrais, Integer> {
	List<LigneDeFrais> findByNoteDeFrais(NoteDeFrais note);
	List<LigneDeFrais> findByDateAndNatureAndNoteDeFraisMissionId(LocalDate date, String nature, Integer idMission);
}
