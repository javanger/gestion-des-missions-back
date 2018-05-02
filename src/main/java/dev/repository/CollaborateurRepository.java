package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.Collaborateur;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Integer> {
	
	Optional<Collaborateur> findByMatricule(String matricule);
	
}
