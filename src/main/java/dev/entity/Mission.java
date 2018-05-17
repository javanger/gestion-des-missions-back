package dev.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import dev.model.Statut;
import dev.model.Transport;

@Entity
public class Mission {

	/** id : Integer */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** dateDebut : LocalDate */
	@Column(name = "DATE_DEBUT")
	private LocalDate dateDebut;

	/** dateFin : LocalDate */
	@Column(name = "DATE_FIN")
	private LocalDate dateFin;

	/** nature : Nature */
	@ManyToOne
	private Nature nature;

	/** villeDepart : String */
	@Column(name = "VILLE_DEPART")
	private String villeDepart;

	/** villeArrivee : String */
	@Column(name = "VILLE_ARRIVEE")
	private String villeArrivee;

	/** transport : Transport */
	@Enumerated(EnumType.STRING)
	private Transport transport;

	/** statut : Statut */
	@Enumerated(EnumType.STRING)
	private Statut statut;

	/** prime : Float */
	@Column(name = "PRIME", length = 6)
	private BigDecimal prime;

	/**
	 * collaborateur: Collaborateur
	 */
	@ManyToOne
	@JoinColumn(name = "ID_COLLABORATEUR")
	private Collaborateur collaborateur;

	public Mission() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param dateDebut
	 *            Date de début mission
	 * @param dateFin
	 *            Date de fin mission
	 * @param nature
	 *            Nature
	 * @param villeDepart
	 *            Ville de départ
	 * @param villeArrivee
	 *            Ville d'arrivée
	 * @param transport
	 *            Transport utilisé
	 * @param status
	 *            Status du traitement
	 */
	public Mission(LocalDate dateDebut, LocalDate dateFin, Nature nature, String villeDepart, String villeArrivee,
			Transport transport, Statut status) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nature = nature;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.transport = transport;
		this.statut = status;
	}

	/** Getter
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/** Getter
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/** Getter
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/** Getter
	 * @return the nature
	 */
	public Nature getNature() {
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
	public Transport getTransport() {
		return transport;
	}

	/** Getter
	 * @return the status
	 */
	public Statut getStatut() {
		return statut;
	}

	/** Getter
	 * @return the prime
	 */
	public BigDecimal getPrime() {
		return prime;
	}

	/** Getter
	 * @return the collaborateur
	 */
	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** Setter
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/** Setter
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/** Setter
	 * @param nature the nature to set
	 */
	public void setNature(Nature nature) {
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
	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	/** Setter
	 * @param status the status to set
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	/** Setter
	 * @param prime the prime to set
	 */
	public void setPrime(BigDecimal prime) {
		this.prime = prime;
	}

	/** Setter
	 * @param collaborateur the collaborateur to set
	 */
	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}

}
