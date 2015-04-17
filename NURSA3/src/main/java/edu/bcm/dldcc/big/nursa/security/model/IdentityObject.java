package edu.bcm.dldcc.big.nursa.security.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.jboss.seam.security.annotations.management.IdentityEntity;
import static org.jboss.seam.security.annotations.management.EntityType.IDENTITY_OBJECT;
import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

/**
 * This entity contains identity objects, e.g. users and groups
 *
 * @author Shane Bryzak
 */
@IdentityEntity(IDENTITY_OBJECT)
@Entity
public class IdentityObject implements Serializable {
    private static final long serialVersionUID = -4623023512038059728L;

    private Long id;
    
    private String name;
    
    private IdentityObjectType type;

    @Id
	@GeneratedValue(generator = "identityObjectSequencer")
	@SequenceGenerator(name = "identityObjectSequencer", sequenceName = "IDENTITYOBJECT_SEQ", initialValue=100)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @IdentityProperty(PropertyType.NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.TYPE)
    @JoinColumn(name = "IDENTITY_OBJECT_TYPE_ID")
    public IdentityObjectType getType() {
        return type;
    }

    public void setType(IdentityObjectType type) {
        this.type = type;
    }

}