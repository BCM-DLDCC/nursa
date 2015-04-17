package edu.bcm.dldcc.big.nursa.model.reagents;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import edu.bcm.dldcc.big.nursa.model.common.Species;
import edu.bcm.dldcc.big.nursa.model.core.Polypeptide;

@Entity
public class Antibody extends Reagent {

	private static final long serialVersionUID = 608112608958712204L;

	public Antibody() {
		setType("Antibody");
	}
	
	@ManyToOne
	private Polypeptide antigen;
	
	private String abType;
	private String purification;
	
	@ManyToOne
	private Species hostSpecies;
	
	private String immunogenType;
	private String epitopeSequence;
	private String conjugate;
	
	private String applications;

	public Polypeptide getAntigen() {
		return antigen;
	}

	public void setAntigen(Polypeptide antigen) {
		this.antigen = antigen;
	}

	public String getAbType() {
		return abType;
	}

	public void setAbType(String abType) {
		this.abType = abType;
	}

	public String getPurification() {
		return purification;
	}

	public void setPurification(String purification) {
		this.purification = purification;
	}

	public Species getHostSpecies() {
		return hostSpecies;
	}

	public void setHostSpecies(Species hostSpecies) {
		this.hostSpecies = hostSpecies;
	}

	public String getImmunogenType() {
		return immunogenType;
	}

	public void setImmunogenType(String immunogenType) {
		this.immunogenType = immunogenType;
	}

	public String getEpitopeSequence() {
		return epitopeSequence;
	}

	public void setEpitopeSequence(String epitopeSequence) {
		this.epitopeSequence = epitopeSequence;
	}

	public String getConjugate() {
		return conjugate;
	}

	public void setConjugate(String conjugate) {
		this.conjugate = conjugate;
	}

	public String getApplications() {
		return applications;
	}

	public void setApplications(String applications) {
		this.applications = applications;
	}
}
