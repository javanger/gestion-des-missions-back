/**
 * 
 */
package dev.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Alexis Darcy
 *
 */
public class LigneDeFrais {

	/**
	 * id: Integer
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	/**
	 * date: LocalDate
	 */
	@Column(name = "DATE")
	LocalDate date;
	/**
	 * nature: String
	 */
	@Column(name = "NATURE")
	String nature;
	/**
	 * montant: Float
	 */
	@Column(name = "MONTANT")
	Float montant;

	/** constructor */
	public LigneDeFrais() {
		super();
	}

	/** constructor */
	public LigneDeFrais(LocalDate date, String nature, Float montant) {
		super();
		this.date = date;
		this.nature = nature;
		this.montant = montant;
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
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * @param nature
	 *            the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/**
	 * @return the montant
	 */
	public Float getMontant() {
		return montant;
	}

	/**
	 * @param montant
	 *            the montant to set
	 */
	public void setMontant(Float montant) {
		this.montant = montant;
	}

}
