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
	
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * items: List<NoteItemFlat>
	 */
	private List<LigneDeFraisFlat> items;

	/** constructor */
	public NoteDeFraisFlat() {
		super();
	}

	/** constructor */
	public NoteDeFraisFlat(Integer id, List<LigneDeFraisFlat> items) {
		super();
		this.id = id;
		this.items = items;
	}

	/**
	 * @return the items
	 */
	public List<LigneDeFraisFlat> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<LigneDeFraisFlat> items) {
		this.items = items;
	}

}
