package edu.bcm.dldcc.big.nursa.model.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import edu.bcm.dldcc.big.nursa.model.common.MoleculeAutoSuggest;
import edu.bcm.dldcc.big.nursa.model.common.TranslationalAutoSuggest;

/**
 * This is the Translational Search Class. It provides a class for performing
 * searches on translational entries. It extends the SearchBase which allows it
 * to have a name and to be saved in a future implementation.
 * 
 * 
 * @author jeremyeaston-marks
 * 
 */
@Entity
public class TranslationalSearch extends SearchBase {

	private static final long serialVersionUID = -2332383325956642836L;

	@ElementCollection
	@CollectionTable(name = "TS_TranslationalTypes")
	private List<String> translationalTypes = new ArrayList<String>();
	
	@ManyToMany
	@JoinTable(name="TS_MOLECULE")
	private List<MoleculeAutoSuggest> molecules = new ArrayList<MoleculeAutoSuggest>();

	@Transient
	private MoleculeAutoSuggest addMolecule;

	@Transient
	private MoleculeAutoSuggest removeMolecule;

	@ManyToMany
	@JoinTable(name="TS_DISEASE")
	private List<TranslationalAutoSuggest> diseases = new ArrayList<TranslationalAutoSuggest>();

	@Transient
	private TranslationalAutoSuggest addDisease;

	@Transient
	private TranslationalAutoSuggest removeDisease;

	@ManyToMany
	@JoinTable(name="TS_DRUG")
	private List<TranslationalAutoSuggest> drugs = new ArrayList<TranslationalAutoSuggest>();

	@Transient
	private TranslationalAutoSuggest addDrug;

	@Transient
	private TranslationalAutoSuggest removeDrug;

	/**
	 * Returns the list of translational types that are to be searched on.
	 * 
	 * @return The list of translational types
	 */
	public List<String> getTranslationalTypes() {
		return translationalTypes;
	}

	/**
	 * Sets the list of translational types that are to be searched on
	 * 
	 * 
	 * @param translationalTypes
	 *            The list of translational types
	 */
	public void setTranslationalTypes(List<String> translationalTypes) {
		this.translationalTypes = translationalTypes;
	}

	/**
	 * Returns the list of molecules that are to be searched on
	 * 
	 * @return The list of molecules
	 */
	public List<MoleculeAutoSuggest> getMolecules() {
		return molecules;
	}

	/**
	 * Sets the list of molecules that are to be searched on
	 * 
	 * @param molecules
	 *            A list of molecules
	 */
	public void setMolecules(List<MoleculeAutoSuggest> molecules) {
		this.molecules = molecules;
	}

	/**
	 * @return the addMolecule
	 */
	public MoleculeAutoSuggest getAddMolecule() {
		return addMolecule;
	}

	/**
	 * @param addMolecule
	 *            the addMolecule to set
	 */
	public void setAddMolecule(MoleculeAutoSuggest addMolecule) {
		if (addMolecule != null) {
			this.getMolecules().add(addMolecule);
		}
		this.addMolecule = null;
	}

	/**
	 * @return the removeMolecule
	 */
	public MoleculeAutoSuggest getRemoveMolecule() {
		return removeMolecule;
	}

	/**
	 * @param removeMolecule
	 *            the removeMolecule to set
	 */
	public void setRemoveMolecule(MoleculeAutoSuggest removeMolecule) {
		this.getMolecules().remove(removeMolecule);
		this.removeMolecule = null;
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

	/**
	 * Returns the list of drugs that are to be searched on
	 * 
	 * @return The list of drugs
	 */
	public List<TranslationalAutoSuggest> getDrugs() {
		return drugs;
	}

	/**
	 * Sets the list of drugs that are to be searched on
	 * 
	 * @param drugs
	 *            The list of drugs
	 */
	public void setDrugs(List<TranslationalAutoSuggest> drugs) {
		this.drugs = drugs;
	}

	/**
	 * @return the addDrug
	 */
	public TranslationalAutoSuggest getAddDrug() {
		return addDrug;
	}

	/**
	 * @param addDrug
	 *            the addDrug to set
	 */
	public void setAddDrug(TranslationalAutoSuggest addDrug) {
		if(addDrug != null) {
			this.getDrugs().add(addDrug);
		}
		this.addDrug = null;
	}

	/**
	 * @return the removeDrug
	 */
	public TranslationalAutoSuggest getRemoveDrug() {
		return removeDrug;
	}

	/**
	 * @param removeDrug
	 *            the removeDrug to set
	 */
	public void setRemoveDrug(TranslationalAutoSuggest removeDrug) {
		this.getDrugs().remove(removeDrug);
		this.removeDrug = null;
	}

}
