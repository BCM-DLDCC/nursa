package edu.bcm.dldcc.big.nursa.model.translational;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import edu.bcm.dldcc.big.nursa.model.common.Organization;
import edu.bcm.dldcc.big.nursa.model.common.Reference;


@Entity
public class ChemicalProperty implements Serializable {

	private static final long serialVersionUID = 8118433277277172126L;

	@Id
	@GeneratedValue(generator = "chemPropertySequencer")
	@SequenceGenerator(name = "chemPropertySequencer", sequenceName = "CHEMPROPERTY_SEQ")
	private Long id;
	
	private String kind;
	
	@Column(length=2800)
	private String value;
	
	@ManyToOne
	private Organization source;
	
	@ManyToOne
	private Reference reference;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Organization getSource() {
		return source;
	}

	public void setSource(Organization source) {
		this.source = source;
	}

	/**
	 * @return the reference
	 */
	public Reference getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	
	
}
