package edu.bcm.dldcc.big.nursa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import edu.bcm.dldcc.big.nursa.model.common.DOI;
import edu.bcm.dldcc.big.nursa.model.common.Reference;
import edu.bcm.dldcc.big.nursa.model.common.Species;
import edu.bcm.dldcc.big.nursa.model.common.MoleculeSynonym;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.MoleculeBaseAnnotation;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.SpeciesAnnotation;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Molecule implements Serializable {

	private static final long serialVersionUID = 8436035036161312729L;

	@Id
	@GeneratedValue(generator = "moleculeSequencer")
	@SequenceGenerator(name = "moleculeSequencer", sequenceName = "MOLECULE_SEQ")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private MoleculeSynonym official;

	@OneToOne(cascade = CascadeType.ALL)
	private MoleculeSynonym nursaSymbol;

	@OneToOne(cascade = CascadeType.ALL)
	private MoleculeSynonym name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "molecule")
	@OrderBy("name")
	@Where(clause = "display=1")
	private Set<MoleculeSynonym> synonyms = new HashSet<MoleculeSynonym>();

	@Column(length = 3500)
	private String blurb;

	@OneToOne(cascade = CascadeType.ALL)
	private MoleculeSynonym nrnc;

	private String moleculePictureFile;
	private String moleculePictureId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Mol_Species")
	private Map<Species, SpeciesAnnotation> speciesAnnotations = new HashMap<Species, SpeciesAnnotation>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Mol_Annotations")
	private Map<Class<? extends MoleculeBaseAnnotation>, MoleculeBaseAnnotation> annotations = new HashMap<Class<? extends MoleculeBaseAnnotation>, MoleculeBaseAnnotation>();

	@Basic
	private String type;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Mol_Literature")
	private List<Reference> definitiveLiterature = new ArrayList<Reference>();

	@ManyToOne(cascade = CascadeType.ALL)
	private DOI doi;

	public Molecule() {
		setOfficial(new MoleculeSynonym());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MoleculeSynonym getOfficial() {
		return official;
	}

	public void setOfficial(MoleculeSynonym official) {
		this.official = official;
	}

	public MoleculeSynonym getNursaSymbol() {
		return nursaSymbol;
	}

	public void setNursaSymbol(MoleculeSynonym nursaSymbol) {
		this.nursaSymbol = nursaSymbol;
	}

	public MoleculeSynonym getName() {
		return name;
	}

	public void setName(MoleculeSynonym name) {
		this.name = name;
	}

	public Set<MoleculeSynonym> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Set<MoleculeSynonym> synonyms) {
		this.synonyms = synonyms;
	}
	
	@Transient
	public List<MoleculeSynonym> getSynonymsAsList() {
		return new ArrayList<MoleculeSynonym>(this.synonyms);
	}

	public void addSynonym(MoleculeSynonym symbol) {
		getSynonyms().add(symbol);
	}

	public void removeSynonym(MoleculeSynonym symbol) {
		getSynonyms().remove(symbol);
	}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	public MoleculeSynonym getNrnc() {
		return nrnc;
	}

	public void setNrnc(MoleculeSynonym nrnc) {
		this.nrnc = nrnc;
	}

	public String getMoleculePictureFile() {
		return moleculePictureFile;
	}

	public void setMoleculePictureFile(String moleculePictureFile) {
		this.moleculePictureFile = moleculePictureFile;
	}

	public String getMoleculePictureId() {
		return moleculePictureId;
	}

	public void setMoleculePictureId(String moleculePictureId) {
		this.moleculePictureId = moleculePictureId;
	}

	public Map<Species, SpeciesAnnotation> getSpeciesAnnotations() {
		return speciesAnnotations;
	}

	public void setSpeciesAnnotations(
			Map<Species, SpeciesAnnotation> annotations) {
		this.speciesAnnotations = annotations;
	}

	public SpeciesAnnotation getSpeciesAnnotation(Species type) {
		return this.getSpeciesAnnotations().get(type);
	}

	public void addSpeciesAnnotation(Species species,
			SpeciesAnnotation annotation) {
		this.getSpeciesAnnotations().put(species, annotation);
	}

	public void addSpeciesAnnotations(Species species) {
		this.getSpeciesAnnotations().put(species, new SpeciesAnnotation());
	}
	
	public List<Species> retrieveSpecies() {
		return new ArrayList<Species>(getSpeciesAnnotations().keySet());
	}

	public Map<Class<? extends MoleculeBaseAnnotation>, MoleculeBaseAnnotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(
			Map<Class<? extends MoleculeBaseAnnotation>, MoleculeBaseAnnotation> annotations) {
		this.annotations = annotations;
	}

	@SuppressWarnings("unchecked")
	public <T extends MoleculeBaseAnnotation> T getAnnotation(
			Class<? extends MoleculeBaseAnnotation> type) {
		return (T) this.getAnnotations().get(type);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends MoleculeBaseAnnotation> MoleculeBaseAnnotation getAnnotationFromString(
			String type) {
		try {

			// Swap the TCCL

			Thread.currentThread().setContextClassLoader(
					getClass().getClassLoader());

			// Invoke the service
			Class<? extends MoleculeBaseAnnotation> clazz = (Class<? extends MoleculeBaseAnnotation>) Class
					.forName("edu.bcm.dldcc.big.nursa.model.moleculeAnnotations."
							+ type);
			return (T) this.getAnnotation(clazz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;
	}

	public <T extends MoleculeBaseAnnotation> void addAnnotation(
			Class<? extends MoleculeBaseAnnotation> class1,
			MoleculeBaseAnnotation baseCompoundAnnotation) {
		this.getAnnotations().put(class1, baseCompoundAnnotation);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DOI getDoi() {
		return doi;
	}

	public void setDoi(DOI doi) {
		this.doi = doi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((annotations == null) ? 0 : annotations.hashCode());
		result = prime * result + ((blurb == null) ? 0 : blurb.hashCode());
		result = prime
				* result
				+ ((getDefinitiveLiterature() == null) ? 0 : getDefinitiveLiterature()
						.hashCode());
		result = prime * result + ((doi == null) ? 0 : doi.hashCode());
		result = prime
				* result
				+ ((moleculePictureFile == null) ? 0 : moleculePictureFile
						.hashCode());
		result = prime
				* result
				+ ((moleculePictureId == null) ? 0 : moleculePictureId
						.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nrnc == null) ? 0 : nrnc.hashCode());
		result = prime * result
				+ ((nursaSymbol == null) ? 0 : nursaSymbol.hashCode());
		result = prime * result
				+ ((official == null) ? 0 : official.hashCode());
		result = prime
				* result
				+ ((speciesAnnotations == null) ? 0 : speciesAnnotations
						.hashCode());
		result = prime * result
				+ ((synonyms == null) ? 0 : synonyms.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Molecule other = (Molecule) obj;
		if (annotations == null) {
			if (other.annotations != null)
				return false;
		} else if (!annotations.equals(other.annotations))
			return false;
		if (blurb == null) {
			if (other.blurb != null)
				return false;
		} else if (!blurb.equals(other.blurb))
			return false;
		if (getDefinitiveLiterature() == null) {
			if (other.getDefinitiveLiterature() != null)
				return false;
		} else if (!getDefinitiveLiterature().equals(other.getDefinitiveLiterature()))
			return false;
		if (doi == null) {
			if (other.doi != null)
				return false;
		} else if (!doi.equals(other.doi))
			return false;
		if (moleculePictureFile == null) {
			if (other.moleculePictureFile != null)
				return false;
		} else if (!moleculePictureFile.equals(other.moleculePictureFile))
			return false;
		if (moleculePictureId == null) {
			if (other.moleculePictureId != null)
				return false;
		} else if (!moleculePictureId.equals(other.moleculePictureId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nrnc == null) {
			if (other.nrnc != null)
				return false;
		} else if (!nrnc.equals(other.nrnc))
			return false;
		if (nursaSymbol == null) {
			if (other.nursaSymbol != null)
				return false;
		} else if (!nursaSymbol.equals(other.nursaSymbol))
			return false;
		if (official == null) {
			if (other.official != null)
				return false;
		} else if (!official.equals(other.official))
			return false;
		if (speciesAnnotations == null) {
			if (other.speciesAnnotations != null)
				return false;
		} else if (!speciesAnnotations.equals(other.speciesAnnotations))
			return false;
		if (synonyms == null) {
			if (other.synonyms != null)
				return false;
		} else if (!synonyms.equals(other.synonyms))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public List<Reference> getDefinitiveLiterature() {
		return definitiveLiterature;
	}

	public void setDefinitiveLiterature(List<Reference> definitiveLiterature) {
		this.definitiveLiterature = definitiveLiterature;
	}

}
