package edu.bcm.dldcc.big.nursa.model.translational;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import edu.bcm.dldcc.big.nursa.model.Molecule;
import edu.bcm.dldcc.big.nursa.model.common.DOI;
import edu.bcm.dldcc.big.nursa.model.common.DataResource;
import edu.bcm.dldcc.big.nursa.model.common.Reference;
import edu.bcm.dldcc.big.nursa.model.common.TranslationalSynonym;
import edu.bcm.dldcc.big.nursa.model.translationalAnnotations.TranslationalBaseAnnotation;
import edu.bcm.dldcc.big.nursa.util.comparator.DataResourceAlphabetical;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Translational implements Serializable {

	private static final long serialVersionUID = 2778226752009691874L;

	@Id
	@GeneratedValue(generator = "translationalSequencer")
	@SequenceGenerator(name = "translationalSequencer", sequenceName = "TRANSLATIONAL_SEQ")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private TranslationalSynonym name;

	@Basic
	private String type;
	
	private String source;
	
	@Lob
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DOI doi;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Reference> references = new ArrayList<Reference>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Translational_Annotations")
	private Map<Class<? extends TranslationalBaseAnnotation>, TranslationalBaseAnnotation> annotations = new HashMap<Class<? extends TranslationalBaseAnnotation>, TranslationalBaseAnnotation>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@Sort(type = SortType.COMPARATOR, comparator = DataResourceAlphabetical.class)
	private SortedSet<DataResource> dataResources = new TreeSet<DataResource>();
	
	private Boolean visible;
	
	
	public Translational() {
		setType("Translational");
	}

	@ManyToMany(cascade = { CascadeType.ALL })
	private List<Molecule> molecules;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TranslationalSynonym getName() {
		return name;
	}

	public void setName(TranslationalSynonym name) {
		this.name = name;
	}

	public List<Molecule> getMolecules() {
		return molecules;
	}

	public void setMolecules(List<Molecule> molecules) {
		this.molecules = molecules;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<Reference> getReferences() {
		return references;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DOI getDoi() {
		return doi;
	}

	public void setDoi(DOI doi) {
		this.doi = doi;
	}

	public Map<Class<? extends TranslationalBaseAnnotation>, TranslationalBaseAnnotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Map<Class<? extends TranslationalBaseAnnotation>, TranslationalBaseAnnotation> annotations) {
		this.annotations = annotations;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends TranslationalBaseAnnotation> T getAnnotation(
			Class<? extends TranslationalBaseAnnotation> type) {
		return (T) this.getAnnotations().get(type);
	}

	public <T extends TranslationalBaseAnnotation> void addAnnotation(
			Class<? extends TranslationalBaseAnnotation> class1,
			TranslationalBaseAnnotation baseCompoundAnnotation) {
		this.getAnnotations().put(class1, baseCompoundAnnotation);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends TranslationalBaseAnnotation> TranslationalBaseAnnotation getAnnotationFromString(
			String type) {
		try {

			// Swap the TCCL

			Thread.currentThread().setContextClassLoader(
					getClass().getClassLoader());

			// Invoke the service
			Class<? extends TranslationalBaseAnnotation> clazz = (Class<? extends TranslationalBaseAnnotation>) Class
					.forName("edu.bcm.dldcc.big.nursa.model.translationalAnnotations."
							+ type);
			return (T) this.getAnnotation(clazz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;
	}

	public SortedSet<DataResource> getDataResources() {
		return dataResources;
	}

	public void setDataResources(SortedSet<DataResource> dataResources) {
		this.dataResources = dataResources;
	}

	/**
	 * @return the visible
	 */
	public Boolean getVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}


}
