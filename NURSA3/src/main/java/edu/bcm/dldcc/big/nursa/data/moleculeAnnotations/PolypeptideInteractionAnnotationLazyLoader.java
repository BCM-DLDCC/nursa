package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.PolypeptideInteraction;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.ProteinInteractionAnnotation;

public class PolypeptideInteractionAnnotationLazyLoader extends
AnnotationLazyLoader<PolypeptideInteraction, ProteinInteractionAnnotation> {

	private static final long serialVersionUID = 6325260821026569762L;

	public PolypeptideInteractionAnnotationLazyLoader(EntityManager objectEntityManager,
			ProteinInteractionAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public PolypeptideInteractionAnnotationLazyLoader(EntityManager objectEntityManager,
			ProteinInteractionAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public PolypeptideInteractionAnnotationLazyLoader(EntityManager objectEntityManager,
			ProteinInteractionAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public PolypeptideInteractionAnnotationLazyLoader(EntityManager objectEntityManager,
			ProteinInteractionAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}
	
	@Override
	public List<PolypeptideInteraction> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "polypeptideInteractions");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "polypeptideInteractions");
		}
	}
}
