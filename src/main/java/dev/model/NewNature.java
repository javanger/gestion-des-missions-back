package dev.model;

public class NewNature {

	/** libelle : String */
	private String libelle;
	
	/** estFacturee : boolean */
	private boolean estFacturee;
	
	/** aUnePrime : boolean */
	private boolean aUnePrime;
	
	/** tjm : Integer */
	private String tjm;
	
	/** pourcentagePrime : Integer */
	private String pourcentagePrime;
	
	private String dateFin;
	
	public NewNature() {
		super();
	}

	/** Constructor
	 * @param libelle
	 * @param estFacturee
	 * @param aUnePrime
	 * @param tjm
	 * @param pourcentagePrime
	 * @param dateFin
	 */
	public NewNature(String libelle, boolean estFacturee, boolean aUnePrime, String tjm, String pourcentagePrime,
			String dateFin) {
		super();
		this.libelle = libelle;
		this.estFacturee = estFacturee;
		this.aUnePrime = aUnePrime;
		this.tjm = tjm;
		this.pourcentagePrime = pourcentagePrime;
		this.dateFin = dateFin;
	}

	/** Getter
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/** Getter
	 * @return the estFacturee
	 */
	public boolean isEstFacturee() {
		return estFacturee;
	}

	/** Getter
	 * @return the aUnePrime
	 */
	public boolean isaUnePrime() {
		return aUnePrime;
	}

	/** Getter
	 * @return the tjm
	 */
	public String getTjm() {
		return tjm;
	}

	/** Getter
	 * @return the pourcentagePrime
	 */
	public String getPourcentagePrime() {
		return pourcentagePrime;
	}

	/** Getter
	 * @return the dateFin
	 */
	public String getDateFin() {
		return dateFin;
	}

	/** Setter
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/** Setter
	 * @param estFacturee the estFacturee to set
	 */
	public void setEstFacturee(boolean estFacturee) {
		this.estFacturee = estFacturee;
	}

	/** Setter
	 * @param aUnePrime the aUnePrime to set
	 */
	public void setaUnePrime(boolean aUnePrime) {
		this.aUnePrime = aUnePrime;
	}

	/** Setter
	 * @param tjm the tjm to set
	 */
	public void setTjm(String tjm) {
		this.tjm = tjm;
	}

	/** Setter
	 * @param pourcentagePrime the pourcentagePrime to set
	 */
	public void setPourcentagePrime(String pourcentagePrime) {
		this.pourcentagePrime = pourcentagePrime;
	}

	/** Setter
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	
}
