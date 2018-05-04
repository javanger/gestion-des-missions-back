/**
 * 
 */
package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entity.Collaborateur;

/**
 * @author Alexis Darcy
 *
 */
public interface CollaborateurRepository extends JpaRepository<Collaborateur, Integer> {
	Collaborateur findByMatricule(String matricule);

}
