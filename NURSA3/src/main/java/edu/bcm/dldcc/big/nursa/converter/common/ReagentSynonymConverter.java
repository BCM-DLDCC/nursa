package edu.bcm.dldcc.big.nursa.converter.common;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.bcm.dldcc.big.nursa.model.common.ReagentSynonym;
import edu.bcm.dldcc.big.nursa.util.qualifier.UserDatabase;

@RequestScoped
@FacesConverter(value = "reagentSynonymConverter", forClass = ReagentSynonym.class)
public class ReagentSynonymConverter implements Converter {
	@Inject
	@UserDatabase
	private EntityManager objectEntityManager;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		ReagentSynonym object = null;
		if ((value == null) || (value.equals(""))) {
			return null;
		}
		object = objectEntityManager.find(ReagentSynonym.class,
				Long.parseLong(value));
		return object;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if ((value == null) || (value.equals(""))) {
			return "";
		}
		return String.valueOf(((ReagentSynonym) value).getId());
	}

}
