/**
 * 
 */
package dev.model;

/**
 * @author ETY0006
 *	Utilisé pour mappé l'id envoyé depuis le front
 */
public class VeryLigthMission {

	private Integer id;

	/** constructor */
	public VeryLigthMission() {
		super();
	}

	/** constructor */
	public VeryLigthMission(Integer id) {
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
