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
	public LigneDeFrais(String nature, LocalDate date , BigDecimal montant, NoteDeFrais noteDeFrais) {
		super();
		this.date = date;
		this.nature = nature;
		this.montant = montant;
		this.noteDeFrais = noteDeFrais;
	}

	/** Getter
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/** Getter
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/** Getter
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}

	/** Getter
	 * @return the montant
	 */
	public BigDecimal getMontant() {
		return montant;
	}

	/** Getter
	 * @return the noteDeFrais
	 */
	public NoteDeFrais getNoteDeFrais() {
		return noteDeFrais;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** Setter
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/** Setter
	 * @param nature the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/** Setter
	 * @param montant the montant to set
	 */
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	/** Setter
	 * @param noteDeFrais the noteDeFrais to set
	 */
	public void setNoteDeFrais(NoteDeFrais noteDeFrais) {
		this.noteDeFrais = noteDeFrais;
	}


}
