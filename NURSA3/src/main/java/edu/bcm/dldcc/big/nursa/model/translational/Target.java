package edu.bcm.dldcc.big.nursa.model.translational;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import edu.bcm.dldcc.big.nursa.model.common.Evidence;
import edu.bcm.dldcc.big.nursa.model.core.Polypeptide;

@Entity
public class Target implements Serializable {
	private static final long serialVersionUID = -6725316751346606577L;

	@Id
	@GeneratedValue(generator = "targetSequencer")
	@SequenceGenerator(name = "targetSequencer", sequenceName = "TARGET_SEQ")
	private Long id;

	private Long targetId;
	private String name;
	@ManyToMany
	private List<Polypeptide> polypeptide = new ArrayList<Polypeptide>();
	private String knownAction;
	private String action;
	@ManyToOne
	private Evidence evidence;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Polypeptide> getPolypeptide() {
		return polypeptide;
	}

	public void setPolypeptide(List<Polypeptide> polypeptide) {
		this.polypeptide = polypeptide;
	}

	public String getKnownAction() {
		return knownAction;
	}

	public void setKnownAction(String knownAction) {
		this.knownAction = knownAction;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Evidence getEvidence() {
		return evidence;
	}

	public void setEvidence(Evidence evidence) {
		this.evidence = evidence;
	}

}
