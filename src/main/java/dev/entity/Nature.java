package dev.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nature {

	/** id : Integer */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** libelle : String */
	@Column(name = "LIBELLE", length = 30)
	private String libelle;
	
	/** estFacturee : boolean */
	@Column(name = "EST_FACTUREE")
	private boolean estFacturee;
	
	/** aUnePrime : boolean */
	@Column(name = "A_UNE_PRIME")
	private boolean aUnePrime;
	
	/** tjm : Integer */
	@Column(name = "TJM")
	private Integer tjm;
	
	/** pourcentagePrime : Integer */
	@Column(name = "POURCENTAGE_PRIME")
	private Integer pourcentagePrime;
	
	@Column(name = "DATE_FIN")
	private LocalDate dateFin;
	
	public Nature() {
		super();
	}

	/** Constructor
	 * @param libelle Libelle de la prime
	 * @param estFacturee Possède une facturation ou non
	 * @param aUnePrime Possède une prime ou non
	 * @param tjm Taux journalier si facturation
	 * @param pourcentagePrime Pourcentage de la prime
	 */
	public Nature(String libelle, boolean estFacturee, boolean aUnePrime, Integer tjm, Integer pourcentagePrime) {
		super();
		this.libelle = libelle;
		this.estFacturee = estFacturee;
		this.aUnePrime = aUnePrime;
		this.tjm = tjm;
		this.pourcentagePrime = pourcentagePrime;
	}

	/** Getter
	 * @return the id
	 */
	public Integer getId() {
		return id;
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
	public Integer getTjm() {
		return tjm;
	}

	/** Getter
	 * @return the pourcentagePrime
	 */
	public Integer getPourcentagePrime() {
		return pourcentagePrime;
	}

	/** Getter
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	public void setTjm(Integer tjm) {
		this.tjm = tjm;
	}

	/** Setter
	 * @param pourcentagePrime the pourcentagePrime to set
	 */
	public void setPourcentagePrime(Integer pourcentagePrime) {
		this.pourcentagePrime = pourcentagePrime;
	}

	/** Setter
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

}
