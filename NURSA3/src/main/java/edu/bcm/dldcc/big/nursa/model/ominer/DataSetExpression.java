package edu.bcm.dldcc.big.nursa.model.ominer;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class DataSetExpression implements Serializable {
	
	private static final long serialVersionUID = 5606597214423172371L;

	@Id
	@GeneratedValue(generator = "datasetExpSequencer")
	@SequenceGenerator(name = "datasetExpSequencer", sequenceName = "DATASETEXP_SEQ")
	private Long id;
	
	@ManyToOne
	private Dataset dataset;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}
}
