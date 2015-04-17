package edu.bcm.dldcc.big.nursa.controller.reagents;


import javax.ejb.Local;

import org.primefaces.model.LazyDataModel;

import edu.bcm.dldcc.big.nursa.model.common.ReagentSynonym;
import edu.bcm.dldcc.big.nursa.model.reagents.Reagent;
import edu.bcm.dldcc.big.nursa.model.search.ReagentResult;
import edu.bcm.dldcc.big.nursa.model.search.ReagentSearch;

/**
 * The reagent search interface bean provides a framework for the
 * implementation layer and allows for further decoupleing later on.
 * 
 * 
 * @author jeremyeaston-marks
 * 
 */
@Local
public interface ReagentSearchInterfaceBean {

	/**
	 * Runs a search against the database using the information in the
	 * reagentSearch object. It stores that information in the searchResults
	 * object.
	 * 
	 */
	void search();

	/**
	 * Resets the reagent search and returns a full list for search
	 * 
	 */
	void reset();

	/**
	 * Sends a redirect to the browser that will bring them to the correct page.
	 * 
	 * @param reagent The reagent to be directed to.
	 */
	void redirect(Reagent reagent);
	
	/**
	 * Redirects the user to the searchReagent page
	 * 
	 */
	void redirect();

	/**
	 * Redirects the user to the reagent attached to that synonym
	 * 
	 * @param synonym
	 */
	void redirect(ReagentSynonym synonym);

	/**
	 * Returns the searchReagent synonym object
	 * 
	 * @return The searchReagentSynonym
	 */
	ReagentSynonym getSearchedReagentSynonym();

	/**
	 * Sets the searchReagent synonym object
	 * 
	 * @param searchedReagentSynonym The searchReagentSynonym
	 */
	void setSearchedReagentSynonym(ReagentSynonym searchedReagentSynonym);

	/**
	 * Returns a list of reagents that are the result of the search
	 * 
	 * @return A list of search results as reagents.
	 */
	LazyDataModel<ReagentResult> getSearchResults();

	/**
	 * Sets the list of reagents that are the results of a search
	 * 
	 * @param searchResults A list of search results as reagents
	 */
	void setSearchResults(LazyDataModel<ReagentResult> searchResults);

	/**
	 * Gets the reagent search object which contains all the search parameters.
	 * 
	 * @return The reagent search object.
	 */
	ReagentSearch getReagentSearch();

	/**
	 * Sets the reagent search object which contains all the search parameters.
	 * 
	 * @param reagentSearch The reagent search object.
	 */
	void setReagentSearch(ReagentSearch reagentSearch);
}
