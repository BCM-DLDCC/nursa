package edu.bcm.dldcc.big.nursa.model.reagents;

import javax.persistence.Entity;

@Entity
public class CellLine extends Reagent {

	private static final long serialVersionUID = -3206358889023662516L;
	
	private String technique;
	private String protocolLink; 
	
	public CellLine() {
		setType("Cell Line");
	}
	public String getTechnique() {
		return technique;
	}
	public void setTechnique(String technique) {
		this.technique = technique;
	}
	public String getProtocolLink() {
		return protocolLink;
	}
	public void setProtocolLink(String protocolLink) {
		this.protocolLink = protocolLink;
	}
	
}
