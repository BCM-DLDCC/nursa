package edu.bcm.dldcc.big.nursa.data.translationalAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.PolypeptideModification;
import edu.bcm.dldcc.big.nursa.model.translationalAnnotations.DatasetTranslationalAnnotation;


public class DatasetTranslationalAnnotationLazyLoader extends
AnnotationLazyLoader<PolypeptideModification, DatasetTranslationalAnnotation> {

	private static final long serialVersionUID = 6826967251032652602L;

	public DatasetTranslationalAnnotationLazyLoader(EntityManager objectEntityManager,
			DatasetTranslationalAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public DatasetTranslationalAnnotationLazyLoader(EntityManager objectEntityManager,
			DatasetTranslationalAnnotation annotation, String distinctColumn) {
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
