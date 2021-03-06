package edu.bcm.dldcc.big.nursa.controller.layout;

import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import edu.bcm.dldcc.big.nursa.model.Molecule;
import edu.bcm.dldcc.big.nursa.model.common.Synonym;

@RequestScoped
@Named("jsfUtilities")
public class Utility implements Serializable {

	private static final long serialVersionUID = -6041995464703811263L;

	public String createSearchTerms(Molecule molecule) {
		String returns = "(" + molecule.getOfficial().getName() + ")";
		
		for(Synonym synonym : molecule.getSynonymsAsList()) {
			returns = "(" + returns + " OR " + synonym.getName() + ")";
		}
		
		return returns;
	}

}
