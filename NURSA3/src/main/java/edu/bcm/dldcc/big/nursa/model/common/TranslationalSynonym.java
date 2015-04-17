package edu.bcm.dldcc.big.nursa.model.common;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import edu.bcm.dldcc.big.nursa.model.translational.Translational;

@Entity
@Table(name = "transynon")
public class TranslationalSynonym extends Synonym {

	private static final long serialVersionUID = 7172451112215140968L;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Translational translational;
	
	private String synonymType;

	public Translational getTranslational() {
		return translational;
	}

	public void setTranslational(Translational translational) {
		this.translational = translational;
	}

	public String getSynonymType() {
		return synonymType;
	}

	public void setSynonymType(String synonymType) {
		this.synonymType = synonymType;
	}

}
