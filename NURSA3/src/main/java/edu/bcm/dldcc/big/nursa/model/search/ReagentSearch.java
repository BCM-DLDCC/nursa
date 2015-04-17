package edu.bcm.dldcc.big.nursa.model.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import edu.bcm.dldcc.big.nursa.model.common.Manufacturer;
import edu.bcm.dldcc.big.nursa.model.common.MoleculeAutoSuggest;
import edu.bcm.dldcc.big.nursa.model.common.Species;

@Entity
public class ReagentSearch extends SearchBase {

	private static final long serialVersionUID = -1078598930469727719L;

	@ElementCollection
	private List<String> reagentTypes = new ArrayList<String>();
	
	@ManyToMany
	private List<MoleculeAutoSuggest> molecules = new ArrayList<MoleculeAutoSuggest>();
	
	@Transient
	private MoleculeAutoSuggest addMolecule;
	@Transient
	private MoleculeAutoSuggest removeMolecule;
	
	@ManyToMany
	private List<Species> species = new ArrayList<Species>();
	
	@ManyToMany
	private List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
	
	private boolean NURSAReagent;

	public List<String> getReagentTypes() {
		return reagentTypes;
	}

	public void setReagentTypes(List<String> reagentTypes) {
		this.reagentTypes = reagentTypes;
	}

	public List<MoleculeAutoSuggest> getMolecules() {
		return molecules;
	}

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
	 * @param addMolecule the addMolecule to set
	 */
	public void setAddMolecule(MoleculeAutoSuggest addMolecule) {
		if(addMolecule != null) {
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
	 * @param removeMolecule the removeMolecule to set
	 */
	public void setRemoveMolecule(MoleculeAutoSuggest removeMolecule) {
		this.getMolecules().remove(removeMolecule);
		this.removeMolecule = null;
	}
	
	public List<Species> getSpecies() {
		return species;
	}

	public void setSpecies(List<Species> species) {
		this.species = species;
	}

	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public boolean isNURSAReagent() {
		return NURSAReagent;
	}

	public void setNURSAReagent(boolean nURSAReagent) {
		NURSAReagent = nURSAReagent;
	}
	
	
}
