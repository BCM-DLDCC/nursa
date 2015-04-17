package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.GOTerm;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.GOTermAnnotation;


public class GoTermAnnotationLazyLoader extends
AnnotationLazyLoader<GOTerm, GOTermAnnotation> {

	private static final long serialVersionUID = 6326569725260821062L;

	public GoTermAnnotationLazyLoader(EntityManager objectEntityManager,
			GOTermAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public GoTermAnnotationLazyLoader(EntityManager objectEntityManager,
			GOTermAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public GoTermAnnotationLazyLoader(EntityManager objectEntityManager,
			GOTermAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public GoTermAnnotationLazyLoader(EntityManager objectEntityManager,
			GOTermAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}

	@Override
	public List<GOTerm> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "goTerms");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "goTerms");
		}
	}
}
