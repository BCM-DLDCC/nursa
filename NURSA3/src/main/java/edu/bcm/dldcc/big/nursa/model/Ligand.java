package edu.bcm.dldcc.big.nursa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import edu.bcm.dldcc.big.nursa.model.common.DataResource;
import edu.bcm.dldcc.big.nursa.model.common.MoleculeSynonym;
import edu.bcm.dldcc.big.nursa.model.translational.Agent;
import edu.bcm.dldcc.big.nursa.util.comparator.DataResourceAlphabetical;

@Entity
public class Ligand extends Other {

	private static final long serialVersionUID = 4882359692542047436L;
	
	@OneToOne(mappedBy="ligand", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Agent compound;
	
	@OneToOne(cascade = CascadeType.ALL)
	private MoleculeSynonym casNumber;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Sort(type = SortType.COMPARATOR, comparator = DataResourceAlphabetical.class)
	private SortedSet<DataResource> dataResources = new TreeSet<DataResource>();
	
	private String sourceType;
	
	private String jmolFile;
	
	@Transient
	private List<DataResource> datasources;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Protein> binds = new ArrayList<Protein>();
	
	public Ligand() {
		setType("Ligand");
	}

	public Agent getCompound() {
		return compound;
	}

	public void setCompound(Agent compound) {
		this.compound = compound;
	}

	/**
	 * @return the casNumber
	 */
	public MoleculeSynonym getCasNumber() {
		return casNumber;
	}

	/**
	 * @param casNumber the casNumber to set
	 */
	public void setCasNumber(MoleculeSynonym casNumber) {
		this.casNumber = casNumber;
	}

	/**
	 * @return the dataResources
	 */
	public SortedSet<DataResource> getDataResources() {
		return dataResources;
	}

	/**
	 * @param dataResources the dataResources to set
	 */
	public void setDataResources(SortedSet<DataResource> dataResources) {
		this.dataResources = dataResources;
	}

	/**
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return the jmolFile
	 */
	public String getJmolFile() {
		return jmolFile;
	}

	/**
	 * @param jmolFile the jmolFile to set
	 */
	public void setJmolFile(String jmolFile) {
		this.jmolFile = jmolFile;
	}

	/**
	 * @return the binds
	 */
	public List<Protein> getBinds() {
		return binds;
	}

	/**
	 * @param binds the binds to set
	 */
	public void setBinds(List<Protein> binds) {
		this.binds = binds;
	}

	public List<DataResource> getDatasources() {
		if(this.datasources== null || this.datasources.size() == 0){
			this.datasources= new ArrayList<DataResource>(this.dataResources);
		}
		return datasources;
	}
	
	
}
