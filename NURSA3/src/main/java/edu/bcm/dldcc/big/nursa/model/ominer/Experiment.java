package edu.bcm.dldcc.big.nursa.model.ominer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import edu.bcm.dldcc.big.nursa.model.common.Reference;

@Entity
public class Experiment  implements Serializable {
	
	private static final long serialVersionUID = 4397885722636250389L;

	@Id
	@GeneratedValue(generator = "experimentSequencer")
	@SequenceGenerator(name = "experimentSequencer", sequenceName = "EXPERIMENT_SEQ")
	private Long id;
	
	private String name;
	private String description;
	private String doi;
	private Reference reference;
	
	@OneToMany(mappedBy = "experiment")
	private List<Dataset> datasets = new ArrayList<Dataset>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public List<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}
	
	

}
