/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author marcelo
 */
@Entity
@DiscriminatorValue(value = "Medico")
public class Medico extends AbstractCliente{    
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
