package edu.bcm.dldcc.big.nursa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import edu.bcm.dldcc.big.nursa.model.core.Homologene;


@Entity
public class Protein extends Molecule {

	private static final long serialVersionUID = -7879588221689278208L;

	@OneToOne(cascade = CascadeType.ALL)
	private Homologene homologene;
	
	public Protein() {
		setType("Protein");
	}

	public Homologene getHomologene() {
		return homologene;
	}

	public void setHomologene(Homologene homologene) {
		this.homologene = homologene;
	}
	
}
