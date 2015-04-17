package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.AnimalModelAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.reagents.AnimalModel;

@Entity
public class AnimalModelAnnotation extends MoleculeBaseAnnotation {

	private static final long serialVersionUID = -4996002011220573389L;

	@ManyToMany
	@JoinTable(name = "MBA_ANIMALMODEL")
	private List<AnimalModel> animalModels = new ArrayList<AnimalModel>();

	public AnimalModelAnnotation() {
		setAnnotationType("Animal Models");
		setLazyDataModelLoaderClass(AnimalModelAnnotationLazyLoader.class);
	}

	/**
	 * @return the animalModels
	 */
	public List<AnimalModel> getAnimalModels() {
		return animalModels;
	}

	/**
	 * @param animalModels
	 *            the animalModels to set
	 */
	public void setAnimalModels(List<AnimalModel> animalModels) {
		this.animalModels = animalModels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((animalModels == null) ? 0 : animalModels.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimalModelAnnotation other = (AnimalModelAnnotation) obj;
		if (animalModels == null) {
			if (other.animalModels != null)
				return false;
		} else if (!animalModels.equals(other.animalModels))
			return false;
		return true;
	}

}
