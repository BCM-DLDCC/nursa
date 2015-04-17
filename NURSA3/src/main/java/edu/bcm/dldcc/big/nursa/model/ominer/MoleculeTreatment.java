package edu.bcm.dldcc.big.nursa.model.ominer;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


import edu.bcm.dldcc.big.nursa.model.Molecule;

@Entity
public class MoleculeTreatment extends DatasetBaseAnnotation {

	private static final long serialVersionUID = 6600355041448205473L;

	@ManyToOne
	private Molecule molecule;

	private Double quantity;

	private String quantityUnit;

	private Integer time;

	private String timeUnit;

	public Molecule getMolecule() {
		return molecule;
	}

	public void setMolecule(Molecule molecule) {
		this.molecule = molecule;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

}
