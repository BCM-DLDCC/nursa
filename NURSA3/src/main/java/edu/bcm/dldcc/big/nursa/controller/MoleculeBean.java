package edu.bcm.dldcc.big.nursa.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.param.ParamValue;
import org.primefaces.model.LazyDataModel;

import edu.bcm.dldcc.big.nursa.controller.util.AdminBean;
import edu.bcm.dldcc.big.nursa.model.Molecule;
import edu.bcm.dldcc.big.nursa.model.Molecule_;
import edu.bcm.dldcc.big.nursa.model.common.DOI;
import edu.bcm.dldcc.big.nursa.model.common.DOI_;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.MIRNAAnnotation;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.MoleculeBaseAnnotation;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.SpeciesAnnotation;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.*;

/**
 * The implementation of the {@link MoleculeInterfaceBean}.
 * 
 * @author jeremyeaston-marks
 * 
 */
@Stateful
@ViewScoped
@Named("moleculeService")
public class MoleculeBean implements MoleculeInterfaceBean {

	private Long moleculeId;

	@Inject
	@Param(required = true)
	private ParamValue<String> doi; // passed DOI

	private Molecule selectedMolecule;

	@Inject
	@Named("admin")
	private AdminBean admin;

	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;

	@Inject
	Event<Molecule> moleculeEvent;
	
	private Map<String, LazyDataModel<?>> lazyLoaders = new HashMap<String, LazyDataModel<?>>();

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void edit(Molecule molecule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub

	}

	// updates the selected molecule based on a passed DOI
	@Override
	@PostConstruct
	public void updateSelectedMolecule() {
		
		admin.startConversation();
		
		CriteriaBuilder cb = objectEntityManager.getCriteriaBuilder();

		CriteriaQuery<Molecule> cq = cb.createQuery(Molecule.class);
		Root<Molecule> molecule = cq.from(Molecule.class);
		Join<Molecule, DOI> doi = molecule.join(Molecule_.doi);

		// filter on DOI
		cq.where(cb.equal(doi.get(DOI_.doi), this.doi.getValue()));
		cq.select(molecule);
		TypedQuery<Molecule> result = objectEntityManager.createQuery(cq);

		List<Molecule> results = result.getResultList();

		// no result found
		if (results.isEmpty()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"No molecule was found with that DOI", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			setSelectedMolecule(null);
		}

		// multiple results found (unlikely, I hope!)
		else if (results.size() > 1) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Multiple molecules were found with that DOI", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			setSelectedMolecule(null);
		}

		else {
			// single expected result
			setSelectedMolecule(results.get(0));
			
			//Create Lazy loading Objects for Molecule
			for(MoleculeBaseAnnotation mba : getSelectedMolecule().getAnnotations().values()) {
				Class<? extends LazyDataModel<?>> ldmClass = mba.getLazyDataModelLoaderClass();
				
				if(ldmClass != null) {
						try {
							this.getLazyLoaders().put(mba.getId().toString(), ldmClass.getConstructor(EntityManager.class, mba.getClass()).newInstance(this.objectEntityManager, mba));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
			
			//Create Lazy Loading Objects for Species
			for(SpeciesAnnotation sa : getSelectedMolecule().getSpeciesAnnotations().values()) {
				for(MoleculeBaseAnnotation mba : sa.getAnnotations().values()) {
					
					Class<? extends LazyDataModel<?>> ldmClass = mba.getLazyDataModelLoaderClass();
					
					if(ldmClass != null) {
						try {
							
							//construct specialized lazy loaders here
							//(results restricted to certain organizations; unique resultsets based on a subset of fields)
							
							//MRNA
							if(mba instanceof MRNAAnnotation) {
								//MRNA with distinct rnaAccessionVersion
								this.getLazyLoaders().put(mba.getId().toString(), ldmClass.getConstructor(EntityManager.class, mba.getClass(), String.class).newInstance(this.objectEntityManager, mba, "rnaAccessionVersion"));
								
								Map<String, String> filter = new HashMap<String, String>();
								//Add Ensembl only resources filter
								filter.put("dataResources.organization.abbreviation", "Ensembl");
								this.getLazyLoaders().put(mba.getId().toString() + "-ENSEMBL", ldmClass.getConstructor(EntityManager.class, mba.getClass(), Map.class).newInstance(this.objectEntityManager, mba, filter));

								//Add Vega only MRNA resources only filter
								filter.put("dataResources.organization.abbreviation", "Vega");
								this.getLazyLoaders().put(mba.getId().toString() + "-VEGA", ldmClass.getConstructor(EntityManager.class, mba.getClass(), Map.class).newInstance(this.objectEntityManager, mba, filter));
							} else if(mba instanceof PolypeptideAnnotation) {
								//Polypeptide with distinct RefSeqId
								this.getLazyLoaders().put(mba.getId().toString(), ldmClass.getConstructor(EntityManager.class, mba.getClass(), String.class).newInstance(this.objectEntityManager, mba, "refseqId"));
							} else if(mba instanceof MIRNAAnnotation) {
								//miRNA with distinct mirBaseId
								this.getLazyLoaders().put(mba.getId().toString(), ldmClass.getConstructor(EntityManager.class, mba.getClass(), String.class).newInstance(this.objectEntityManager, mba, "mirBaseId"));
							} else if(mba instanceof ProteinInteractionAnnotation) {
								//Protein Interaction with distinct id
								this.getLazyLoaders().put(mba.getId().toString(), ldmClass.getConstructor(EntityManager.class, mba.getClass(), String.class).newInstance(this.objectEntityManager, mba, "id"));
							} else {
								this.getLazyLoaders().put(mba.getId().toString(), ldmClass.getConstructor(EntityManager.class, mba.getClass()).newInstance(this.objectEntityManager, mba));
							}
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	// SETTER AND GETTERS

	@Override
	public Molecule getSelectedMolecule() {
		return selectedMolecule;
	}

	@Override
	public void setSelectedMolecule(Molecule selectedMolecule) {
		this.selectedMolecule = selectedMolecule;
	}

	@Override
	public Long getMoleculeId() {
		return moleculeId;
	}

	@Override
	public void setMoleculeId(Long moleculeId) {
		this.moleculeId = moleculeId;
	}
	@Override
	public Map<String, LazyDataModel<?>> getLazyLoaders() {
		return lazyLoaders;
	}
	@Override
	public void setLazyLoaders(Map<String, LazyDataModel<?>> lazyLoaders) {
		this.lazyLoaders = lazyLoaders;
	}
}
