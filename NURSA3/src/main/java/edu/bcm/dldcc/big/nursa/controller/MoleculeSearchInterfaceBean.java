package edu.bcm.dldcc.big.nursa.controller;


import javax.ejb.Local;

import org.primefaces.model.LazyDataModel;

import edu.bcm.dldcc.big.nursa.model.Molecule;
import edu.bcm.dldcc.big.nursa.model.common.MoleculeSynonym;
import edu.bcm.dldcc.big.nursa.model.search.MoleculeResult;
import edu.bcm.dldcc.big.nursa.model.search.MoleculeSearch;

/**
 * The molecule search interface bean provides a framework for the
 * implementation layer and allows for further decoupleing later on.
 * 
 * 
 * @author jeremyeaston-marks
 * 
 */
@Local
public interface MoleculeSearchInterfaceBean {

	/**
	 * Runs a search against the database using the information in the
	 * moleculeSearch object. It stores that information in the searchResults
	 * object.
	 * 
	 */
	void search();

	/**
	 * Resets the molecule search and returns a full list for search
	 * 
	 */
	void reset();

	/**
	 * Sends a redirect to the browser that will bring them to the correct page.
	 * 
	 * @param mol The molecule to be directed to.
	 */
	void redirect(Molecule mol);
	
	/**
	 * Redirects the user to the searchMolecule page
	 * 
	 */
	void redirect();

	/**
	 * Redirects the user to the molecule attached to that synonym
	 * 
	 * @param synonym
	 */
	void redirect(MoleculeSynonym synonym);

	/**
	 * Returns the searchMolecule synonym object
	 * 
	 * @return The searchMoleculeSynonym
	 */
	MoleculeSynonym getSearchedMoleculeSynonym();

	/**
	 * Sets the searchMolecule synonym object
	 * 
	 * @param searchedMoleculeSynonym The searchMoleculeSynonym
	 */
	void setSearchedMoleculeSynonym(MoleculeSynonym searchedMoleculeSynonym);

	/**
	 * Returns a list of molecules that are the result of the search
	 * 
	 * @return A list of search results as molecules.
	 */
	LazyDataModel<MoleculeResult> getSearchResults();

	/**
	 * Sets the list of molecules that are the results of a search
	 * 
	 * @param searchResults A list of search results as molecules
	 */
	void setSearchResults(LazyDataModel<MoleculeResult> searchResults);

	/**
	 * Gets the molecule search object which contains all the search parameters.
	 * 
	 * @return The molecule search object.
	 */
	MoleculeSearch getMoleculeSearch();

	/**
	 * Sets the molecule search object which contains all the search parameters.
	 * 
	 * @param moleculeSearch The molecule search object.
	 */
	void setMoleculeSearch(MoleculeSearch moleculeSearch);
}
