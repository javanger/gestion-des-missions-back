package dev.model;

public class NewMission {
	
	/** dateDebut : String */
	private String dateDebut;
	
	/** dateFin : String */
	private String dateFin;
	
	/** nature : String */
	private String nature;
	
	/** villeDepart : String */
	private String villeDepart;
	
	/** villeArrivee : String */
	private String villeArrivee;
	
	/** transport : String */
	private String transport;

	public NewMission() {
		super();
	}

	/** Constructor
	 * @param dateDebut
	 * @param dateFin
	 * @param nature
	 * @param villeDepart
	 * @param villeArrivee
	 * @param transport
	 */
	public NewMission(String dateDebut, String dateFin, String nature, String villeDepart, String villeArrivee,
			String transport) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nature = nature;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.transport = transport;
	}

	/** Getter
	 * @return the dateDebut
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	/** Getter
	 * @return the dateFin
	 */
	public String getDateFin() {
		return dateFin;
	}

	/** Getter
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}

	/** Getter
	 * @return the villeDepart
	 */
	public String getVilleDepart() {
		return villeDepart;
	}

	/** Getter
	 * @return the villeArrivee
	 */
	public String getVilleArrivee() {
		return villeArrivee;
	}

	/** Getter
	 * @return the transport
	 */
	public String getTransport() {
		return transport;
	}

	/** Setter
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	/** Setter
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	/** Setter
	 * @param nature the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/** Setter
	 * @param villeDepart the villeDepart to set
	 */
	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	/** Setter
	 * @param villeArrivee the villeArrivee to set
	 */
	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	/** Setter
	 * @param transport the transport to set
	 */
	public void setTransport(String transport) {
		this.transport = transport;
	}

}
