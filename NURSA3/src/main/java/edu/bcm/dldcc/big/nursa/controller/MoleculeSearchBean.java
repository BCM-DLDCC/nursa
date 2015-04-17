package edu.bcm.dldcc.big.nursa.controller;

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

import edu.bcm.dldcc.big.nursa.data.LazyMoleculeDataLoader;
import edu.bcm.dldcc.big.nursa.model.Molecule;
import edu.bcm.dldcc.big.nursa.model.common.MoleculeSynonym;
import edu.bcm.dldcc.big.nursa.model.search.MoleculeResult;
import edu.bcm.dldcc.big.nursa.model.search.MoleculeSearch;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

@Stateful
@ViewScoped
@Named("moleculeSearchService")
public class MoleculeSearchBean implements MoleculeSearchInterfaceBean {
	private MoleculeSynonym searchedMoleculeSynonym;

	@Inject
	@Param(required = false)
	private ParamValue<String> molType;

	private MoleculeSearch moleculeSearch = new MoleculeSearch();

	private LazyDataModel<MoleculeResult> searchResults;

	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;

	@PostConstruct
	public void init() {
		if (this.molType.getValue() != null) {
			this.moleculeSearch.getMoleculeTypes().add(this.molType.getValue());
		}
		searchResults = new LazyMoleculeDataLoader(objectEntityManager, this.moleculeSearch);
	}

	@Override
	public void search() {
	}

	@Override
	public void reset() {
		setMoleculeSearch(new MoleculeSearch());
		init();
	}

	@Override
	public void redirect(Molecule mol) {
		try {

			// doi.url should be absolute
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							FacesContext.getCurrentInstance()
									.getExternalContext()
									.getRequestContextPath()
									+ (mol.getDoi().getUrl().charAt(0) == '/' ? mol
											.getDoi().getUrl() : "/"
											+ mol.getDoi().getUrl()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void redirect() {
		redirect(searchedMoleculeSynonym.getMolecule());
	}

	@Override
	public void redirect(MoleculeSynonym synonym) {
		redirect(synonym.getMolecule());
	}

	@Override
	public MoleculeSynonym getSearchedMoleculeSynonym() {
		return searchedMoleculeSynonym;
	}

	@Override
	public void setSearchedMoleculeSynonym(
			MoleculeSynonym searchedMoleculeSynonym) {
		this.searchedMoleculeSynonym = searchedMoleculeSynonym;
	}

	@Override
	public MoleculeSearch getMoleculeSearch() {
		return moleculeSearch;
	}

	@Override
	public void setMoleculeSearch(MoleculeSearch moleculeSearch) {
		this.moleculeSearch = moleculeSearch;
	}

	@Override
	public LazyDataModel<MoleculeResult> getSearchResults() {
		return searchResults;
	}

	@Override
	public void setSearchResults(LazyDataModel<MoleculeResult> searchResults) {
		this.searchResults = searchResults;
	}
}
