/**
 * 
 */
package dev.model;

import java.util.List;

/**
 * @author Alexis Darcy
 *
 */
public class ApiCollaborateur {
	/** matricule : String */
	private String matricule;
	/** nom : String */
	private String nom;
	/** prenom : String */
	private String prenom;
	/** email : String */
	private String email;
	/** dateNaissance : LocalDate */
	private String dateNaissance;
	/** sexe : String */
	private String sexe;
	/** adresse : String */
	private String adresse;
	/** password : String */
	private String password;
	/** photo : String */
	private String photo;
	/** subalternes : List<String> */
	private List<String> subalternes;
	/** departement : String */
	private String departement;

	/**
	 * Constructeur
	 * 
	 * @param matricule
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param dateNaissance
	 * @param sexe
	 * @param adresse
	 * @param password
	 * @param photo
	 * @param subalternes
	 * @param departement
	 */
	public ApiCollaborateur(String matricule, String nom, String prenom, String email, String dateNaissance, String sexe,
			String adresse, String password, String photo, List<String> subalternes, String departement) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
		this.adresse = adresse;
		this.password = password;
		this.photo = photo;
		this.subalternes = subalternes;
		this.departement = departement;
	}

	/**
	 * Constructeur
	 * 
	 */
	public ApiCollaborateur() {
		super();
	}

	/**
	 * Getter
	 * 
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * Setter
	 * 
	 * @param matricule
	 *            the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Setter
	 * 
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Getter
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter
	 * 
	 * @return the dateNaissance
	 */
	public String getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * Setter
	 * 
	 * @param dateNaissance
	 *            the dateNaissance to set
	 */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * Getter
	 * 
	 * @return the sexe
	 */
	public String getSexe() {
		return sexe;
	}

	/**
	 * Setter
	 * 
	 * @param sexe
	 *            the sexe to set
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	/**
	 * Getter
	 * 
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Setter
	 * 
	 * @param adresse
	 *            the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * Getter
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter
	 * 
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * Setter
	 * 
	 * @param photo
	 *            the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * Getter
	 * 
	 * @return the subalternes
	 */
	public List<String> getSubalternes() {
		return subalternes;
	}

	/**
	 * Setter
	 * 
	 * @param subalternes
	 *            the subalternes to set
	 */
	public void setSubalternes(List<String> subalternes) {
		this.subalternes = subalternes;
	}

	/**
	 * Getter
	 * 
	 * @return the departement
	 */
	public String getDepartement() {
		return departement;
	}

	/**
	 * Setter
	 * 
	 * @param departement
	 *            the departement to set
	 */
	public void setDepartement(String departement) {
		this.departement = departement;
	}
}
