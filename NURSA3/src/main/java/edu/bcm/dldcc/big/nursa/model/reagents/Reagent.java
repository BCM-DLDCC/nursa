package edu.bcm.dldcc.big.nursa.model.reagents;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import edu.bcm.dldcc.big.nursa.model.Molecule;
import edu.bcm.dldcc.big.nursa.model.common.DOI;
import edu.bcm.dldcc.big.nursa.model.common.Manufacturer;
import edu.bcm.dldcc.big.nursa.model.common.ReagentSynonym;
import edu.bcm.dldcc.big.nursa.model.common.Reference;
import edu.bcm.dldcc.big.nursa.model.common.Species;
import edu.bcm.dldcc.big.nursa.model.reagentAnnotations.ReagentBaseAnnotation;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Reagent implements Serializable {
	
	private static final long serialVersionUID = 8616143603572033129L;
	
	@Id
	@GeneratedValue(generator = "reagentSequencer")
	@SequenceGenerator(name = "reagentSequencer", sequenceName = "REAGENT_SEQ")
	private Long id;
	
	@Basic
	private String type;
	
	@OneToOne(cascade=CascadeType.ALL)
	private ReagentSynonym name;
	
	@ManyToOne
	private Species species;
	
	@OneToOne
	@JoinColumn(name="MOL")
	private Molecule mol;
	
	@Column(length = 3500)
	private String description;
	
	private String reagentInformationFile;
	private String reagentInformationId;
	
	private Boolean nursaReagent;
	
	@OneToOne(cascade=CascadeType.ALL)
	private DOI doi;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Manufacturer manufacturer;
	
	@OneToOne(cascade=CascadeType.ALL)
	private ReagentSynonym catalogNumber;
	
	private String catalogLink;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Reference reference;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Reagent_Annotations")
	private Map<Class<? extends ReagentBaseAnnotation>, ReagentBaseAnnotation> annotations = new HashMap<Class<? extends ReagentBaseAnnotation>, ReagentBaseAnnotation>();
	
	private String source;
	
	//@since 3.1.patch1 amcowiti this should probably be in Synonyms/Datasources/annotations
		private String contact;
		
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public ReagentSynonym getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(ReagentSynonym name) {
		this.name = name;
	}

	/**
	 * @return the species
	 */
	public Species getSpecies() {
		return species;
	}

	/**
	 * @param species the species to set
	 */
	public void setSpecies(Species species) {
		this.species = species;
	}

	/**
	 * @return the mol
	 */
	public Molecule getMol() {
		return mol;
	}

	/**
	 * @param mol the mol to set
	 */
	public void setMol(Molecule mol) {
		this.mol = mol;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the reagentInformationFile
	 */
	public String getReagentInformationFile() {
		return reagentInformationFile;
	}

	/**
	 * @param reagentInformationFile the reagentInformationFile to set
	 */
	public void setReagentInformationFile(String reagentInformationFile) {
		this.reagentInformationFile = reagentInformationFile;
	}

	/**
	 * @return the reagentInformationId
	 */
	public String getReagentInformationId() {
		return reagentInformationId;
	}

	/**
	 * @param reagentInformationId the reagentInformationId to set
	 */
	public void setReagentInformationId(String reagentInformationId) {
		this.reagentInformationId = reagentInformationId;
	}

	/**
	 * @return the nursaReagent
	 */
	public Boolean isNursaReagent() {
		return nursaReagent;
	}

	/**
	 * @param nursaReagent the nursaReagent to set
	 */
	public void setNursaReagent(Boolean nursaReagent) {
		this.nursaReagent = nursaReagent;
	}

	/**
	 * @return the doi
	 */
	public DOI getDoi() {
		return doi;
	}

	/**
	 * @param doi the doi to set
	 */
	public void setDoi(DOI doi) {
		this.doi = doi;
	}

	/**
	 * @return the manufacturer
	 */
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the catalogNumber
	 */
	public ReagentSynonym getCatalogNumber() {
		return catalogNumber;
	}

	/**
	 * @param catalogNumber the catalogNumber to set
	 */
	public void setCatalogNumber(ReagentSynonym catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	/**
	 * @return the reference
	 */
	public Reference getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(Reference reference) {
		this.reference = reference;
	}

	/**
	 * @return the annotations
	 */
	public Map<Class<? extends ReagentBaseAnnotation>, ReagentBaseAnnotation> getAnnotations() {
		return annotations;
	}

	/**
	 * @param annotations the annotations to set
	 */
	public void setAnnotations(
			Map<Class<? extends ReagentBaseAnnotation>, ReagentBaseAnnotation> annotations) {
		this.annotations = annotations;
	}

	@SuppressWarnings("unchecked")
	public <T extends ReagentBaseAnnotation> T getAnnotation(
			Class<? extends ReagentBaseAnnotation> type) {
		return (T) this.getAnnotations().get(type);
	}

	public <T extends ReagentBaseAnnotation> void addAnnotation(
			Class<? extends ReagentBaseAnnotation> class1,
			ReagentBaseAnnotation baseCompoundAnnotation) {
		this.getAnnotations().put(class1, baseCompoundAnnotation);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ReagentBaseAnnotation> ReagentBaseAnnotation getAnnotationFromString(
			String type) {
		try {

			// Swap the TCCL

			Thread.currentThread().setContextClassLoader(
					getClass().getClassLoader());

			// Invoke the service
			Class<? extends ReagentBaseAnnotation> clazz = (Class<? extends ReagentBaseAnnotation>) Class
					.forName("edu.bcm.dldcc.big.nursa.model.reagentAnnotations."
							+ type);
			return (T) this.getAnnotation(clazz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((catalogNumber == null) ? 0 : catalogNumber.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((doi == null) ? 0 : doi.hashCode());
		result = prime * result
				+ ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((mol == null) ? 0 : mol.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (nursaReagent ? 1231 : 1237);
		result = prime
				* result
				+ ((reagentInformationFile == null) ? 0
						: reagentInformationFile.hashCode());
		result = prime
				* result
				+ ((reagentInformationId == null) ? 0 : reagentInformationId
						.hashCode());
		result = prime * result
				+ ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((species == null) ? 0 : species.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Reagent other = (Reagent) obj;
		if (catalogNumber == null) {
			if (other.catalogNumber != null)
				return false;
		} else if (!catalogNumber.equals(other.catalogNumber))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (doi == null) {
			if (other.doi != null)
				return false;
		} else if (!doi.equals(other.doi))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (mol == null) {
			if (other.mol != null)
				return false;
		} else if (!mol.equals(other.mol))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nursaReagent != other.nursaReagent)
			return false;
		if (reagentInformationFile == null) {
			if (other.reagentInformationFile != null)
				return false;
		} else if (!reagentInformationFile.equals(other.reagentInformationFile))
			return false;
		if (reagentInformationId == null) {
			if (other.reagentInformationId != null)
				return false;
		} else if (!reagentInformationId.equals(other.reagentInformationId))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (species == null) {
			if (other.species != null)
				return false;
		} else if (!species.equals(other.species))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public String getCatalogLink() {
		return catalogLink;
	}

	public void setCatalogLink(String catalogLink) {
		this.catalogLink = catalogLink;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
