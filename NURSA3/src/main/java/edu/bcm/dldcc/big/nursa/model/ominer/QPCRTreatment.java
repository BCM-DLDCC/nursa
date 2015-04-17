package edu.bcm.dldcc.big.nursa.model.ominer;

import javax.persistence.Entity;


@Entity
public class QPCRTreatment extends DatasetBaseAnnotation {

	private static final long serialVersionUID = 6600355820547041443L;

	private int zeitgeiberTime;

	public int getZeitgeiberTime() {
		return zeitgeiberTime;
	}

	public void setZeitgeiberTime(int zeitgeiberTime) {
		this.zeitgeiberTime = zeitgeiberTime;
	}

}
