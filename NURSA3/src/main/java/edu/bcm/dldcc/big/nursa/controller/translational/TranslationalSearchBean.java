package edu.bcm.dldcc.big.nursa.controller.translational;

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

import edu.bcm.dldcc.big.nursa.data.translational.LazyTranslationalDataLoader;
import edu.bcm.dldcc.big.nursa.model.common.TranslationalSynonym;
import edu.bcm.dldcc.big.nursa.model.search.TranslationalResult;
import edu.bcm.dldcc.big.nursa.model.search.TranslationalSearch;
import edu.bcm.dldcc.big.nursa.model.translational.Translational;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

@Stateful
@ViewScoped
@Named("translationalSearchService")
public class TranslationalSearchBean implements
		TranslationalSearchInterfaceBean {
	private TranslationalSynonym searchedTranslationalSynonym;

	@Inject
	@Param(required = false)
	private ParamValue<String> translationalType;

	private TranslationalSearch translationalSearch = new TranslationalSearch();

	private LazyDataModel<TranslationalResult> searchResults;

	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;

	@PostConstruct
	public void init() {
		if (this.translationalType.getValue() != null) {
			this.translationalSearch.getTranslationalTypes().add(
					this.translationalType.getValue());
		}
		searchResults = new LazyTranslationalDataLoader(objectEntityManager,
				this.translationalSearch);
	}

	@Override
	public void search() {

	}

	@Override
	public void reset() {
		setTranslationalSearch(new TranslationalSearch());
		init();
	}

	@Override
	public void redirect(Translational translational) {
		try {

			// doi.url should be absolute
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							FacesContext.getCurrentInstance()
									.getExternalContext()
									.getRequestContextPath()
									+ (translational.getDoi().getUrl()
											.charAt(0) == '/' ? translational
											.getDoi().getUrl() : "/"
											+ translational.getDoi().getUrl()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void redirect() {
		redirect(searchedTranslationalSynonym.getTranslational());

	}

	@Override
	public void redirect(TranslationalSynonym synonym) {
		redirect(synonym.getTranslational());

	}

	@Override
	public TranslationalSynonym getSearchedTranslationalSynonym() {
		return searchedTranslationalSynonym;
	}

	@Override
	public void setSearchedTranslationalSynonym(
			TranslationalSynonym searchedTranslationalSynonym) {
		this.searchedTranslationalSynonym = searchedTranslationalSynonym;

	}

	@Override
	public LazyDataModel<TranslationalResult> getSearchResults() {
		return searchResults;
	}

	@Override
	public void setSearchResults(LazyDataModel<TranslationalResult> searchResults) {
		this.searchResults = searchResults;

	}

	@Override
	public TranslationalSearch getTranslationalSearch() {
		return this.translationalSearch;
	}

	@Override
	public void setTranslationalSearch(TranslationalSearch translationalSearch) {
		this.translationalSearch = translationalSearch;

	}

}
