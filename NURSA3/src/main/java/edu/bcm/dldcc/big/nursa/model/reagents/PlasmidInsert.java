package edu.bcm.dldcc.big.nursa.model.reagents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import edu.bcm.dldcc.big.nursa.model.core.Gene;

@Entity
public class PlasmidInsert implements Serializable {
	
	private static final long serialVersionUID = 5030378844530251759L;

	@Id
	@GeneratedValue(generator = "plasmidInsertSequencer")
	@SequenceGenerator(name = "plasmidInsertSequencer", sequenceName = "PLASMIDINSERT_SEQ")
	private Long id;
	
	private String mutation;
	private String name;
	private String shRNASequence;
	private Integer plasmidSize;
	
	@ElementCollection
	private Map<String, String> tags = new HashMap<String, String>();
	
	@ManyToMany
	private List<Gene> gene = new ArrayList<Gene>();
	
	@OneToMany
	private List<Cloning> cloning = new ArrayList<Cloning>();

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
	 * @return the mutation
	 */
	public String getMutation() {
		return mutation;
	}

	/**
	 * @param mutation the mutation to set
	 */
	public void setMutation(String mutation) {
		this.mutation = mutation;
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
	 * @return the shRNASequence
	 */
	public String getShRNASequence() {
		return shRNASequence;
	}

	/**
	 * @param shRNASequence the shRNASequence to set
	 */
	public void setShRNASequence(String shRNASequence) {
		this.shRNASequence = shRNASequence;
	}

	/**
	 * @return the size
	 */
	public Integer getPlasmidSize() {
		return plasmidSize;
	}

	/**
	 * @param size the size to set
	 */
	public void setPlasmidSize(Integer plasmidSize) {
		this.plasmidSize = plasmidSize;
	}

	/**
	 * @return the tags
	 */
	public Map<String, String> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

	/**
	 * @return the gene
	 */
	public List<Gene> getGene() {
		return gene;
	}

	/**
	 * @param gene the gene to set
	 */
	public void setGene(List<Gene> gene) {
		this.gene = gene;
	}

	/**
	 * @return the cloning
	 */
	public List<Cloning> getCloning() {
		return cloning;
	}

	/**
	 * @param cloning the cloning to set
	 */
	public void setCloning(List<Cloning> cloning) {
		this.cloning = cloning;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cloning == null) ? 0 : cloning.hashCode());
		result = prime * result + ((gene == null) ? 0 : gene.hashCode());
		result = prime * result
				+ ((mutation == null) ? 0 : mutation.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((shRNASequence == null) ? 0 : shRNASequence.hashCode());
		result = prime * result + ((plasmidSize == null) ? 0 : plasmidSize.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		PlasmidInsert other = (PlasmidInsert) obj;
		if (cloning == null) {
			if (other.cloning != null)
				return false;
		} else if (!cloning.equals(other.cloning))
			return false;
		if (gene == null) {
			if (other.gene != null)
				return false;
		} else if (!gene.equals(other.gene))
			return false;
		if (mutation == null) {
			if (other.mutation != null)
				return false;
		} else if (!mutation.equals(other.mutation))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (shRNASequence == null) {
			if (other.shRNASequence != null)
				return false;
		} else if (!shRNASequence.equals(other.shRNASequence))
			return false;
		if (plasmidSize == null) {
			if (other.plasmidSize != null)
				return false;
		} else if (!plasmidSize.equals(other.plasmidSize))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}
	
	
	
}
