package edu.bcm.dldcc.big.nursa.model.reagents;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import edu.bcm.dldcc.big.nursa.model.core.MRNA;

@Entity
public class Primer extends Reagent {


	private static final long serialVersionUID = 800169789212967624L;
	
	private String forwardProbeSequence;
	private String reverseProbeSequence;
	private String probeSequence;
	
	private String probeType;
	private String fluorophore;
	private String ampliconProvider;
	
	public Primer() {
		setType("Primer");
	}
	
	@ManyToMany
	private List<MRNA> mrna = new ArrayList<MRNA>();

	public String getForwardProbeSequence() {
		return forwardProbeSequence;
	}

	public void setForwardProbeSequence(String forwardProbeSequence) {
		this.forwardProbeSequence = forwardProbeSequence;
	}

	public String getReverseProbeSequence() {
		return reverseProbeSequence;
	}

	public void setReverseProbeSequence(String reverseProbeSequence) {
		this.reverseProbeSequence = reverseProbeSequence;
	}

	public String getProbeSequence() {
		return probeSequence;
	}

	public void setProbeSequence(String probeSequence) {
		this.probeSequence = probeSequence;
	}

	public String getProbeType() {
		return probeType;
	}

	public void setProbeType(String probeType) {
		this.probeType = probeType;
	}

	public String getFluorophore() {
		return fluorophore;
	}

	public void setFluorophore(String fluorophore) {
		this.fluorophore = fluorophore;
	}

	public String getAmpliconProvider() {
		return ampliconProvider;
	}

	public void setAmpliconProvider(String ampliconProvider) {
		this.ampliconProvider = ampliconProvider;
	}

	public List<MRNA> getMrna() {
		return mrna;
	}

	public void setMrna(List<MRNA> mrna) {
		this.mrna = mrna;
	} 
	
}
