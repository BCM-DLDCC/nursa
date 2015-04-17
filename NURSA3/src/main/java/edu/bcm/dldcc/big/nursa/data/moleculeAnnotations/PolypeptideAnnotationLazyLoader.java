package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.Polypeptide;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.PolypeptideAnnotation;


public class PolypeptideAnnotationLazyLoader extends
AnnotationLazyLoader<Polypeptide, PolypeptideAnnotation> {

	private static final long serialVersionUID = 6326569725260821062L;

	public PolypeptideAnnotationLazyLoader(EntityManager objectEntityManager,
			PolypeptideAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public PolypeptideAnnotationLazyLoader(EntityManager objectEntityManager,
			PolypeptideAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public PolypeptideAnnotationLazyLoader(EntityManager objectEntityManager,
			PolypeptideAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public PolypeptideAnnotationLazyLoader(EntityManager objectEntityManager,
			PolypeptideAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}
	

	@Override
	public List<Polypeptide> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "polypeptides");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "polypeptides");
		}
	}
}
