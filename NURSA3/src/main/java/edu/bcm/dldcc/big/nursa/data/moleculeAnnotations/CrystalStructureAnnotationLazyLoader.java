package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.CrystalStructure;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.CrystalStructureAnnotation;

public class CrystalStructureAnnotationLazyLoader extends
AnnotationLazyLoader<CrystalStructure, CrystalStructureAnnotation> {

	private static final long serialVersionUID = -2801611978585605986L;

	public CrystalStructureAnnotationLazyLoader(
			EntityManager objectEntityManager,
			CrystalStructureAnnotation annotation) {
		super(objectEntityManager, annotation);
	}

	public CrystalStructureAnnotationLazyLoader(
			EntityManager objectEntityManager,
			CrystalStructureAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}

	@Override
	public List<CrystalStructure> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if (this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters,
					"crystalStructures");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta,
					filters, "crystalStructures");
		}
	}

}
