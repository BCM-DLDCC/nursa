package edu.bcm.dldcc.big.nursa.model;

import javax.persistence.Entity;

@Entity
public class Other extends Molecule {

	private static final long serialVersionUID = -3247917721317216960L;

	public Other() {
		setType("Other");
	}
}
