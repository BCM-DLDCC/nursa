package edu.bcm.dldcc.big.nursa.controller.reagents;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.param.ParamValue;
import org.primefaces.model.LazyDataModel;

import edu.bcm.dldcc.big.nursa.data.reagents.LazyReagentDataLoader;
import edu.bcm.dldcc.big.nursa.model.common.ReagentSynonym;
import edu.bcm.dldcc.big.nursa.model.reagents.Reagent;
import edu.bcm.dldcc.big.nursa.model.search.ReagentResult;
import edu.bcm.dldcc.big.nursa.model.search.ReagentSearch;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

@Stateful
@ViewScoped
@Named("reagentSearchService")
public class ReagentSearchBean implements ReagentSearchInterfaceBean {

	private ReagentSynonym searchedReagentSynonym;
	
	@Inject
	@Param(required = false)
	private ParamValue<String> reagentType;
	
	private ReagentSearch reagentSearch = new ReagentSearch();
	
	private LazyDataModel<ReagentResult> searchResults; 
	
	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;
	
	@PostConstruct
	public void init() {
		if (this.reagentType.getValue() != null) {
			this.reagentSearch.getReagentTypes().add(this.reagentType.getValue());
		}
		searchResults = new LazyReagentDataLoader(objectEntityManager,
				this.reagentSearch);
	}
	
	@Override
	public void search() {

	}

	@Override
	public void reset() {
		setReagentSearch(new ReagentSearch());
		init();
	}

	@Override
	public void redirect(Reagent reagent) {
		try {

			// doi.url should be absolute
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							FacesContext.getCurrentInstance()
									.getExternalContext()
									.getRequestContextPath()
									+ (reagent.getDoi().getUrl().charAt(0) == '/' ? reagent
											.getDoi().getUrl() : "/"
											+ reagent.getDoi().getUrl()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void redirect() {
redirect(searchedReagentSynonym.getReagent());

	}

	@Override
	public void redirect(ReagentSynonym synonym) {
		redirect(synonym.getReagent());

	}

	@Override
	public ReagentSynonym getSearchedReagentSynonym() {
		return searchedReagentSynonym;
	}

	@Override
	public void setSearchedReagentSynonym(ReagentSynonym searchedReagentSynonym) {
this.searchedReagentSynonym = searchedReagentSynonym;

	}

	@Override
	public LazyDataModel<ReagentResult> getSearchResults() {
		return searchResults;
	}

	@Override
	public void setSearchResults(LazyDataModel<ReagentResult> searchResults) {
		this.searchResults = searchResults;
	}

	@Override
	public ReagentSearch getReagentSearch() {
		return this.reagentSearch;
	}

	@Override
	public void setReagentSearch(ReagentSearch reagentSearch) {
		this.reagentSearch = reagentSearch;

	}

}
