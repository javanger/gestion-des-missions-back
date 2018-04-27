/**
 * 
 */
package dev.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Alexis Darcy
 *
 */
@Entity
public class LigneDeFrais {

	/**
	 * id: Integer
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * date: LocalDate
	 */
	@Column(name = "DATE")
	private LocalDate date;
	/**
	 * nature: String
	 */
	@Column(name = "NATURE")
	private String nature;
	/**
	 * montant: Float
	 */
	@Column(name = "MONTANT")
	private BigDecimal montant;
	
	@ManyToOne
	@JoinColumn(name="ID_NOTE_DE_FRAIS")
	private NoteDeFrais noteDeFrais;

	/** constructor */
	public LigneDeFrais() {
		super();
	}

	/** constructor */
	public LigneDeFrais(LocalDate date, String nature, BigDecimal montant) {
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
	public BigDecimal getMontant() {
		return montant;
	}

	/**
	 * @param montant
	 *            the montant to set
	 */
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

}
