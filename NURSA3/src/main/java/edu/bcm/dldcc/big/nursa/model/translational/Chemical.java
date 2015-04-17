package edu.bcm.dldcc.big.nursa.model.translational;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import edu.bcm.dldcc.big.nursa.model.common.TranslationalSynonym;

@Entity
public class Chemical extends Agent {

	private static final long serialVersionUID = -5480608618164938624L;
	
	@OneToOne(cascade = CascadeType.ALL)
	private TranslationalSynonym casNumber;
	
	public Chemical() {
		setType("Chemical");
	}

	/**
	 * @return the casNumber
	 */
	public TranslationalSynonym getCasNumber() {
		return casNumber;
	}

	/**
	 * @param casNumber the casNumber to set
	 */
	public void setCasNumber(TranslationalSynonym casNumber) {
		this.casNumber = casNumber;
	}
}
