/**
 * 
 */
package dev.model;

/**
 * @author ETY0006
 *
 */
public class NoteItemFlat {

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

	/** constructor */
	public NoteItemFlat() {
		super();
	}

	/** constructor */
	public NoteItemFlat(String date, String nature, String montant) {
		super();
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
