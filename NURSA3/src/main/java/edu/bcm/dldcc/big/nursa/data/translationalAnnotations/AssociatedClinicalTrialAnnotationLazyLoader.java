package edu.bcm.dldcc.big.nursa.data.translationalAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.translational.ClinicalTrial;
import edu.bcm.dldcc.big.nursa.model.translationalAnnotations.AssociatedClinicalTrialAnnotation;

public class AssociatedClinicalTrialAnnotationLazyLoader extends
		AnnotationLazyLoader<ClinicalTrial, AssociatedClinicalTrialAnnotation> {


	private static final long serialVersionUID = -5838630605926263988L;

	public AssociatedClinicalTrialAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedClinicalTrialAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	public AssociatedClinicalTrialAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedClinicalTrialAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public AssociatedClinicalTrialAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedClinicalTrialAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public AssociatedClinicalTrialAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedClinicalTrialAnnotation annotation, Map<String, String> filters, String distinctColumn) {
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
