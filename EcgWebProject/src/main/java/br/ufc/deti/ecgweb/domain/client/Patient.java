package br.ufc.deti.ecgweb.domain.client;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@DiscriminatorValue(value = RoleType.PATIENT)
public class Patient extends Client{    
}
