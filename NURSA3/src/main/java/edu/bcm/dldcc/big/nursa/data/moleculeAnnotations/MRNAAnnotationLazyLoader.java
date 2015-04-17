package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.MRNA;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.MRNAAnnotation;

public class MRNAAnnotationLazyLoader extends
AnnotationLazyLoader<MRNA, MRNAAnnotation> {

	private static final long serialVersionUID = 6326569725260821062L;

	public MRNAAnnotationLazyLoader(EntityManager objectEntityManager,
			MRNAAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public MRNAAnnotationLazyLoader(EntityManager objectEntityManager,
			MRNAAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}

	public MRNAAnnotationLazyLoader(EntityManager objectEntityManager,
			MRNAAnnotation annotation, Map<String, String> filters) {
		 super(objectEntityManager, annotation, filters);
	}
	
	public MRNAAnnotationLazyLoader(EntityManager objectEntityManager,
			MRNAAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}


	@Override
	public List<MRNA> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if (this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "mrna");
		}
		return createDistinctColumnQuery(first, pageSize, multiSortMeta,
				filters, "mrna");
	}
}
