package edu.bcm.dldcc.big.nursa.model.translational;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import edu.bcm.dldcc.big.nursa.model.common.DataResource;
import edu.bcm.dldcc.big.nursa.model.common.TranslationalSynonym;

@Entity
public class Drug extends Agent {

	private static final long serialVersionUID = -7339495953554064988L;

	@OneToOne(cascade = CascadeType.ALL)
	private TranslationalSynonym drugBankId;

	@OneToOne(cascade = CascadeType.ALL)
	private TranslationalSynonym casNumber;

	@Column(length = 1700)
	private String indication;

	@Column(length = 3100)
	private String pharmacology;

	@Column(length = 3300)
	private String mechanismOfAction;

	@Column(length = 1000)
	private String absorption;

	@Column(length = 600)
	private String halfLife;

	@Column(length = 500)
	private String proteinBinding;

	@Column(length = 600)
	private String volumeOfDistribution;
	
	private String drugType;

	@OneToMany(cascade = CascadeType.ALL)
	private List<TranslationalSynonym> secondaryAssesionNumbers = new ArrayList<TranslationalSynonym>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<TranslationalSynonym> salts = new ArrayList<TranslationalSynonym>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<TranslationalSynonym> brands = new ArrayList<TranslationalSynonym>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<TranslationalSynonym> atcCodes = new ArrayList<TranslationalSynonym>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<TranslationalSynonym> ahfCodes = new ArrayList<TranslationalSynonym>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<DrugInteraction> drugInteractions = new ArrayList<DrugInteraction>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<ChemicalProperty> calculatedProperty = new ArrayList<ChemicalProperty>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<ChemicalProperty> experimentalProperty = new ArrayList<ChemicalProperty>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<DataResource> externalIdentifier = new ArrayList<DataResource>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Enzyme> enzymes = new ArrayList<Enzyme>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<Target> targets = new ArrayList<Target>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<Transporter> transporters = new ArrayList<Transporter>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<Carrier> carriers = new ArrayList<Carrier>();
	
	@ManyToMany(mappedBy="drugs", cascade = CascadeType.ALL)
	private List<ClinicalTrial> clinicalTrials = new ArrayList<ClinicalTrial>();

	public Drug() {
		setType("Drug");
	}

	// SETTERS AND GETTERS

	public TranslationalSynonym getDrugBankId() {
		return drugBankId;
	}

	public void setDrugBankId(TranslationalSynonym drugBankId) {
		this.drugBankId = drugBankId;
	}

	public TranslationalSynonym getCasNumber() {
		return casNumber;
	}

	public void setCasNumber(TranslationalSynonym casNumber) {
		this.casNumber = casNumber;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getPharmacology() {
		return pharmacology;
	}

	public void setPharmacology(String pharmacology) {
		this.pharmacology = pharmacology;
	}

	public String getMechanismOfAction() {
		return mechanismOfAction;
	}

	public void setMechanismOfAction(String mechanismOfAction) {
		this.mechanismOfAction = mechanismOfAction;
	}

	public String getAbsorption() {
		return absorption;
	}

	public void setAbsorption(String absorption) {
		this.absorption = absorption;
	}

	public String getHalfLife() {
		return halfLife;
	}

	public void setHalfLife(String halfLife) {
		this.halfLife = halfLife;
	}

	public String getProteinBinding() {
		return proteinBinding;
	}

	public void setProteinBinding(String proteinBinding) {
		this.proteinBinding = proteinBinding;
	}

	public String getVolumeOfDistribution() {
		return volumeOfDistribution;
	}

	public void setVolumeOfDistribution(String volumeOfDistribution) {
		this.volumeOfDistribution = volumeOfDistribution;
	}
	
	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public List<TranslationalSynonym> getSecondaryAssesionNumbers() {
		return secondaryAssesionNumbers;
	}

	public void setSecondaryAssesionNumbers(
			List<TranslationalSynonym> secondaryAssesionNumbers) {
		this.secondaryAssesionNumbers = secondaryAssesionNumbers;
	}

	/**
	 * @return the enzymes
	 */
	public List<Enzyme> getEnzymes() {
		return enzymes;
	}

	/**
	 * @param enzymes the enzymes to set
	 */
	public void setEnzymes(List<Enzyme> enzymes) {
		this.enzymes = enzymes;
	}

	public List<TranslationalSynonym> getSalts() {
		return salts;
	}

	public void setSalts(List<TranslationalSynonym> salts) {
		this.salts = salts;
	}

	public List<TranslationalSynonym> getBrands() {
		return brands;
	}

	public void setBrands(List<TranslationalSynonym> brands) {
		this.brands = brands;
	}

	public List<TranslationalSynonym> getAtcCodes() {
		return atcCodes;
	}

	public void setAtcCodes(List<TranslationalSynonym> atcCodes) {
		this.atcCodes = atcCodes;
	}

	public List<TranslationalSynonym> getAhfCodes() {
		return ahfCodes;
	}

	public void setAhfCodes(List<TranslationalSynonym> ahfCodes) {
		this.ahfCodes = ahfCodes;
	}

	public List<DrugInteraction> getDrugInteractions() {
		return drugInteractions;
	}

	public void setDrugInteractions(List<DrugInteraction> drugInteractions) {
		this.drugInteractions = drugInteractions;
	}

	public List<ChemicalProperty> getCalculatedProperty() {
		return calculatedProperty;
	}

	public void setCalculatedProperty(List<ChemicalProperty> calculatedProperty) {
		this.calculatedProperty = calculatedProperty;
	}

	public List<ChemicalProperty> getExperimentalProperty() {
		return experimentalProperty;
	}

	public void setExperimentalProperty(
			List<ChemicalProperty> experimentalProperty) {
		this.experimentalProperty = experimentalProperty;
	}

	public List<DataResource> getExternalIdentifier() {
		return externalIdentifier;
	}

	public void setExternalIdentifier(List<DataResource> externalIdentifier) {
		this.externalIdentifier = externalIdentifier;
	}

	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}

	public List<Transporter> getTransporters() {
		return transporters;
	}

	public void setTransporters(List<Transporter> transporters) {
		this.transporters = transporters;
	}

	public List<Carrier> getCarriers() {
		return carriers;
	}

	public void setCarriers(List<Carrier> carriers) {
		this.carriers = carriers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((absorption == null) ? 0 : absorption.hashCode());
		result = prime * result
				+ ((ahfCodes == null) ? 0 : ahfCodes.hashCode());
		result = prime * result
				+ ((atcCodes == null) ? 0 : atcCodes.hashCode());
		result = prime * result + ((brands == null) ? 0 : brands.hashCode());
		result = prime
				* result
				+ ((calculatedProperty == null) ? 0 : calculatedProperty
						.hashCode());
		result = prime * result
				+ ((carriers == null) ? 0 : carriers.hashCode());
		result = prime * result
				+ ((casNumber == null) ? 0 : casNumber.hashCode());
		result = prime * result
				+ ((drugBankId == null) ? 0 : drugBankId.hashCode());
		result = prime
				* result
				+ ((drugInteractions == null) ? 0 : drugInteractions.hashCode());
		result = prime
				* result
				+ ((experimentalProperty == null) ? 0 : experimentalProperty
						.hashCode());
		result = prime
				* result
				+ ((externalIdentifier == null) ? 0 : externalIdentifier
						.hashCode());
		result = prime * result
				+ ((halfLife == null) ? 0 : halfLife.hashCode());
		result = prime * result
				+ ((indication == null) ? 0 : indication.hashCode());
		result = prime
				* result
				+ ((mechanismOfAction == null) ? 0 : mechanismOfAction
						.hashCode());
		result = prime * result
				+ ((pharmacology == null) ? 0 : pharmacology.hashCode());
		result = prime * result
				+ ((proteinBinding == null) ? 0 : proteinBinding.hashCode());
		result = prime * result + ((salts == null) ? 0 : salts.hashCode());
		result = prime
				* result
				+ ((secondaryAssesionNumbers == null) ? 0
						: secondaryAssesionNumbers.hashCode());
		result = prime * result + ((targets == null) ? 0 : targets.hashCode());
		result = prime * result
				+ ((transporters == null) ? 0 : transporters.hashCode());
		result = prime
				* result
				+ ((volumeOfDistribution == null) ? 0 : volumeOfDistribution
						.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Drug other = (Drug) obj;
		if (absorption == null) {
			if (other.absorption != null)
				return false;
		} else if (!absorption.equals(other.absorption))
			return false;
		if (ahfCodes == null) {
			if (other.ahfCodes != null)
				return false;
		} else if (!ahfCodes.equals(other.ahfCodes))
			return false;
		if (atcCodes == null) {
			if (other.atcCodes != null)
				return false;
		} else if (!atcCodes.equals(other.atcCodes))
			return false;
		if (brands == null) {
			if (other.brands != null)
				return false;
		} else if (!brands.equals(other.brands))
			return false;
		if (calculatedProperty == null) {
			if (other.calculatedProperty != null)
				return false;
		} else if (!calculatedProperty.equals(other.calculatedProperty))
			return false;
		if (carriers == null) {
			if (other.carriers != null)
				return false;
		} else if (!carriers.equals(other.carriers))
			return false;
		if (casNumber == null) {
			if (other.casNumber != null)
				return false;
		} else if (!casNumber.equals(other.casNumber))
			return false;
		if (drugBankId == null) {
			if (other.drugBankId != null)
				return false;
		} else if (!drugBankId.equals(other.drugBankId))
			return false;
		if (drugInteractions == null) {
			if (other.drugInteractions != null)
				return false;
		} else if (!drugInteractions.equals(other.drugInteractions))
			return false;
		if (experimentalProperty == null) {
			if (other.experimentalProperty != null)
				return false;
		} else if (!experimentalProperty.equals(other.experimentalProperty))
			return false;
		if (externalIdentifier == null) {
			if (other.externalIdentifier != null)
				return false;
		} else if (!externalIdentifier.equals(other.externalIdentifier))
			return false;
		if (halfLife == null) {
			if (other.halfLife != null)
				return false;
		} else if (!halfLife.equals(other.halfLife))
			return false;
		if (indication == null) {
			if (other.indication != null)
				return false;
		} else if (!indication.equals(other.indication))
			return false;
		if (mechanismOfAction == null) {
			if (other.mechanismOfAction != null)
				return false;
		} else if (!mechanismOfAction.equals(other.mechanismOfAction))
			return false;
		if (pharmacology == null) {
			if (other.pharmacology != null)
				return false;
		} else if (!pharmacology.equals(other.pharmacology))
			return false;
		if (proteinBinding == null) {
			if (other.proteinBinding != null)
				return false;
		} else if (!proteinBinding.equals(other.proteinBinding))
			return false;
		if (salts == null) {
			if (other.salts != null)
				return false;
		} else if (!salts.equals(other.salts))
			return false;
		if (secondaryAssesionNumbers == null) {
			if (other.secondaryAssesionNumbers != null)
				return false;
		} else if (!secondaryAssesionNumbers
				.equals(other.secondaryAssesionNumbers))
			return false;
		if (targets == null) {
			if (other.targets != null)
				return false;
		} else if (!targets.equals(other.targets))
			return false;
		if (transporters == null) {
			if (other.transporters != null)
				return false;
		} else if (!transporters.equals(other.transporters))
			return false;
		if (volumeOfDistribution == null) {
			if (other.volumeOfDistribution != null)
				return false;
		} else if (!volumeOfDistribution.equals(other.volumeOfDistribution))
			return false;
		return true;
	}

	
}
