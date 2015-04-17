package edu.bcm.dldcc.big.nursa.model.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import edu.bcm.dldcc.big.nursa.model.common.TranslationalAutoSuggest;
import edu.bcm.dldcc.big.nursa.model.core.GOTerm;

/**
 * This is the Molecule Search Class. It provides a class for performing
 * searches on molecules. It extends the SearchBase which allows it to have a
 * name. This implementation will allow for the ability to save searches in the
 * future.
 * 
 * @author jeremyeaston-marks
 * 
 */
@Entity
public class MoleculeSearch extends SearchBase {

	private static final long serialVersionUID = -1030467985989727719L;
	
	@ElementCollection
	private List<String> moleculeTypes = new ArrayList<String>();
	
	@ManyToMany
	private List<GOTerm> goTerms = new ArrayList<GOTerm>();
	
	@Transient
	private GOTerm addGOTerm;
	
	@Transient
	private GOTerm removeGOTerm;
	
	@ManyToMany
	@JoinTable(name="MS_DISEASE")
	private List<TranslationalAutoSuggest> diseases = new ArrayList<TranslationalAutoSuggest>();

	@Transient
	private TranslationalAutoSuggest addDisease;

	@Transient
	private TranslationalAutoSuggest removeDisease;
	

	/**
	 * Returns the list of molecule types that are to be searched on.
	 * 
	 * @return A list of molecule types
	 */
	public List<String> getMoleculeTypes() {
		return moleculeTypes;
	}

	/**
	 * Sets the list of molecule types that will be searched on.
	 * 
	 * @param moleculeTypes
	 *            A list of molecule types
	 */
	public void setMoleculeTypes(List<String> moleculeTypes) {
		this.moleculeTypes = moleculeTypes;
	}

	/**
	 * Returns the list of goTerms that are to be searched on.
	 * 
	 * @return A list of goTerms
	 */
	public List<GOTerm> getGoTerms() {
		return goTerms;
	}

	/**
	 * Sets the list of goTerms that will be searched on.
	 * 
	 * @param goTerms
	 *            A list of goTerms
	 */
	public void setGoTerms(List<GOTerm> goTerms) {
		this.goTerms = goTerms;
	}

	/**
	 * @return the addGOTerm
	 */
	public GOTerm getAddGOTerm() {
		return addGOTerm;
	}

	/**
	 * @param addGOTerm the addGOTerm to set
	 */
	public void setAddGOTerm(GOTerm addGOTerm) {
		if(addGOTerm != null) {
			this.getGoTerms().add(addGOTerm);
		}
		this.addGOTerm = null;
	}

	/**
	 * @return the removeGOTerm
	 */
	public GOTerm getRemoveGOTerm() {
		return removeGOTerm;
	}

	/**
	 * @param removeGOTerm the removeGOTerm to set
	 */
	public void setRemoveGOTerm(GOTerm removeGOTerm) {
		this.getGoTerms().remove(removeGOTerm);
		this.removeGOTerm = null;
	}

	/**
	 * Returns the list of diseases that are to be searched on
	 * 
	 * @return The list of diseases
	 */
	public List<TranslationalAutoSuggest> getDiseases() {
		return diseases;
	}

	/**
	 * Sets the list of diseases that are to be searched on
	 * 
	 * @param diseases
	 *            The list of diseases
	 */
	public void setDiseases(List<TranslationalAutoSuggest> diseases) {
		this.diseases = diseases;
	}

	/**
	 * @return the addDisease
	 */
	public TranslationalAutoSuggest getAddDisease() {
		return addDisease;
	}

	/**
	 * @param addDisease
	 *            the addDisease to set
	 */
	public void setAddDisease(TranslationalAutoSuggest addDisease) {
		if (addDisease != null) {
			this.getDiseases().add(addDisease);
		}
		this.addDisease = null;
	}

	/**
	 * @return the removeDisease
	 */
	public TranslationalAutoSuggest getRemoveDisease() {
		return removeDisease;
	}

	/**
	 * @param removeDisease
	 *            the removeDisease to set
	 */
	public void setRemoveDisease(TranslationalAutoSuggest removeDisease) {
		this.getDiseases().remove(removeDisease);
		this.removeDisease = null;
	}

}
