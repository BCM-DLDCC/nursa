package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.DiseaseAssociationAnnotation;
import edu.bcm.dldcc.big.nursa.model.translational.Disease;


public class DiseaseAssociationAnnotationLazyLoader extends
AnnotationLazyLoader<Disease, DiseaseAssociationAnnotation> {

	private static final long serialVersionUID = -7058765082410019881L;

	public DiseaseAssociationAnnotationLazyLoader(EntityManager objectEntityManager,
			DiseaseAssociationAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public DiseaseAssociationAnnotationLazyLoader(EntityManager objectEntityManager,
			DiseaseAssociationAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public DiseaseAssociationAnnotationLazyLoader(EntityManager objectEntityManager,
			DiseaseAssociationAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public DiseaseAssociationAnnotationLazyLoader(EntityManager objectEntityManager,
			DiseaseAssociationAnnotation annotation, Map<String, String> filters, String distinctColumn) {
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
