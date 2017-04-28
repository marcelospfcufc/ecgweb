package br.ufc.deti.ecgweb.domain.client;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@DiscriminatorValue(value = RoleType.DOCTOR)
public class Doctor extends Client {       
    
    @Column(nullable = true, unique = false)
    private String crm;
    
    public String getCrm() {        
        return crm;
    }

    public void setCrm(String crm) {
        //TODO
        //VALIDAR
        this.crm = crm;
    }
}
