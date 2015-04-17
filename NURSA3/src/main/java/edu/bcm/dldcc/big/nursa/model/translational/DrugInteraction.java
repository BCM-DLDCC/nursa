package edu.bcm.dldcc.big.nursa.model.translational;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class DrugInteraction implements Serializable {
	private static final long serialVersionUID = 442313681321838184L;

	@Id
	@GeneratedValue(generator = "drugInterSequencer")
	@SequenceGenerator(name = "drugInterSequencer", sequenceName = "DRUGINTER_SEQ")
	private Long id;
	
	@ManyToOne
	private Drug drug;
	
	@Column(length=1000)
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Drug getDrug() {
		return drug;
	}
	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
