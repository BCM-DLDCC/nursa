package edu.bcm.dldcc.big.nursa.model.common;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.bcm.dldcc.big.nursa.model.Molecule;

@Entity
@Table(name = "molsynon")
public class MoleculeSynonym extends Synonym {

	private static final long serialVersionUID = -3187374386146060683L;
	@ManyToOne(cascade = CascadeType.ALL)
	private Molecule molecule;


	public Molecule getMolecule() {
		return molecule;
	}

	public void setMolecule(Molecule molecule) {
		this.molecule = molecule;
	}


}
