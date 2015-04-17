package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.Gene;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.GeneAnnotation;

public class GeneAnnotationLazyLoader extends AnnotationLazyLoader<Gene, GeneAnnotation> {

	private static final long serialVersionUID = 6326569725260821062L;

	public GeneAnnotationLazyLoader(EntityManager objectEntityManager,
			GeneAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	public GeneAnnotationLazyLoader(EntityManager objectEntityManager,
			GeneAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public GeneAnnotationLazyLoader(EntityManager objectEntityManager,
			GeneAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public GeneAnnotationLazyLoader(EntityManager objectEntityManager,
			GeneAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}

	@Override
	public List<Gene> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "genes");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "genes");
		}
	}
}
