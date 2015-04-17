package edu.bcm.dldcc.big.nursa.model.reagentAnnotations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.primefaces.model.LazyDataModel;

import edu.bcm.dldcc.big.nursa.model.common.BaseAnnotation;

@Entity
@Table(name="RBA")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class ReagentBaseAnnotation extends BaseAnnotation {

	private static final long serialVersionUID = -3975738817026565400L;

	@Id
	@GeneratedValue(generator = "reagentbaseAnnotationSequencer")
	@SequenceGenerator(name = "reagentbaseAnnotationSequencer", sequenceName = "REAGENTBA_SEQ")
	private Long id;

	private String annotationType;
	
	@Transient
	private Class<? extends LazyDataModel<?>> lazyDataModelLoaderClass;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnnotationType() {
		return annotationType;
	}

	public void setAnnotationType(String type) {
		this.annotationType = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annotationType == null) ? 0 : annotationType.hashCode());
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
		ReagentBaseAnnotation other = (ReagentBaseAnnotation) obj;
		if (annotationType == null) {
			if (other.annotationType != null)
				return false;
		} else if (!annotationType.equals(other.annotationType))
			return false;
		return true;
	}

	public Class<? extends LazyDataModel<?>> getLazyDataModelLoaderClass() {
		return lazyDataModelLoaderClass;
	}

	public void setLazyDataModelLoaderClass(Class<? extends LazyDataModel<?>> lazyDataModelLoaderClass) {
		this.lazyDataModelLoaderClass = lazyDataModelLoaderClass;
	}

	
}
