package edu.bcm.dldcc.big.nursa.converter.reagents;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.bcm.dldcc.big.nursa.model.common.ReagentAutoSuggest;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

@RequestScoped
@FacesConverter(value = "reagentAutoSuggestConverter", forClass = ReagentAutoSuggest.class)
public class ReagentAutoSuggestConverter implements Converter {
	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		ReagentAutoSuggest object = null;
		if ((value == null) || (value.equals(""))) {
			return null;
		}
		try {
			object = objectEntityManager.find(ReagentAutoSuggest.class,
					Long.parseLong(value));
		} catch (NumberFormatException nfe) {
			return null;
		}
		return object;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if ((value == null) || (value.equals(""))) {
			return "";
		}
		return String.valueOf(((ReagentAutoSuggest) value).getId());
	}

}
