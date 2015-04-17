package edu.bcm.dldcc.big.nursa.util.filter;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import edu.bcm.dldcc.big.nursa.model.common.Reference;

/**
 * Component to help with Reference in UI dialogs/dialog
 * @author mcowiti
 *
 */
@Named("referenceUtil")
@ViewScoped
public class ReferenceUtil implements Serializable{
	
	private static final long serialVersionUID = 6802388986959747782L;
	private Reference reference;

	
	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		
		this.reference = reference;
	}
}
