package edu.bcm.dldcc.big.nursa.model.dataset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import edu.bcm.dldcc.big.nursa.model.Molecule;
import edu.bcm.dldcc.big.nursa.model.common.DOI;
import edu.bcm.dldcc.big.nursa.model.common.Reference;
import edu.bcm.dldcc.big.nursa.model.common.Species;
import edu.bcm.dldcc.big.nursa.model.reagents.Reagent;

@Entity
public class NURSADataset  implements Serializable {


	private static final long serialVersionUID = 4347561349038197693L;

	@Id
	@GeneratedValue(generator = "nursadatasetSequencer")
	@SequenceGenerator(name = "nursadatasetSequencer", sequenceName = "NURSADATASET_SEQ")
	private Long id;
	
	@Basic
	private String type;
	@Basic
	private String name;
	
	@ManyToOne
	private Species species;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Molecule> molecules = new ArrayList<Molecule>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Reagent> reagents = new ArrayList<Reagent>();
	
	@Column(length = 3500)
	private String description;
	
	@ElementCollection
	private List<String> cellLine = new ArrayList<String>();
	
	private String dataSource;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Reference reference;
	
	@Transient
	private List<Reference> references= new ArrayList<Reference>();
	
	@ManyToOne(cascade=CascadeType.ALL)
	private DOI doi;
	
	private String fileName;
	private String fileID;
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
	 * @return the species
	 */
	public Species getSpecies() {
		return species;
	}
	/**
	 * @param species the species to set
	 */
	public void setSpecies(Species species) {
		this.species = species;
	}
	/**
	 * @return the molecules
	 */
	public List<Molecule> getMolecules() {
		return molecules;
	}
	/**
	 * @param molecules the molecules to set
	 */
	public void setMolecules(List<Molecule> molecules) {
		this.molecules = molecules;
	}
	/**
	 * @return the reagents
	 */
	public List<Reagent> getReagents() {
		return reagents;
	}
	/**
	 * @param reagents the reagents to set
	 */
	public void setReagents(List<Reagent> reagents) {
		this.reagents = reagents;
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
	 * @return the cellLine
	 */
	public List<String> getCellLine() {
		return cellLine;
	}
	/**
	 * @param cellLine the cellLine to set
	 */
	public void setCellLine(List<String> cellLine) {
		this.cellLine = cellLine;
	}
	/**
	 * @return the dataSource
	 */
	public String getDataSource() {
		return dataSource;
	}
	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	/**
	 * @return the reference
	 */
	public Reference getReference() {
		return reference;
	}
	/**
	 * @param reference the reference to set
	 */
	public void setReference(Reference reference) {
		this.reference = reference;
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
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileID
	 */
	public String getFileID() {
		return fileID;
	}
	/**
	 * @param fileID the fileID to set
	 */
	public void setFileID(String fileID) {
		this.fileID = fileID;
	}
	public List<Reference> getReferences() {
		
		if(this.reference != null){
			this.references.clear();
			this.references.add(this.reference);
		}
		return references;
	}
	
	
}
