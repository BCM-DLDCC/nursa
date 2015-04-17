package edu.bcm.dldcc.big.nursa.model.translational;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import edu.bcm.dldcc.big.nursa.model.Ligand;
import edu.bcm.dldcc.big.nursa.model.common.TranslationalSynonym;

@Entity
public class Agent extends Translational {

	private static final long serialVersionUID = 4825988944895957577L;

	@OneToMany(cascade = CascadeType.ALL)
	private List<TranslationalSynonym> synonyms = new ArrayList<TranslationalSynonym>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Ligand ligand;

	public Agent() {
		setType("Agent");
	}

	// SETTERS AND GETTERS

	public List<TranslationalSynonym> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(List<TranslationalSynonym> synonyms) {
		this.synonyms = synonyms;
	}

	public Ligand getLigand() {
		return ligand;
	}

	public void setLigand(Ligand ligand) {
		this.ligand = ligand;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ligand == null) ? 0 : ligand.hashCode());
		result = prime * result
				+ ((synonyms == null) ? 0 : synonyms.hashCode());
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
		Agent other = (Agent) obj;
		if (ligand == null) {
			if (other.ligand != null)
				return false;
		} else if (!ligand.equals(other.ligand))
			return false;
		if (synonyms == null) {
			if (other.synonyms != null)
				return false;
		} else if (!synonyms.equals(other.synonyms))
			return false;
		return true;
	}
	
	
}
