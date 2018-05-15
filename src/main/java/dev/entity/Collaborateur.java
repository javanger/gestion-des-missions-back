package dev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.model.Role;

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
	/** motDePasse : String */
	@Column(name = "MOT_DE_PASSE", nullable = false)
	private String motDePasse;
	/** estActif : Boolean */
	@Column(name = "EST_ACTIF")
	private Boolean estActif;
	/** role : String */
	@Column(name = "ROLE", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

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
	 * @param motDePasse
	 * @param role
	 */
	public Collaborateur(String matricule, String motDePasse, Role role) {
		super();
		this.matricule = matricule;
		this.motDePasse = motDePasse;
		this.estActif = true;
		this.role = role;
	}
  
  public Collaborateur(String matricule, Role role) {
		super();
		this.matricule = matricule;
		this.estActif = true;
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
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * Setter
	 * 
	 * @param motDePasse
	 *            the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * Getter
	 * 
	 * @return the estActif
	 */
	public Boolean getEstActif() {
		return estActif;
	}

	/**
	 * Setter
	 * 
	 * @param estActif
	 *            the estActif to set
	 */
	public void setEstActif(Boolean estActif) {
		this.estActif = estActif;
	}

	/**
	 * Getter
	 * 
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Setter
	 * 
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
