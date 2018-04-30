package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.NoteDeFrais;

public interface NoteDeFraisRepository extends JpaRepository<NoteDeFrais, Integer> {
}
