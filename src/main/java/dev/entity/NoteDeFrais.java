package dev.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	 * dateCreation: LocalDateTime
	 */
	@Column(name = "DATE_CREATION")
	LocalDateTime dateCreation;
	/**
	 * dateValidation: LocalDateTime
	 */
	@Column(name = "DATE_VALIDATION", nullable=true)
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
	@OneToOne
    @JoinColumn(name="ID_MISSION")
	Mission mission;

	/**
	 * constructor
	 */
	public NoteDeFrais() {
		super();
	}

	public NoteDeFrais(Mission mission) {
		super();
		this.dateCreation = LocalDateTime.now();
		this.dateValidation = null;
		this.estValidee = false;
		this.estRejectee = false;
		this.mission = mission;
	}
	
	/** 
	* constructor 
	* @param dateCreation
	* @param dateValidation
	* @param estValidee
	* @param estRejectee
	* @param mission
	*/
	public NoteDeFrais(LocalDateTime dateValidation,
			Boolean estValidee, Boolean estRejectee, Mission mission) {
		super();
		this.dateCreation = LocalDateTime.now();
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
