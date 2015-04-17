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

import edu.bcm.dldcc.big.nursa.model.common.TranslationalAutoSuggest;
import edu.bcm.dldcc.big.nursa.model.core.GOTerm;

@Entity
public class MoleculeResult implements Serializable {
	private static final long serialVersionUID = 6546435439950677400L;
	
	@Id
	@GeneratedValue(generator = "moleculeResultSequencer")
	@SequenceGenerator(name = "moleculeResultSequencer", sequenceName = "MOLRES_SEQ")
	private Long id;
	
	@Basic
	private String type;
	private String symbol;
	private String doi;
	private String name;
	private String description;
	
	@OneToMany
	@JoinTable(name="MR_GOTERM")
	private List<GOTerm> goterms = new ArrayList<GOTerm>();

	@OneToMany
	@JoinTable(name="MR_DISEASE")
	private List<TranslationalAutoSuggest> diseases = new ArrayList<TranslationalAutoSuggest>();
	
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
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the goterms
	 */
	public List<GOTerm> getGoterms() {
		return goterms;
	}

	/**
	 * @param goterms the goterms to set
	 */
	public void setGoterms(List<GOTerm> goterms) {
		this.goterms = goterms;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((diseases == null) ? 0 : diseases.hashCode());
		result = prime * result + ((doi == null) ? 0 : doi.hashCode());
		result = prime * result + ((goterms == null) ? 0 : goterms.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		MoleculeResult other = (MoleculeResult) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
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
		if (goterms == null) {
			if (other.goterms != null)
				return false;
		} else if (!goterms.equals(other.goterms))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	

}
