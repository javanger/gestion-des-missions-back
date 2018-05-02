package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
	
	List<Mission> findByCollaborateurMatricule(String matricule);
	
}
