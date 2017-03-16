/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.client;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@DiscriminatorValue(value = ClientType.DOCTOR)
public class Doctor extends AbstractClient {

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
