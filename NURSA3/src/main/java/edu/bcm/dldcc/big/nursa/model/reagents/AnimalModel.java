package edu.bcm.dldcc.big.nursa.model.reagents;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class AnimalModel extends Reagent {

	private static final long serialVersionUID = -8813874387858834781L;
	public AnimalModel() {
		setType("Animal Model");
	}
	
	private String technique;
	private String background;
	
	@ManyToOne
	private AlleleType alleleType;
	
	public String getTechnique() {
		return technique;
	}
	public void setTechnique(String technique) {
		this.technique = technique;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	/**
	 * @return the alleleType
	 */
	public AlleleType getAlleleType() {
		return alleleType;
	}
	/**
	 * @param alleleType the alleleType to set
	 */
	public void setAlleleType(AlleleType alleleType) {
		this.alleleType = alleleType;
	}
	
	
}
