/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import java.io.Serializable;

/**
 *
 * @author Marcelo Araujo Lima
 */
public class Doctor extends AbstractClient implements Serializable {

    private String crm_;    

    public Doctor(String crm_, String name, Identification ident, String email, String password, ContactNumber numbers) {
        super(name, ident, email, password, numbers);
        this.crm_ = crm_;
    }    

    public String getCrm_() {
        return crm_;
    }

    public void setCrm_(String crm_) {
        this.crm_ = crm_;
    }
}
