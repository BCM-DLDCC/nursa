package edu.bcm.dldcc.big.nursa.model.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import edu.bcm.dldcc.big.nursa.model.Molecule;
import edu.bcm.dldcc.big.nursa.model.common.Evidence;

@Entity
@Table(name = "molInt")
public class MoleculeInteraction implements Serializable {
	private static final long serialVersionUID = -7914291521395671184L;

	@Id
	@GeneratedValue(generator = "molInterSequencer")
	@SequenceGenerator(name = "molInterSequencer", sequenceName = "MOLINTER_SEQ")
	private Long id;
	@ManyToOne
	private Evidence evidence;

	@ManyToOne
	private Molecule source;

	@ManyToMany
	private List<Molecule> interactions = new ArrayList<Molecule>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Evidence getEvidence() {
		return evidence;
	}

	public void setEvidence(Evidence evidence) {
		this.evidence = evidence;
	}

	public Molecule getSource() {
		return source;
	}

	public void setSource(Molecule source) {
		this.source = source;
	}

	public List<Molecule> getInteractions() {
		return interactions;
	}

	public void setInteractions(List<Molecule> interactions) {
		this.interactions = interactions;
	}
	
	
}
