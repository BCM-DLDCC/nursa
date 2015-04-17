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
public class DatasetBaseAnnotation implements Serializable {

	private static final long serialVersionUID = -5445435578500529073L;
	@Id
	@GeneratedValue(generator = "datasetAnnotationSequencer")
	@SequenceGenerator(name = "datasetAnnotationSequencer", sequenceName = "DATASETANNOT_SEQ")
	private Long id;
	
	@ManyToOne
	private Dataset dataset;
	
}
