package edu.bcm.dldcc.big.nursa.data.translationalAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.translational.Disease;
import edu.bcm.dldcc.big.nursa.model.translationalAnnotations.AssociatedDiseaseAnnotation;

public class AssociatedDiseaseAnnotationLazyLoader extends
		AnnotationLazyLoader<Disease, AssociatedDiseaseAnnotation> {

	private static final long serialVersionUID = 2573435685725110411L;

	public AssociatedDiseaseAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedDiseaseAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	public AssociatedDiseaseAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedDiseaseAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public AssociatedDiseaseAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedDiseaseAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public AssociatedDiseaseAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedDiseaseAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}

	@Override
	public List<Disease> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "diseases");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "diseases");
		}
	}
}
