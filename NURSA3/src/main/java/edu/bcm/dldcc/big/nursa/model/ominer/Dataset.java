package edu.bcm.dldcc.big.nursa.model.ominer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import edu.bcm.dldcc.big.nursa.model.common.Species;
import edu.bcm.dldcc.big.nursa.model.core.GOTerm;
import edu.bcm.dldcc.big.nursa.model.translational.Disease;

@Entity
public class Dataset implements Serializable {

	private static final long serialVersionUID = 3810563876122260239L;

	@Id
	@GeneratedValue(generator = "datasetSequencer")
	@SequenceGenerator(name = "datasetSequencer", sequenceName = "DATASET_SEQ")
	private Long id;

	private String title;

	private String identification;

	@ManyToOne
	private Technique technique;

	private String doi;

	@Column(length = 3500)
	private String description;
	
	private String dataSource;

	@ManyToMany
	private List<Tissue> tissue = new ArrayList<Tissue>();

	@ManyToMany
	private List<Species> species = new ArrayList<Species>();

	@ManyToMany
	private List<Disease> disease = new ArrayList<Disease>();

	@ManyToMany
	private List<GOTerm> goTerm = new ArrayList<GOTerm>();
	
	@OneToMany(mappedBy="dataset")
	private List<DatasetBaseAnnotation> attributes = new ArrayList<DatasetBaseAnnotation>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dataset")
	private List<DataSetExpression> data = new ArrayList<DataSetExpression>();
	
	@ManyToOne
	private Experiment experiment;
	
	private boolean active = false;
	
	private boolean imported = false;
	
	private String dataFile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public Technique getTechnique() {
		return technique;
	}

	public void setTechnique(Technique technique) {
		this.technique = technique;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public List<Tissue> getTissue() {
		return tissue;
	}

	public void setTissue(List<Tissue> tissue) {
		this.tissue = tissue;
	}

	public List<Species> getSpecies() {
		return species;
	}

	public void setSpecies(List<Species> species) {
		this.species = species;
	}

	public List<Disease> getDisease() {
		return disease;
	}

	public void setDisease(List<Disease> disease) {
		this.disease = disease;
	}

	public List<GOTerm> getGoTerm() {
		return goTerm;
	}

	public void setGoTerm(List<GOTerm> goTerm) {
		this.goTerm = goTerm;
	}

	public List<DatasetBaseAnnotation> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<DatasetBaseAnnotation> attributes) {
		this.attributes = attributes;
	}

	public Experiment getExperiment() {
		return experiment;
	}

	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public String getDataFile() {
		return dataFile;
	}

	public void setDataFile(String dataFile) {
		this.dataFile = dataFile;
	}

	public List<DataSetExpression> getData() {
		return data;
	}

	public void setData(List<DataSetExpression> data) {
		this.data = data;
	}

	
}
