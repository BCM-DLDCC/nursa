package edu.bcm.dldcc.big.nursa.model.ominer;

import javax.persistence.Entity;

@Entity
public class MicroarrayExpression extends GeneExpression {

	private static final long serialVersionUID = -3208598957021544038L;

	private double foldChange;
	private String direction;
	private double significance;
	private String chipLocation;
	

	public double getFoldChange() {
		return foldChange;
	}

	public void setFoldChange(double foldChange) {
		this.foldChange = foldChange;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public double getSignificance() {
		return significance;
	}

	public void setSignificance(double significance) {
		this.significance = significance;
	}

	public String getChipLocation() {
		return chipLocation;
	}

	public void setChipLocation(String chipLocation) {
		this.chipLocation = chipLocation;
	}
}
