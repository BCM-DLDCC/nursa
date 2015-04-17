package edu.bcm.dldcc.big.nursa.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Coreg")
public class CoRegulator extends Protein {

	private static final long serialVersionUID = -6293061374615459384L;

	public CoRegulator() {
		setType("Coregulator");
	}
}
