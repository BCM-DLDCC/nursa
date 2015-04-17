package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.PolypeptideModification;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.PTMAnnotation;


public class PTMAnnotationLazyLoader extends
AnnotationLazyLoader<PolypeptideModification, PTMAnnotation> {

	private static final long serialVersionUID = 6326569725260821062L;

	public PTMAnnotationLazyLoader(EntityManager objectEntityManager,
			PTMAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public PTMAnnotationLazyLoader(EntityManager objectEntityManager,
			PTMAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public PTMAnnotationLazyLoader(EntityManager objectEntityManager,
			PTMAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public PTMAnnotationLazyLoader(EntityManager objectEntityManager,
			PTMAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}

	@Override
	public List<PolypeptideModification> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "polypetideModifications");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "polypetideModifications");
		}
	}
}
