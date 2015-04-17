package edu.bcm.dldcc.big.nursa.model.ominer;

import javax.persistence.Entity;

import edu.bcm.dldcc.big.nursa.model.core.Gene;

@Entity
public class QPCRExpression extends GeneExpression {

	private static final long serialVersionUID = 7409949061139957021L;

	private double relativeExpressionLevel;
	private double relativeExpressionLevelError;
	private Gene gene;

	public double getRelativeExpressionLevel() {
		return relativeExpressionLevel;
	}

	public void setRelativeExpressionLevel(double relativeExpressionLevel) {
		this.relativeExpressionLevel = relativeExpressionLevel;
	}

	public double getRelativeExpressionLevelError() {
		return relativeExpressionLevelError;
	}

	public void setRelativeExpressionLevelError(
			double relativeExpressionLevelError) {
		this.relativeExpressionLevelError = relativeExpressionLevelError;
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}
}
