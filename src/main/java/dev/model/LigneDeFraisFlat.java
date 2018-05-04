/**
 * 
 */
package dev.model;

/**
 * @author ETY0006
 *
 */
public class LigneDeFraisFlat {

	/**
	 * id: String
	 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * date: String
	 */
	private String date;
	/**
	 * nature: String
	 */
	private String nature;
	/**
	 * montant: String
	 */
	private String montant;

	/**
	 * constructor
	 */
	public LigneDeFraisFlat() {
		super();
	}

	/**
	 * constructor
	 * 
	 * @param id
	 * @param date
	 * @param nature
	 * @param montant
	 */
	public LigneDeFraisFlat(String id, String date, String nature, String montant) {
		super();
		this.id = id;
		this.date = date;
		this.nature = nature;
		this.montant = montant;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
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
	public String getMontant() {
		return montant;
	}

	/**
	 * @param montant
	 *            the montant to set
	 */
	public void setMontant(String montant) {
		this.montant = montant;
	}

}
