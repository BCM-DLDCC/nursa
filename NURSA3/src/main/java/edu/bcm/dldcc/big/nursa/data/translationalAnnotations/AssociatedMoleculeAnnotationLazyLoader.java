package edu.bcm.dldcc.big.nursa.data.translationalAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.search.MoleculeResult;
import edu.bcm.dldcc.big.nursa.model.translationalAnnotations.AssociatedMoleculesAnnotation;

public class AssociatedMoleculeAnnotationLazyLoader extends
		AnnotationLazyLoader<MoleculeResult, AssociatedMoleculesAnnotation> {
	private static final long serialVersionUID = -435555714562578517L;

	public AssociatedMoleculeAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedMoleculesAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	public AssociatedMoleculeAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedMoleculesAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public AssociatedMoleculeAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedMoleculesAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public AssociatedMoleculeAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedMoleculesAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}

	@Override
	public List<MoleculeResult> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "molecules");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "molecules");
		}
	}
}
