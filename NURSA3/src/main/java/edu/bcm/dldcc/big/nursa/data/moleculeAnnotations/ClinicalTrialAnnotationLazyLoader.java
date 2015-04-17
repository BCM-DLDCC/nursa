package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.ClinicalTrialAnnotation;
import edu.bcm.dldcc.big.nursa.model.translational.ClinicalTrial;


public class ClinicalTrialAnnotationLazyLoader extends
AnnotationLazyLoader<ClinicalTrial, ClinicalTrialAnnotation> {


	private static final long serialVersionUID = 2504477305683253913L;

	public ClinicalTrialAnnotationLazyLoader(EntityManager objectEntityManager,
			ClinicalTrialAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public ClinicalTrialAnnotationLazyLoader(EntityManager objectEntityManager,
			ClinicalTrialAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public ClinicalTrialAnnotationLazyLoader(EntityManager objectEntityManager,
			ClinicalTrialAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public ClinicalTrialAnnotationLazyLoader(EntityManager objectEntityManager,
			ClinicalTrialAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}

	@Override
	public List<ClinicalTrial> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "clinicalTrials");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "clinicalTrials");
		}
	}
}
