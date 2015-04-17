package edu.bcm.dldcc.big.nursa.controller.translational;


import javax.ejb.Local;

import org.primefaces.model.LazyDataModel;

import edu.bcm.dldcc.big.nursa.model.common.TranslationalSynonym;
import edu.bcm.dldcc.big.nursa.model.search.TranslationalResult;
import edu.bcm.dldcc.big.nursa.model.search.TranslationalSearch;
import edu.bcm.dldcc.big.nursa.model.translational.Translational;

/**
 * The translational search interface bean provides a framework for the
 * implementation layer and allows for further decoupleing later on.
 * 
 * 
 * @author jeremyeaston-marks
 * 
 */
@Local
public interface TranslationalSearchInterfaceBean {

	/**
	 * Runs a search against the database using the information in the
	 * translationalSearch object. It stores that information in the searchResults
	 * object.
	 * 
	 */
	void search();

	/**
	 * Resets the translational search and returns a full list for search
	 * 
	 */
	void reset();

	/**
	 * Sends a redirect to the browser that will bring them to the correct page.
	 * 
	 * @param translational The translational to be directed to.
	 */
	void redirect(Translational translational);
	
	/**
	 * Redirects the user to the searchTranslational page
	 * 
	 */
	void redirect();

	/**
	 * Redirects the user to the translational attached to that synonym
	 * 
	 * @param synonym
	 */
	void redirect(TranslationalSynonym synonym);

	/**
	 * Returns the searchTranslational synonym object
	 * 
	 * @return The searchTranslationalSynonym
	 */
	TranslationalSynonym getSearchedTranslationalSynonym();

	/**
	 * Sets the searchTranslational synonym object
	 * 
	 * @param searchedTranslationalSynonym The searchTranslationalSynonym
	 */
	void setSearchedTranslationalSynonym(TranslationalSynonym searchedTranslationalSynonym);

	/**
	 * Returns a list of translationals that are the result of the search
	 * 
	 * @return A list of search results as translationals.
	 */
	LazyDataModel<TranslationalResult> getSearchResults();

	/**
	 * Sets the list of translationals that are the results of a search
	 * 
	 * @param searchResults A list of search results as translationals
	 */
	void setSearchResults(LazyDataModel<TranslationalResult> searchResults);

	/**
	 * Gets the translational search object which contains all the search parameters.
	 * 
	 * @return The translational search object.
	 */
	TranslationalSearch getTranslationalSearch();

	/**
	 * Sets the translational search object which contains all the search parameters.
	 * 
	 * @param translationalSearch The translational search object.
	 */
	void setTranslationalSearch(TranslationalSearch translationalSearch);

	
}
