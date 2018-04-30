/**
 * 
 */
package dev.model;

import java.util.List;

/**
 * @author ETY0006
 *
 */
public class NoteDeFraisFlat {

	/**
	 * dateDebut: String
	 */
	private String dateDebut;
	/**
	 * dateFin: String
	 */
	private String dateFin;
	/**
	 * estimationPrime: String
	 */
	private String estimationPrime;
	/**
	 * nature: String
	 */
	private String nature;
	/**
	 * villeDepart: String
	 */
	private String villeDepart;
	/**
	 * villeArrivee: String
	 */
	private String villeArrivee;

	/**
	 * items: List<NoteItemFlat>
	 */
	private List<NoteItemFlat> items;

	/** constructor */
	public NoteDeFraisFlat() {
		super();
	}

	/** constructor */
	public NoteDeFraisFlat(String dateDebut, String dateFin, String estimationPrime, String nature, String villeDepart,
			String villeArrivee, List<NoteItemFlat> items) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.estimationPrime = estimationPrime;
		this.nature = nature;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.items = items;
	}

	/**
	 * @return the dateDebut
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public String getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the estimationPrime
	 */
	public String getEstimationPrime() {
		return estimationPrime;
	}

	/**
	 * @param estimationPrime
	 *            the estimationPrime to set
	 */
	public void setEstimationPrime(String estimationPrime) {
		this.estimationPrime = estimationPrime;
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
	 * @return the villeDepart
	 */
	public String getVilleDepart() {
		return villeDepart;
	}

	/**
	 * @param villeDepart
	 *            the villeDepart to set
	 */
	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	/**
	 * @return the villeArrivee
	 */
	public String getVilleArrivee() {
		return villeArrivee;
	}

	/**
	 * @param villeArrivee
	 *            the villeArrivee to set
	 */
	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	/**
	 * @return the items
	 */
	public List<NoteItemFlat> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<NoteItemFlat> items) {
		this.items = items;
	}

}
