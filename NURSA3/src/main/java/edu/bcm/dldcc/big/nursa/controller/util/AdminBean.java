package edu.bcm.dldcc.big.nursa.controller.util;

import javax.ejb.Startup;
import javax.enterprise.context.Conversation;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Startup
@Named("admin")
public class AdminBean {
	
	@Inject
	private Conversation conversation;
	
	public void startConversation() {
		
		if (this.conversation.isTransient()) {
			this.conversation.begin();
			this.conversation.setTimeout(1800000L); // Apollo: seems to work, but should this not be per conversation?
		}
	}
	
	public void endConversation() {
		if(!this.conversation.isTransient()) {
			this.conversation.end();
		}
	}
}
