package edu.bcm.dldcc.big.nursa.security;

import java.io.IOException;

import javax.enterprise.event.Observes;
import javax.faces.context.ExternalContext;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;
import org.jboss.seam.security.events.LoginFailedEvent;
import org.jboss.seam.security.events.NotAuthorizedEvent;
import org.jboss.seam.security.events.NotLoggedInEvent;

import edu.bcm.dldcc.big.nursa.security.identity.Admin;
import edu.bcm.dldcc.big.nursa.security.identity.Editor;
import edu.bcm.dldcc.big.nursa.security.identity.User;

public class SecurityRules {
	public @Secures
	@User
	boolean userChecker(Identity identity) {
		return !(identity.getUser() == null);
	}

	public @Secures
	@Admin
	boolean adminCheck(Identity identity) {
		if (identity.hasRole("admin", "NURSA", "GROUP")) {
			return true;
		}
		return false;
	}

	public @Secures
	@Editor
	boolean editorCheck(Identity identity) {
		if (identity.hasRole("editor", "NURSA", "GROUP")) {
			return true;
		}
		return false;
	}

	public void loginFailed(@Observes LoginFailedEvent event,
			ExternalContext context) throws IOException {
		context.redirect(context.getRequestContextPath() + "/index.jsf");
	}

	public void notLoggedIn(@Observes NotLoggedInEvent event,
			ExternalContext context) throws IOException {
		context.redirect(context.getRequestContextPath() + "/denied.jsf");
	}

	public void unauthorized(@Observes NotAuthorizedEvent event,
			ExternalContext context) throws IOException {

		context.redirect(context.getRequestContextPath() + "/denied.jsf");
	}

}
