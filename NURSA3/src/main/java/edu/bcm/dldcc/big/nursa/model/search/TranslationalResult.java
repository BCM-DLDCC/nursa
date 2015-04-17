package edu.bcm.dldcc.big.nursa.model.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import edu.bcm.dldcc.big.nursa.model.common.MoleculeAutoSuggest;
import edu.bcm.dldcc.big.nursa.model.common.TranslationalAutoSuggest;

@Entity
public class TranslationalResult implements Serializable {
	
	private static final long serialVersionUID = -5736586285150801898L;

	@Id
	@GeneratedValue(generator = "translationalResultSequencer")
	@SequenceGenerator(name = "translationalResultSequencer", sequenceName = "TRANRES_SEQ")
	private Long id;
	
	@Basic
	private String type;
	private String name;
	@Basic
	private String doi;
	private String source;
	
	
	@OneToMany
	@JoinTable(name="TRANRESULT_MOLECULE")
	private List<MoleculeAutoSuggest> molecules = new ArrayList<MoleculeAutoSuggest>();
	
	@OneToMany
	@JoinTable(name="TRANRESULT_DISEASE")
	private List<TranslationalAutoSuggest> diseases = new ArrayList<TranslationalAutoSuggest>();
	
	@OneToMany
	@JoinTable(name="TRANRESULT_DRUG")
	private List<TranslationalAutoSuggest> drugs = new ArrayList<TranslationalAutoSuggest>();

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the doi
	 */
	public String getDoi() {
		return doi;
	}

	/**
	 * @param doi the doi to set
	 */
	public void setDoi(String doi) {
		this.doi = doi;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the molecules
	 */
	public List<MoleculeAutoSuggest> getMolecules() {
		return molecules;
	}

	/**
	 * @param molecules the molecules to set
	 */
	public void setMolecules(List<MoleculeAutoSuggest> molecules) {
		this.molecules = molecules;
	}

	/**
	 * @return the diseases
	 */
	public List<TranslationalAutoSuggest> getDiseases() {
		return diseases;
	}

	/**
	 * @param diseases the diseases to set
	 */
	public void setDiseases(List<TranslationalAutoSuggest> diseases) {
		this.diseases = diseases;
	}

	/**
	 * @return the drugs
	 */
	public List<TranslationalAutoSuggest> getDrugs() {
		return drugs;
	}

	/**
	 * @param drugs the drugs to set
	 */
	public void setDrugs(List<TranslationalAutoSuggest> drugs) {
		this.drugs = drugs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((diseases == null) ? 0 : diseases.hashCode());
		result = prime * result + ((doi == null) ? 0 : doi.hashCode());
		result = prime * result + ((drugs == null) ? 0 : drugs.hashCode());
		result = prime * result
				+ ((molecules == null) ? 0 : molecules.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TranslationalResult other = (TranslationalResult) obj;
		if (diseases == null) {
			if (other.diseases != null)
				return false;
		} else if (!diseases.equals(other.diseases))
			return false;
		if (doi == null) {
			if (other.doi != null)
				return false;
		} else if (!doi.equals(other.doi))
			return false;
		if (drugs == null) {
			if (other.drugs != null)
				return false;
		} else if (!drugs.equals(other.drugs))
			return false;
		if (molecules == null) {
			if (other.molecules != null)
				return false;
		} else if (!molecules.equals(other.molecules))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
	
}
