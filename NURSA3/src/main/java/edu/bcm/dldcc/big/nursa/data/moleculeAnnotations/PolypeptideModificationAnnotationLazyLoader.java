package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.PolypeptideModification;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.PolypeptideModificationAnnotation;


public class PolypeptideModificationAnnotationLazyLoader extends
AnnotationLazyLoader<PolypeptideModification, PolypeptideModificationAnnotation> {

	private static final long serialVersionUID = 6326569725260821062L;

	public PolypeptideModificationAnnotationLazyLoader(EntityManager objectEntityManager,
			PolypeptideModificationAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public PolypeptideModificationAnnotationLazyLoader(EntityManager objectEntityManager,
			PolypeptideModificationAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}

	@Override
	public List<PolypeptideModification> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if(this.getDistinctColumn() == null) {
		return createQuery(first, pageSize, multiSortMeta, filters, "polypeptideModifications");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "polypetideModifications");
		}
	}
}
