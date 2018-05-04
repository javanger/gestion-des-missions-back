package dev.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MissionDetailsFraisFlat {

	private String id;

	/** dateDebut : String */
	private String dateDebut;

	/** dateFin : String */
	private String dateFin;

	/** nature : String */
	private String nature;

	/** estimationPrime : String */
	private String estimationPrime;

	/** villeDepart : String */
	private String villeDepart;

	/** villeArrivee : String */
	private String villeArrivee;

	/** transport : String */
	private String transport;

	/** estEchue : boolean */
	private boolean estEchue;

	/** Constructeur */
	public MissionDetailsFraisFlat() {
		super();
	}

	/** Constructeur */
	public MissionDetailsFraisFlat(String id, String dateDebut, String dateFin, String nature, String estimationPrime,
			String villeDepart, String villeArrivee, String transport) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nature = nature;
		this.estimationPrime = estimationPrime;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.transport = transport;
		estEchue = LocalDate.parse(dateFin, DateTimeFormatter.ISO_DATE).isBefore(LocalDate.now());
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter
	 * 
	 * @return the dateDebut
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	/**
	 * Getter
	 * 
	 * @return the dateFin
	 */
	public String getDateFin() {
		return dateFin;
	}

	/**
	 * Getter
	 * 
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * Getter
	 * 
	 * @return the estimationPrime
	 */
	public String getEstimationPrime() {
		return estimationPrime;
	}

	/**
	 * Getter
	 * 
	 * @return the villeDepart
	 */
	public String getVilleDepart() {
		return villeDepart;
	}

	/**
	 * Getter
	 * 
	 * @return the villeArrivee
	 */
	public String getVilleArrivee() {
		return villeArrivee;
	}

	/**
	 * Getter
	 * 
	 * @return the transport
	 */
	public String getTransport() {
		return transport;
	}

	/**
	 * Getter
	 * 
	 * @return the estEchue
	 */
	public boolean isEstEchue() {
		return estEchue;
	}

	/**
	 * Setter
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Setter
	 * 
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Setter
	 * 
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Setter
	 * 
	 * @param nature
	 *            the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/**
	 * Setter
	 * 
	 * @param estimationPrime
	 *            the estimationPrime to set
	 */
	public void setEstimationPrime(String estimationPrime) {
		this.estimationPrime = estimationPrime;
	}

	/**
	 * Setter
	 * 
	 * @param villeDepart
	 *            the villeDepart to set
	 */
	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	/**
	 * Setter
	 * 
	 * @param villeArrivee
	 *            the villeArrivee to set
	 */
	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	/**
	 * Setter
	 * 
	 * @param transport
	 *            the transport to set
	 */
	public void setTransport(String transport) {
		this.transport = transport;
	}

	/**
	 * Setter
	 * 
	 * @param estEchue
	 *            the estEchue to set
	 */
	public void setEstEchue(boolean estEchue) {
		this.estEchue = estEchue;
	}

}
