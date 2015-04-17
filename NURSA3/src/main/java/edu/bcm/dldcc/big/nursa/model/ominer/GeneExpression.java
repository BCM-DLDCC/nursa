package edu.bcm.dldcc.big.nursa.model.ominer;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


import edu.bcm.dldcc.big.nursa.model.core.Gene;

@Entity
public class GeneExpression extends DataSetExpression {

	private static final long serialVersionUID = -275868472852011934L;
	
	@ManyToOne
	private Gene gene;

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}	
}
