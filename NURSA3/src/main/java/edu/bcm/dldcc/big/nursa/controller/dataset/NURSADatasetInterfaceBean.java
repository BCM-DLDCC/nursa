package edu.bcm.dldcc.big.nursa.controller.dataset;

import javax.ejb.Local;

import org.primefaces.model.LazyDataModel;

import edu.bcm.dldcc.big.nursa.model.dataset.NURSADataset;
import edu.bcm.dldcc.big.nursa.model.search.DatasetMoleculeResult;
import edu.bcm.dldcc.big.nursa.model.search.DatasetReagentResult;

@Local
public interface NURSADatasetInterfaceBean {

	void updateSelectedDataset();
	
	NURSADataset getSelectedDataset();
	
	void setSelectedDataset(NURSADataset selectedDataset);
	
	LazyDataModel<DatasetMoleculeResult> getMoleculeSearchResults();
	void setMoleculeSearchResults(LazyDataModel<DatasetMoleculeResult> searchResults);

	LazyDataModel<DatasetReagentResult> getReagentResults();
	void setReagentResults(LazyDataModel<DatasetReagentResult> searchResults);

	
}
