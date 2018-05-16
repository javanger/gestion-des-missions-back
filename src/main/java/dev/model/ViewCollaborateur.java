/**
 * 
 */
package dev.model;

/**
 * @author Alexis Darcy
 *
 */
/**
 * @author Alexis Darcy
 *
 */
public class ViewCollaborateur {
	/** matricule : String */
	private String matricule;
	/** nom : String */
	private String nom;
	/** prenom : String */
	private String prenom;
	/** role : Role */
	private Role role;

	/**
	 * Constructeur
	 * 
	 */
	public ViewCollaborateur() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param matricule
	 * @param nom
	 * @param prenom
	 * @param role
	 */
	public ViewCollaborateur(String matricule, String nom, String prenom, Role role) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
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
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Setter
	 * 
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
