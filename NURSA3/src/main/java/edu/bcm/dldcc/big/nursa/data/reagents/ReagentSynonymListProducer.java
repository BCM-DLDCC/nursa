package edu.bcm.dldcc.big.nursa.data.reagents;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;


import edu.bcm.dldcc.big.nursa.model.common.ReagentAutoSuggest;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

@Named
@RequestScoped
public class ReagentSynonymListProducer {
	private ReagentAutoSuggest selectedReagent;

	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;

	public void redirect() {
		redirect(getSelectedReagent());
	}

	public void redirect(ReagentAutoSuggest ras) {
		try {
			// put the search term into flash scope to display on the target
			// page
//			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("searchTerm",
//					ras.getName());
			// doi.url should be absolute
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect("./index.jsf?doi=" + ras.getDoi());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ReagentAutoSuggest> completeSymbol(String query) {
		String reagentType = (String) UIComponent
				.getCurrentComponent(FacesContext.getCurrentInstance())
				.getAttributes().get("reagentASType");
		String sql = "";
		if(reagentType == null) {
			sql = "select ID, NAME, DOI, RANK, TYPE, SOURCE from (select ID, NAME, DOI, RANK, TYPE, SOURCE, ROW_NUMBER() OVER (PARTITION BY DOI ORDER BY DOI) AS rn from reagent_as mas where UPPER(NAME) LIKE :query order by RANK asc) where rn <=1 order by RANK asc, NAME asc";
		} else {
			sql = "select ID, NAME, DOI, RANK, TYPE, SOURCE from (select ID, NAME, DOI, RANK, TYPE, SOURCE, ROW_NUMBER() OVER (PARTITION BY DOI ORDER BY DOI) AS rn from reagent_as mas where UPPER(NAME) LIKE :query and UPPER(TYPE) like :type order by RANK asc) where rn <=1 order by RANK asc, NAME asc";
		}
		
		Query returnQuery = objectEntityManager.createNativeQuery(sql, ReagentAutoSuggest.class);
		returnQuery.setParameter("query", "%" + query.toUpperCase() + "%");
		
		if(reagentType != null) {
			returnQuery.setParameter("type", "%" + reagentType.toUpperCase() + "%");
		}
		
		returnQuery.setMaxResults(5);
		return returnQuery.getResultList();

	}

	

	public ReagentAutoSuggest getSelectedReagent() {
		return selectedReagent;
	}

	public void setSelectedReagent(ReagentAutoSuggest selectedReagent) {
		this.selectedReagent = selectedReagent;
	}
}
