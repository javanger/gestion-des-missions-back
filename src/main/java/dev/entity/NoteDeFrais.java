package dev.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Alexis Darcy
 */
@Entity
public class NoteDeFrais {

	/**
	 * id: Integer
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	Integer id;
	/**
	 * ligneDeFrais: LigneDeFrais
	 */
	@Column(name = "LIGNE_DE_FRAIS")
	LigneDeFrais ligneDeFrais;
	/**
	 * dateCreation: LocalDateTime
	 */
	@Column(name = "DATE_CREATION")
	LocalDateTime dateCreation;
	/**
	 * dateValidation: LocalDateTime
	 */
	@Column(name = "DATE_VALIDATION")
	LocalDateTime dateValidation;
	/**
	 * estValidee: Boolean
	 */
	@Column(name = "EST_VALIDEE")
	Boolean estValidee;
	/**
	 * estRejectee: Boolean
	 */
	@Column(name = "EST_REJETEE")
	Boolean estRejectee;
	/**
	 * mission: Mission
	 */
	@OneToMany
	Mission mission;

	/**
	 * constructor
	 */
	public NoteDeFrais() {
		super();
	}

	public NoteDeFrais(LigneDeFrais ligneDeFrais, LocalDateTime dateCreation, LocalDateTime dateValidation,
			Boolean estValidee, Boolean estRejectee, Mission mission) {
		super();
		this.ligneDeFrais = ligneDeFrais;
		this.dateCreation = dateCreation;
		this.dateValidation = dateValidation;
		this.estValidee = estValidee;
		this.estRejectee = estRejectee;
		this.mission = mission;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the ligneDeFrais
	 */
	public LigneDeFrais getLigneDeFrais() {
		return ligneDeFrais;
	}

	/**
	 * @param ligneDeFrais
	 *            the ligneDeFrais to set
	 */
	public void setLigneDeFrais(LigneDeFrais ligneDeFrais) {
		this.ligneDeFrais = ligneDeFrais;
	}

	/**
	 * @return the dateCreation
	 */
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the dateValidation
	 */
	public LocalDateTime getDateValidation() {
		return dateValidation;
	}

	/**
	 * @param dateValidation
	 *            the dateValidation to set
	 */
	public void setDateValidation(LocalDateTime dateValidation) {
		this.dateValidation = dateValidation;
	}

	/**
	 * @return the estValidee
	 */
	public Boolean getEstValidee() {
		return estValidee;
	}

	/**
	 * @param estValidee
	 *            the estValidee to set
	 */
	public void setEstValidee(Boolean estValidee) {
		this.estValidee = estValidee;
	}

	/**
	 * @return the estRejectee
	 */
	public Boolean getEstRejectee() {
		return estRejectee;
	}

	/**
	 * @param estRejectee
	 *            the estRejectee to set
	 */
	public void setEstRejectee(Boolean estRejectee) {
		this.estRejectee = estRejectee;
	}

	/**
	 * @return the mission
	 */
	public Mission getMission() {
		return mission;
	}

	/**
	 * @param mission
	 *            the mission to set
	 */
	public void setMission(Mission mission) {
		this.mission = mission;
	}

}
