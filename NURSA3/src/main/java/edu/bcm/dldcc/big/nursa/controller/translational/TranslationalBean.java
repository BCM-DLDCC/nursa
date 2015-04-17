package edu.bcm.dldcc.big.nursa.controller.translational;

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
import edu.bcm.dldcc.big.nursa.model.common.DOI;
import edu.bcm.dldcc.big.nursa.model.common.DOI_;
import edu.bcm.dldcc.big.nursa.model.reagentAnnotations.ReagentBaseAnnotation;
import edu.bcm.dldcc.big.nursa.model.translational.Translational;
import edu.bcm.dldcc.big.nursa.model.translational.Translational_;
import edu.bcm.dldcc.big.nursa.model.translationalAnnotations.TranslationalBaseAnnotation;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

@Stateful
@ViewScoped
@Named("translationalService")
public class TranslationalBean implements TranslationalInterfaceBean {

	private Long translationalId;

	@Inject
	@Param(required = true)
	private ParamValue<String> doi; // passed DOI

	private Translational selectedTranslational;

	@Inject
	@Named("admin")
	private AdminBean admin;

	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;

	@Inject
	Event<Translational> translationalEvent;

	private Map<String, LazyDataModel<?>> lazyLoaders = new HashMap<String, LazyDataModel<?>>();

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void edit(Translational translational) {
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

	@Override
	@PostConstruct
	public void updateSelectedTranslational() {
		admin.startConversation();

		CriteriaBuilder cb = objectEntityManager.getCriteriaBuilder();

		CriteriaQuery<Translational> cq = cb.createQuery(Translational.class);
		Root<Translational> translational = cq.from(Translational.class);
		Join<Translational, DOI> doi = translational.join(Translational_.doi);

		// filter on DOI
		cq.where(cb.equal(doi.get(DOI_.doi), this.doi.getValue()));
		cq.select(translational);
		TypedQuery<Translational> result = objectEntityManager.createQuery(cq);

		List<Translational> results = result.getResultList();

		// no result found
		if (results.isEmpty()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"No translational was found with that DOI", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			setSelectedTranslational(null);
		}

		// multiple results found (unlikely, I hope!)
		else if (results.size() > 1) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Multiple translationals were found with that DOI", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			setSelectedTranslational(null);
		}

		else {
			// single expected result
			setSelectedTranslational(results.get(0));

			// Load Lazy Loaders
			
			//Create Lazy loading Objects for Molecule
			for(TranslationalBaseAnnotation tba : getSelectedTranslational().getAnnotations().values()) {
				Class<? extends LazyDataModel<?>> ldmClass = tba.getLazyDataModelLoaderClass();
				
				if(ldmClass != null) {
						try {
							this.getLazyLoaders().put(tba.getId().toString(), ldmClass.getConstructor(EntityManager.class, tba.getClass()).newInstance(this.objectEntityManager, tba));
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

	@Override
	public Translational getSelectedTranslational() {
		return this.selectedTranslational;
	}

	@Override
	public void setSelectedTranslational(Translational selectedTranslational) {
		this.selectedTranslational = selectedTranslational;

	}

	@Override
	public Long getTranslationalId() {
		return this.translationalId;
	}

	@Override
	public void setTranslationalId(Long translationalId) {
		this.translationalId = translationalId;
	}

	@Override
	public Map<String, LazyDataModel<?>> getLazyLoaders() {
		return this.lazyLoaders;
	}

	@Override
	public void setLazyLoaders(Map<String, LazyDataModel<?>> lazyLoaders) {
		this.lazyLoaders = lazyLoaders;

	}

}
