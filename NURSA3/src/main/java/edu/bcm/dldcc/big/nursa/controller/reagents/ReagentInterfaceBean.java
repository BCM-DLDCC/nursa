package edu.bcm.dldcc.big.nursa.controller.reagents;


import java.util.Map;

import javax.ejb.Local;

import org.primefaces.model.LazyDataModel;

import edu.bcm.dldcc.big.nursa.model.reagents.Reagent;


/**
 * The interface for the reagent bean. Provides a framework for the
 * implementation layer and allows further decoupling later on.
 * 
 * @author jeremyeaston-marks
 * 
 */
@Local
public interface ReagentInterfaceBean {
	/**
	 * 
	 * A stub method for later implementation to create a new Reagent
	 * 
	 */
	void create();

	/**
	 * A stub method for later implementation to pass a reagent that will be
	 * edited
	 * 
	 */
	void edit(Reagent reagent);

	/**
	 * 
	 * A stub method for later implementation to merge or persist a reagent
	 * 
	 */
	void save();

	/**
	 * A stub method for later implementation of a method to delete the selected
	 * reagent
	 */
	void delete();

	/**
	 * A stub method for later implementation of a method that cancels any
	 * changes made to a reagent and rolls back the db
	 * 
	 */
	void cancel();

	/**
	 * Returns the reagent that has been selected
	 * 
	 * @return The selected reagent
	 */
	Reagent getSelectedReagent();

	/**
	 * Sets the reagent that has been selected
	 * 
	 * @param selectedReagent
	 *            The selected reagent
	 */
	void setSelectedReagent(Reagent selectedReagent);

	/**
	 * Gets the id of the reagent id parameter that has been passed to it
	 * 
	 * @return The passed reagent id
	 */
	Long getReagentId();

	/**
	 * Sets the id of the reagent id parameter that has been passed to it.
	 * 
	 * @param reagentId
	 *            The passed reagent id
	 */
	void setReagentId(Long reagentId);

	/**
	 * 
	 * Starts a conversation and then retrieves a reagent from the database
	 * based on the reagentId
	 * 
	 */
	void updateSelectedReagent();

	Map<String, LazyDataModel<?>> getLazyLoaders();

	void setLazyLoaders(Map<String, LazyDataModel<?>> lazyLoaders);
}
