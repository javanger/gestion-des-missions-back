/**
 * 
 */
package dev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Alexis Darcy
 *
 */
@Entity
@Table(name = "COLLABORATEUR")
public class Collaborateur {
	/** id : Integer */
	@JsonIgnore
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** matricule : String */
	@Column(name = "MATRICULE", nullable = false, unique = true)
	private String matricule;
	/** role : String */
	@Column(name = "ROLE", nullable = false, unique = true)
	private String role;

	/**
	 * Constructeur
	 * 
	 */
	public Collaborateur() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param matricule
	 * @param role
	 */
	public Collaborateur(String matricule, String role) {
		super();
		this.matricule = matricule;
		this.role = role;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Getter
	 * 
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * Setter
	 * 
	 * @param matricule
	 *            the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * Getter
	 * 
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Setter
	 * 
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
