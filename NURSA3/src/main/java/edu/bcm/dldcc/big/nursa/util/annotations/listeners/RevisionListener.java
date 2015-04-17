package edu.bcm.dldcc.big.nursa.util.annotations.listeners;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.inject.Default;
import javax.enterprise.util.AnnotationLiteral;
import javax.naming.NamingException;

import org.jboss.seam.security.Identity;

import edu.bcm.dldcc.big.nursa.util.Resources;
import edu.bcm.dldcc.big.nursa.util.model.AuditingRevision;
import edu.bcm.dldcc.big.nursa.util.qualifier.NamedLiteral;

/**
 * @author pew
 * 
 */
public class RevisionListener implements org.hibernate.envers.RevisionListener {

	/*
	 * 
	 * 
	 * 
	 */
	public RevisionListener() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.envers.RevisionListener#newRevision(java.lang.Object)
	 */
	@Override
	public void newRevision(Object revisionEntity) {
		AuditingRevision revision = (AuditingRevision) revisionEntity;
		try {
			@SuppressWarnings("serial")
			Identity identity = Resources.lookup(Identity.class, new AnnotationLiteral<Default>() {
			});
			if (identity.isLoggedIn()) {
				revision.setUsername(identity.getUser().getId());
				revision.setRationale(Resources.lookup(String.class, new NamedLiteral("auditRationale")));
			} else {
				this.enterSystemInformation(revision);
			}
		} catch (ContextNotActiveException e) {
			this.enterSystemInformation(revision);
		} catch (RuntimeException e) {
			if (e.getCause() instanceof NamingException) {
				this.enterSystemInformation(revision);
			} else {
				throw e;
			}
		}

	}

	protected void enterSystemInformation(AuditingRevision revision) {
		revision.setUsername("system");
		revision.setRationale("System management");
	}

}
