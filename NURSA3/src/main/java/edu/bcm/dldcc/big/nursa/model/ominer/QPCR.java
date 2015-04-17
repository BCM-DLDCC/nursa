package edu.bcm.dldcc.big.nursa.model.ominer;

import javax.persistence.Entity;

@Entity
public class QPCR extends Technique {
	private static final long serialVersionUID = 4844100252421182192L;

	private String primerSequence;
	private String primerTemplate;
	
	public String getPrimerSequence() {
		return primerSequence;
	}
	public void setPrimerSequence(String primerSequence) {
		this.primerSequence = primerSequence;
	}
	public String getPrimerTemplate() {
		return primerTemplate;
	}
	public void setPrimerTemplate(String primerTemplate) {
		this.primerTemplate = primerTemplate;
	}

	
}
