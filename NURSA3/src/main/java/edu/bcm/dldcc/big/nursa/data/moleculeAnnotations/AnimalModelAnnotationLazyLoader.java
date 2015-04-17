package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.AnimalModelAnnotation;
import edu.bcm.dldcc.big.nursa.model.reagents.AnimalModel;


public class AnimalModelAnnotationLazyLoader extends
AnnotationLazyLoader<AnimalModel, AnimalModelAnnotation> {


	private static final long serialVersionUID = -3291934965708240381L;

	public AnimalModelAnnotationLazyLoader(EntityManager objectEntityManager,
			AnimalModelAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public AnimalModelAnnotationLazyLoader(EntityManager objectEntityManager,
			AnimalModelAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public AnimalModelAnnotationLazyLoader(EntityManager objectEntityManager,
			AnimalModelAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public AnimalModelAnnotationLazyLoader(EntityManager objectEntityManager,
			AnimalModelAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}

	@Override
	public List<AnimalModel> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "animalModels");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "animalModels");
		}
	}
}
