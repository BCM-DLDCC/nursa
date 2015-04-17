package edu.bcm.dldcc.big.nursa.controller.translational;


import java.util.Map;

import javax.ejb.Local;

import org.primefaces.model.LazyDataModel;

import edu.bcm.dldcc.big.nursa.model.translational.Translational;


/**
 * The interface for the translational bean. Provides a framework for the
 * implementation layer and allows further decoupling later on.
 * 
 * @author jeremyeaston-marks
 * 
 */
@Local
public interface TranslationalInterfaceBean {
	/**
	 * 
	 * A stub method for later implementation to create a new Translational
	 * 
	 */
	void create();

	/**
	 * A stub method for later implementation to pass a translational that will be
	 * edited
	 * 
	 */
	void edit(Translational translational);

	/**
	 * 
	 * A stub method for later implementation to merge or persist a translational
	 * 
	 */
	void save();

	/**
	 * A stub method for later implementation of a method to delete the selected
	 * translational
	 */
	void delete();

	/**
	 * A stub method for later implementation of a method that cancels any
	 * changes made to a translational and rolls back the db
	 * 
	 */
	void cancel();

	/**
	 * Returns the translational that has been selected
	 * 
	 * @return The selected translational
	 */
	Translational getSelectedTranslational();

	/**
	 * Sets the translational that has been selected
	 * 
	 * @param selectedTranslational
	 *            The selected translational
	 */
	void setSelectedTranslational(Translational selectedTranslational);

	/**
	 * Gets the id of the translational id parameter that has been passed to it
	 * 
	 * @return The passed translational id
	 */
	Long getTranslationalId();

	/**
	 * Sets the id of the translational id parameter that has been passed to it.
	 * 
	 * @param translationalId
	 *            The passed translational id
	 */
	void setTranslationalId(Long translationalId);

	/**
	 * 
	 * Starts a conversation and then retrieves a translational from the database
	 * based on the translationalId
	 * 
	 */
	void updateSelectedTranslational();

	Map<String, LazyDataModel<?>> getLazyLoaders();

	void setLazyLoaders(Map<String, LazyDataModel<?>> lazyLoaders);
}
