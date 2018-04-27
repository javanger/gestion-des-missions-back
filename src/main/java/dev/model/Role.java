/**
 * 
 */
package dev.model;

/**
 * @author Alexis Darcy
 *
 */
public enum Role {
	ROLE_ADMINISTRATEUR("Administrateur"), ROLE_MANAGER("Manager"), ROLE_EMPLOYE("Employe");
	
	/**cat : String*/
	private String categorie;

	private Role(String categorie) {
		this.categorie = categorie;
	}

	/** Getter
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/** Setter
	 * @param categorie the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
}
