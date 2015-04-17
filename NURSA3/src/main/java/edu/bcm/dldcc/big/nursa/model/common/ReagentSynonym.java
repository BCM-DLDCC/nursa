package edu.bcm.dldcc.big.nursa.model.common;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.bcm.dldcc.big.nursa.model.reagents.Reagent;

@Entity
@Table(name = "reagentsynon")
public class ReagentSynonym extends Synonym {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8431260316135036729L;
	@ManyToOne(cascade = CascadeType.ALL)
	private Reagent reagent;
	public Reagent getReagent() {
		return reagent;
	}
	public void setReagent(Reagent reagent) {
		this.reagent = reagent;
	}

}
