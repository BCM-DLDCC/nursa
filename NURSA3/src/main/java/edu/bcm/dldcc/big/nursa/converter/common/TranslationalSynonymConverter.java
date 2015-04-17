package edu.bcm.dldcc.big.nursa.converter.common;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.bcm.dldcc.big.nursa.model.common.TranslationalSynonym;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

@RequestScoped
@FacesConverter(value = "translationalSynonymConverter", forClass = TranslationalSynonym.class)
public class TranslationalSynonymConverter implements Converter {
	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		TranslationalSynonym object = null;
		if ((value == null) || (value.equals(""))) {
			return null;
		}
		object = objectEntityManager.find(TranslationalSynonym.class,
				Long.parseLong(value));
		return object;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if ((value == null) || (value.equals(""))) {
			return "";
		}
		return String.valueOf(((TranslationalSynonym) value).getId());
	}

}
