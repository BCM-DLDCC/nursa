package edu.bcm.dldcc.big.nursa.util;

import org.jboss.seam.faces.event.PhaseIdType;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.security.RestrictAtPhase;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;

import edu.bcm.dldcc.big.nursa.security.identity.Admin;
import edu.bcm.dldcc.big.nursa.security.identity.Editor;
import edu.bcm.dldcc.big.nursa.security.identity.User;

@ViewConfig
public interface PagesConfig {
	static enum Pages {

		@ViewPattern("/editor/*")
		@RestrictAtPhase(PhaseIdType.RESTORE_VIEW)
		@LoginView("/denied.xhtml")
		@Editor
		EDITOR,
		
		
		@ViewPattern("/admin/*")
		@RestrictAtPhase(PhaseIdType.RESTORE_VIEW)
		@LoginView("/denied.xhtml")
		@Admin
		ADMIN,

		@ViewPattern("/account/*")
		@LoginView("/denied.xhtml")
		@User
		ACCOUNT,
	}
}