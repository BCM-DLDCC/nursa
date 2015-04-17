package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.Ligand;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.LigandAnnotation;


public class LigandAnnotationLazyLoader extends
AnnotationLazyLoader<Ligand, LigandAnnotation> {

	private static final long serialVersionUID = -4706935036679891983L;

	public LigandAnnotationLazyLoader(EntityManager objectEntityManager,
			LigandAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public LigandAnnotationLazyLoader(EntityManager objectEntityManager,
			LigandAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public LigandAnnotationLazyLoader(EntityManager objectEntityManager,
			LigandAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public LigandAnnotationLazyLoader(EntityManager objectEntityManager,
			LigandAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}

	@Override
	public List<Ligand> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "ligands");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "ligands");
		}
	}
}
