package edu.bcm.dldcc.big.nursa.model.translational;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import edu.bcm.dldcc.big.nursa.model.common.TranslationalSynonym;
import edu.bcm.dldcc.big.nursa.model.core.Gene;

@Entity
public class Disease extends Translational {

	private static final long serialVersionUID = 169158368712101074L;

	@OneToOne(cascade = CascadeType.ALL)
	private TranslationalSynonym omimID;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<TranslationalSynonym> synonyms = new ArrayList<TranslationalSynonym>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Map<Gene, GeneAssociationType> geneMap = new HashMap<Gene, GeneAssociationType>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Gene> associatedGenes = new ArrayList<Gene>();
	
	@ManyToMany(mappedBy="condition", cascade = CascadeType.ALL)
	private List<ClinicalTrial> clinicalTrials = new ArrayList<ClinicalTrial>();

	public Disease() {
		setType("Disease");
	}

	/**
	 * @return the omimID
	 */
	public TranslationalSynonym getOmimID() {
		return omimID;
	}

	/**
	 * @param omimID
	 *            the omimID to set
	 */
	public void setOmimID(TranslationalSynonym omimID) {
		this.omimID = omimID;
	}



	/**
	 * @return the synonyms
	 */
	public List<TranslationalSynonym> getSynonyms() {
		return synonyms;
	}



	/**
	 * @param synonyms the synonyms to set
	 */
	public void setSynonyms(List<TranslationalSynonym> synonyms) {
		this.synonyms = synonyms;
	}

	/**
	 * @return the geneMap
	 */
	public Map<Gene, GeneAssociationType> getGeneMap() {
		return geneMap;
	}

	/**
	 * @param geneMap the geneMap to set
	 */
	public void setGeneMap(Map<Gene, GeneAssociationType> geneMap) {
		this.geneMap = geneMap;
	}
	
	
	public List<Gene> getGeneMapKeysAsList() {
		List<Gene> returns = new LinkedList<Gene>();
		returns.addAll(this.geneMap.keySet());
		return returns;
	}

	/**
	 * @return the associatedGenes
	 */
	public List<Gene> getAssociatedGenes() {
		return associatedGenes;
	}

	/**
	 * @param associatedGenes the associatedGenes to set
	 */
	public void setAssociatedGenes(List<Gene> associatedGenes) {
		this.associatedGenes = associatedGenes;
	}

	/**
	 * @return the clinicalTrials
	 */
	public List<ClinicalTrial> getClinicalTrials() {
		return clinicalTrials;
	}

	/**
	 * @param clinicalTrials the clinicalTrials to set
	 */
	public void setClinicalTrials(List<ClinicalTrial> clinicalTrials) {
		this.clinicalTrials = clinicalTrials;
	}

}
