package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.MiRNA;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.MIRNAAnnotation;


public class MiRNAAnnotationLazyLoader extends
AnnotationLazyLoader<MiRNA, MIRNAAnnotation> {

	private static final long serialVersionUID = 6325260826569721062L;

	public MiRNAAnnotationLazyLoader(EntityManager objectEntityManager,
			MIRNAAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public MiRNAAnnotationLazyLoader(EntityManager objectEntityManager,
			MIRNAAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public MiRNAAnnotationLazyLoader(EntityManager objectEntityManager,
			MIRNAAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public MiRNAAnnotationLazyLoader(EntityManager objectEntityManager,
			MIRNAAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}

	@Override
	public List<MiRNA> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "mirna");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "mirna");
		}
	}
}
