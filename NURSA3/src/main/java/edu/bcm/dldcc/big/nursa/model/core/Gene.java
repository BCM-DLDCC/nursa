package edu.bcm.dldcc.big.nursa.model.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import edu.bcm.dldcc.big.nursa.model.common.DOI;
import edu.bcm.dldcc.big.nursa.model.common.DataResource;
import edu.bcm.dldcc.big.nursa.model.common.MoleculeSynonym;
import edu.bcm.dldcc.big.nursa.model.common.Species;
import edu.bcm.dldcc.big.nursa.util.comparator.DataResourceAlphabetical;

@Entity
public class Gene implements Serializable {

	private static final long serialVersionUID = -5803872719328545056L;

	@Id
	@GeneratedValue(generator = "geneSequencer")
	@SequenceGenerator(name = "geneSequencer", sequenceName = "GENE_SEQ")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private MoleculeSynonym official;

	@OneToMany(cascade = CascadeType.ALL)
	private List<MoleculeSynonym> synonyms = new ArrayList<MoleculeSynonym>();

	@OneToMany(cascade = CascadeType.ALL)
	@Sort(type = SortType.COMPARATOR, comparator = DataResourceAlphabetical.class)
	private SortedSet<DataResource> dataResources = new TreeSet<DataResource>();

	@ManyToOne(cascade = CascadeType.ALL)
	private Species species;

	private String chromosome;

	private String strand;

	private String mapLocation;

	@Basic
	private String entrezGeneId;

	@Column(length = 3500)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "encodingGene")
	private List<MRNA> encodes = new ArrayList<MRNA>();

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "genes")
	private List<GOTerm> goTerms = new ArrayList<GOTerm>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	private DOI doi;


	public Gene() {
	}

	public Long getId() {
		return id;
	}

	public MoleculeSynonym getOfficial() {
		return official;
	}

	public void setOfficial(MoleculeSynonym official) {
		this.official = official;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MoleculeSynonym> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(List<MoleculeSynonym> synonyms) {
		this.synonyms = synonyms;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public String getChromosome() {
		return chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public String getStrand() {
		return strand;
	}

	public void setStrand(String strand) {
		this.strand = strand;
	}

	public String getMapLocation() {
		return mapLocation;
	}

	public void setMapLocation(String mapLocation) {
		this.mapLocation = mapLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MRNA> getEncodes() {
		return encodes;
	}

	public void setEncodes(List<MRNA> encodes) {
		this.encodes = encodes;
	}

	public SortedSet<DataResource> getDataResources() {
		return dataResources;
	}

	public void setDataResources(SortedSet<DataResource> dataResources) {
		this.dataResources = dataResources;
	}

	@Transient
	public List<DataResource> getDataResourcesAsList() {
		return new ArrayList<DataResource>(this.dataResources);
	}

	public String getEntrezGeneId() {
		return entrezGeneId;
	}

	public void setEntrezGeneId(String entrezGeneId) {
		this.entrezGeneId = entrezGeneId;
	}

	public List<GOTerm> getGoTerms() {
		return goTerms;
	}

	public void setGoTerms(List<GOTerm> goTerms) {
		this.goTerms = goTerms;
	}

	/**
	 * @return the doi
	 */
	public DOI getDoi() {
		return doi;
	}

	/**
	 * @param doi the doi to set
	 */
	public void setDoi(DOI doi) {
		this.doi = doi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chromosome == null) ? 0 : chromosome.hashCode());
		result = prime * result
				+ ((dataResources == null) ? 0 : dataResources.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((encodes == null) ? 0 : encodes.hashCode());
		result = prime * result
				+ ((entrezGeneId == null) ? 0 : entrezGeneId.hashCode());
		result = prime * result + ((goTerms == null) ? 0 : goTerms.hashCode());
		result = prime * result
				+ ((mapLocation == null) ? 0 : mapLocation.hashCode());
		result = prime * result
				+ ((official == null) ? 0 : official.hashCode());
		result = prime * result + ((species == null) ? 0 : species.hashCode());
		result = prime * result + ((strand == null) ? 0 : strand.hashCode());
		result = prime * result
				+ ((synonyms == null) ? 0 : synonyms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gene other = (Gene) obj;
		if (chromosome == null) {
			if (other.chromosome != null)
				return false;
		} else if (!chromosome.equals(other.chromosome))
			return false;
		if (dataResources == null) {
			if (other.dataResources != null)
				return false;
		} else if (!dataResources.equals(other.dataResources))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (encodes == null) {
			if (other.encodes != null)
				return false;
		} else if (!encodes.equals(other.encodes))
			return false;
		if (entrezGeneId == null) {
			if (other.entrezGeneId != null)
				return false;
		} else if (!entrezGeneId.equals(other.entrezGeneId))
			return false;
		if (goTerms == null) {
			if (other.goTerms != null)
				return false;
		} else if (!goTerms.equals(other.goTerms))
			return false;
		if (mapLocation == null) {
			if (other.mapLocation != null)
				return false;
		} else if (!mapLocation.equals(other.mapLocation))
			return false;
		if (official == null) {
			if (other.official != null)
				return false;
		} else if (!official.equals(other.official))
			return false;
		if (species == null) {
			if (other.species != null)
				return false;
		} else if (!species.equals(other.species))
			return false;
		if (strand == null) {
			if (other.strand != null)
				return false;
		} else if (!strand.equals(other.strand))
			return false;
		if (synonyms == null) {
			if (other.synonyms != null)
				return false;
		} else if (!synonyms.equals(other.synonyms))
			return false;
		return true;
	}

}
