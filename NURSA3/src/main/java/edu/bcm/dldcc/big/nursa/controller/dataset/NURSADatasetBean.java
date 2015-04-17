package edu.bcm.dldcc.big.nursa.controller.dataset;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.param.ParamValue;
import org.primefaces.model.LazyDataModel;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.bcm.dldcc.big.nursa.controller.util.AdminBean;
import edu.bcm.dldcc.big.nursa.data.dataset.LazyNursaDatasetMoleculeDataLoader;
import edu.bcm.dldcc.big.nursa.data.dataset.LazyNursaDatasetReagentDataLoader;
import edu.bcm.dldcc.big.nursa.model.common.DOI;
import edu.bcm.dldcc.big.nursa.model.common.DOI_;
import edu.bcm.dldcc.big.nursa.model.dataset.NURSADataset;
import edu.bcm.dldcc.big.nursa.model.dataset.NURSADataset_;
import edu.bcm.dldcc.big.nursa.model.search.DatasetMoleculeResult;
import edu.bcm.dldcc.big.nursa.model.search.DatasetReagentResult;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

@Stateful
@ViewScoped
@Named("datasetService")
public class NURSADatasetBean implements NURSADatasetInterfaceBean {

	@Inject
	@Param(required = true)
	private ParamValue<String> doi; // passed DOI

	private NURSADataset selectedDataset;

	@Inject
	@Named("admin")
	private AdminBean admin;

	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;
	
	private LazyDataModel<DatasetMoleculeResult> moleculeSearchResults;
	private LazyDataModel<DatasetReagentResult> reagentResults; 
	
	@Override
	@PostConstruct
	public void updateSelectedDataset() {
		
		
		admin.startConversation();

		CriteriaBuilder cb = objectEntityManager.getCriteriaBuilder();

		CriteriaQuery<NURSADataset> cq = cb.createQuery(NURSADataset.class);
		Root<NURSADataset> nds = cq.from(NURSADataset.class);
		Join<NURSADataset, DOI> doi = nds.join(NURSADataset_.doi);

		cq.where(cb.equal(doi.get(DOI_.doi), this.doi.getValue()));
		cq.select(nds);
		TypedQuery<NURSADataset> result = objectEntityManager.createQuery(cq);

		List<NURSADataset> results = result.getResultList();

		// no result found
		if (results.isEmpty()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"No datasets was found with that DOI", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			setSelectedDataset(null);
		}

		// multiple results found (unlikely, I hope!)
		else if (results.size() > 1) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Multiple datasets were found with that DOI", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			setSelectedDataset(null);
		}

		else {
			setSelectedDataset(results.get(0));
			// 9.30.2014 we disable eager fetch of molecules, reagents Collection, instead fetch separately via LazyLoader
			initializeDatasetMoleculeLazyLoader();
			initializeDatasetReagentLazyLoader();
		}
	}
	
	private void initializeDatasetMoleculeLazyLoader(){
		moleculeSearchResults = new LazyNursaDatasetMoleculeDataLoader(objectEntityManager, this.doi.getValue());
	}
	
	private void initializeDatasetReagentLazyLoader(){
		reagentResults = new LazyNursaDatasetReagentDataLoader(objectEntityManager, this.doi.getValue());
	}
	
	public LazyDataModel<DatasetMoleculeResult> getMoleculeSearchResults() {
		return moleculeSearchResults;
	}

	@Override
	public void setMoleculeSearchResults(LazyDataModel<DatasetMoleculeResult> searchResults) {
		this.moleculeSearchResults = searchResults;
	}
	
	

	@Override
	public LazyDataModel<DatasetReagentResult> getReagentResults() {
		return reagentResults;
	}

	@Override
	public void setReagentResults(
			LazyDataModel<DatasetReagentResult> reagentResults) {
		this.reagentResults=reagentResults;
		
	}

	@Override
	public NURSADataset getSelectedDataset() {
		return selectedDataset;
	}

	@Override
	public void setSelectedDataset(NURSADataset selectedDataset) {
		this.selectedDataset = selectedDataset;
	}

}
