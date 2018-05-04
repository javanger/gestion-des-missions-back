package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.NoteDeFrais;

public interface NoteDeFraisRepository extends JpaRepository<NoteDeFrais, Integer> {
	Optional<NoteDeFrais> findByMissionId(Integer id);
}
