package edu.bcm.dldcc.big.nursa.controller.reagents;

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
import edu.bcm.dldcc.big.nursa.model.reagents.Reagent;
import edu.bcm.dldcc.big.nursa.model.reagents.Reagent_;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

@Stateful
@ViewScoped
@Named("reagentService")
public class ReagentBean implements ReagentInterfaceBean {

	private Long reagentId;

	@Inject
	@Param(required = true)
	private ParamValue<String> doi; // passed DOI

	private Reagent selectedReagent;

	@Inject
	@Named("admin")
	private AdminBean admin;

	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;

	@Inject
	Event<Reagent> reagentEvent;

	private Map<String, LazyDataModel<?>> lazyLoaders = new HashMap<String, LazyDataModel<?>>();

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void edit(Reagent reagent) {
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
	public void updateSelectedReagent() {
		admin.startConversation();

		CriteriaBuilder cb = objectEntityManager.getCriteriaBuilder();

		CriteriaQuery<Reagent> cq = cb.createQuery(Reagent.class);
		Root<Reagent> reagent = cq.from(Reagent.class);
		Join<Reagent, DOI> doi = reagent.join(Reagent_.doi);

		// filter on DOI
		cq.where(cb.equal(doi.get(DOI_.doi), this.doi.getValue()));
		cq.select(reagent);
		TypedQuery<Reagent> result = objectEntityManager.createQuery(cq);

		List<Reagent> results = result.getResultList();

		// no result found
		if (results.isEmpty()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"No reagent was found with that DOI", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			setSelectedReagent(null);
		}

		// multiple results found (unlikely, I hope!)
		else if (results.size() > 1) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Multiple reagents were found with that DOI", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			setSelectedReagent(null);
		}

		else {
			// single expected result
			setSelectedReagent(results.get(0));

			// Load Lazy Loaders
			//Create Lazy loading Objects for Molecule
			for(ReagentBaseAnnotation rba : getSelectedReagent().getAnnotations().values()) {
				Class<? extends LazyDataModel<?>> ldmClass = rba.getLazyDataModelLoaderClass();
				
				if(ldmClass != null) {
						try {
							this.getLazyLoaders().put(rba.getId().toString(), ldmClass.getConstructor(EntityManager.class, rba.getClass()).newInstance(this.objectEntityManager, rba));
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
	public Reagent getSelectedReagent() {
		return this.selectedReagent;
	}

	@Override
	public void setSelectedReagent(Reagent selectedReagent) {
		this.selectedReagent = selectedReagent;

	}

	@Override
	public Long getReagentId() {
		return this.reagentId;
	}

	@Override
	public void setReagentId(Long reagentId) {
		this.reagentId = reagentId;
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
