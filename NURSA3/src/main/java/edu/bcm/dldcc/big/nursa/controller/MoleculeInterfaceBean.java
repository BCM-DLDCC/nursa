package edu.bcm.dldcc.big.nursa.controller;


import java.util.Map;

import javax.ejb.Local;

import org.primefaces.model.LazyDataModel;

import edu.bcm.dldcc.big.nursa.model.Molecule;

/**
 * The interface for the molecule bean. Provides a framework for the
 * implementation layer and allows further decoupling later on.
 * 
 * @author jeremyeaston-marks
 * 
 */
@Local
public interface MoleculeInterfaceBean {
	/**
	 * 
	 * A stub method for later implementation to create a new Molecule
	 * 
	 */
	void create();

	/**
	 * A stub method for later implementation to pass a molecule that will be
	 * edited
	 * 
	 */
	void edit(Molecule molecule);

	/**
	 * 
	 * A stub method for later implementation to merge or persist a molecule
	 * 
	 */
	void save();

	/**
	 * A stub method for later implementation of a method to delete the selected
	 * molecule
	 */
	void delete();

	/**
	 * A stub method for later implementation of a method that cancels any
	 * changes made to a molecule and rolls back the db
	 * 
	 */
	void cancel();

	/**
	 * Returns the molecule that has been selected
	 * 
	 * @return The selected molecule
	 */
	Molecule getSelectedMolecule();

	/**
	 * Sets the molecule that has been selected
	 * 
	 * @param selectedMolecule
	 *            The selected molecule
	 */
	void setSelectedMolecule(Molecule selectedMolecule);

	/**
	 * Gets the id of the molecule id parameter that has been passed to it
	 * 
	 * @return The passed molecule id
	 */
	Long getMoleculeId();

	/**
	 * Sets the id of the molecule id parameter that has been passed to it.
	 * 
	 * @param moleculeId
	 *            The passed molecule id
	 */
	void setMoleculeId(Long moleculeId);

	/**
	 * 
	 * Starts a conversation and then retrieves a molecule from the database
	 * based on the moleculeId
	 * 
	 */
	void updateSelectedMolecule();

	Map<String, LazyDataModel<?>> getLazyLoaders();

	void setLazyLoaders(Map<String, LazyDataModel<?>> lazyLoaders);
}
