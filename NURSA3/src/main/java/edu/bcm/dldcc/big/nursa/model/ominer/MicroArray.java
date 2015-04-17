package edu.bcm.dldcc.big.nursa.model.ominer;

import javax.persistence.Entity;

@Entity
public class MicroArray extends Technique {
	private static final long serialVersionUID = 5244844100221182192L;

	private String type;
	private String identifier;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}
