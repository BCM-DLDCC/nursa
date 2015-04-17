package edu.bcm.dldcc.big.nursa.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="NR")
public class NuclearReceptor extends Protein {

	private static final long serialVersionUID = 4882359692542047436L;
	
	public NuclearReceptor() {
		setType("Nuclear Receptor");
	}
}
