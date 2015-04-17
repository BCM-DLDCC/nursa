package edu.bcm.dldcc.big.nursa.data.common;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.bcm.dldcc.big.nursa.model.common.TranslationalAutoSuggest;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

@Named
@RequestScoped
public class TranslationalSynonymListProducer {
	private TranslationalAutoSuggest selectedTranslational;

	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;

	public void redirect() {
		redirect(getSelectedTranslational());
	}

	public void redirect(TranslationalAutoSuggest mas) {
		try {
			// put the search term into flash scope to display on the target
			// page
			if(!mas.getName().equals(mas.getOfficial())) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.put("searchTerm", mas.getName());
			}
			// doi.url should be absolute
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("./index.jsf?doi=" + mas.getDoi());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<TranslationalAutoSuggest> completeSymbol(String query) {
		String translationalType = (String) UIComponent
				.getCurrentComponent(FacesContext.getCurrentInstance())
				.getAttributes().get("translationalType");
		
		String sql = "";
		if (translationalType == null) {
			sql = "select ID, NAME, OFFICIAL, DOI, RANK, TYPE from (select ID, NAME, OFFICIAL, DOI, RANK, TYPE, ROW_NUMBER() OVER (PARTITION BY OFFICIAL ORDER BY OFFICIAL) AS rn from translational_as mas where UPPER(NAME) LIKE :query order by RANK asc) where rn <=1 order by RANK asc, NAME asc";
		} else {
			sql = "select ID, NAME, OFFICIAL, DOI, RANK, TYPE from (select ID, NAME, OFFICIAL, DOI, RANK, TYPE, ROW_NUMBER() OVER (PARTITION BY OFFICIAL ORDER BY OFFICIAL) AS rn from translational_as mas where UPPER(NAME) LIKE :query AND type = :tranType order by RANK asc) where rn <=1 order by RANK asc, NAME asc";
		}
		Query returnQuery = objectEntityManager.createNativeQuery(sql,
				TranslationalAutoSuggest.class);
		returnQuery.setParameter("query", "%" + query.toUpperCase() + "%");
		if (translationalType != null) {
			returnQuery.setParameter("tranType", translationalType);
		}
		
		returnQuery.setMaxResults(5);
		
		List<TranslationalAutoSuggest> rawResults = returnQuery.getResultList();
		return rawResults;

	}

	public TranslationalAutoSuggest getSelectedTranslational() {
		return selectedTranslational;
	}

	public void setSelectedTranslational(
			TranslationalAutoSuggest selectedTranslational) {
		this.selectedTranslational = selectedTranslational;
	}
}
