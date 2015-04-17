package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class SpeciesAnnotation implements Serializable {

	private static final long serialVersionUID = -5699990357303955959L;

	@Id
	@GeneratedValue(generator = "speciesAnnotionSequencer")
	@SequenceGenerator(name = "speciesAnnotionSequencer", sequenceName = "SPECIESANNOT_SEQ")
	private Long id;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Spec_Annotations")
	private Map<Class<? extends MoleculeBaseAnnotation>, MoleculeBaseAnnotation> annotations = new HashMap<Class<? extends MoleculeBaseAnnotation>, MoleculeBaseAnnotation>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public <T extends MoleculeBaseAnnotation> void addAnnotation(
			Class<? extends MoleculeBaseAnnotation> class1,
			MoleculeBaseAnnotation baseCompoundAnnotation) {
		this.getAnnotations().put(class1, baseCompoundAnnotation);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((annotations == null) ? 0 : annotations.hashCode());
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
		SpeciesAnnotation other = (SpeciesAnnotation) obj;
		if (annotations == null) {
			if (other.annotations != null)
				return false;
		} else if (!annotations.equals(other.annotations))
			return false;
		return true;
	}

}
