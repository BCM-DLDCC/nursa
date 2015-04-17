package edu.bcm.dldcc.big.nursa.converter.translational;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.bcm.dldcc.big.nursa.model.common.TranslationalAutoSuggest;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

@RequestScoped
@FacesConverter(value = "translationalAutoSuggestConverter", forClass = TranslationalAutoSuggest.class)
public class TranslationalAutoSuggestConverter implements Converter {
	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		TranslationalAutoSuggest object = null;
		if ((value == null) || (value.equals(""))) {
			return null;
		}
		try {
			object = objectEntityManager.find(TranslationalAutoSuggest.class,
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
		return String.valueOf(((TranslationalAutoSuggest) value).getId());
	}

}
