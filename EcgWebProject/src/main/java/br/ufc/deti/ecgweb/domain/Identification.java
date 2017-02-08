/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

/**
 *
 * @author javadev
 */
public class Identification {

    private String rg_;
    private String cpf_;

    public String getRg_() {
        return rg_;
    }

    public void setRg_(String rg_) {
        this.rg_ = rg_;
    }

    public String getCpf_() {
        return cpf_;
    }

    public void setCpf_(String cpf_) {
        this.cpf_ = cpf_;
    }

    @Override
    public boolean equals(Object obj) {        
        Identification identAux = (Identification) obj;
        return (identAux.cpf_.equals(this.cpf_) && identAux.rg_.equals(this.rg_));
    }
    
    
}
