package edu.bcm.dldcc.big.nursa.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class Reference implements Serializable {

	private static final long serialVersionUID = -6609521722907109346L;

	@Id
	@GeneratedValue(generator = "referenceSequencer")
	@SequenceGenerator(name = "referenceSequencer", sequenceName = "REFERENCE_SEQ")
	private Long id;
	
	private String pubmedId;
	
	@Column(length = 2000)
	private String nursaCitation;
	
	
	@Transient
	private String volume;
	
	@Transient
	private String pagination;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Article article;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getPubmedId() {
		return pubmedId;
	}

	
	public void setPubmedId(String pubmedId) {
		this.pubmedId = pubmedId;
	}

	public String getNursaCitation() {
		return nursaCitation;
	}

	public void setNursaCitation(String nursaCitation) {
		this.nursaCitation = nursaCitation;
	}

	
	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	public String getVolume() {
		return (article!=null)?article.getVolume():null;
	}


	public String getPagination() {
		return (article!=null)?article.getPagination():null;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nursaCitation == null) ? 0 : nursaCitation.hashCode());
		result = prime * result
				+ ((pubmedId == null) ? 0 : pubmedId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reference other = (Reference) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nursaCitation == null) {
			if (other.nursaCitation != null)
				return false;
		} else if (!nursaCitation.equals(other.nursaCitation))
			return false;
		if (pubmedId == null) {
			if (other.pubmedId != null)
				return false;
		} else if (!pubmedId.equals(other.pubmedId))
			return false;
		return true;
	}

	
}
