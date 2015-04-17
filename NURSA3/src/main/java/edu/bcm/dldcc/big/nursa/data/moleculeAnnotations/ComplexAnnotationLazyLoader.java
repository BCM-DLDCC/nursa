package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.Complex;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.ComplexAnnotation;

public class ComplexAnnotationLazyLoader extends
AnnotationLazyLoader<Complex, ComplexAnnotation> {

	private static final long serialVersionUID = -2801611978585605986L;

	public ComplexAnnotationLazyLoader(EntityManager objectEntityManager,
			ComplexAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public ComplexAnnotationLazyLoader(EntityManager objectEntityManager,
			ComplexAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	
	public ComplexAnnotationLazyLoader(EntityManager objectEntityManager,
			ComplexAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	
	public ComplexAnnotationLazyLoader(EntityManager objectEntityManager,
			ComplexAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}
	
	
	@Override
	public List<Complex> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "complexes");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "complexes");
		}
	}

}
