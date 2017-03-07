/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Embeddable
public class Observacao implements Serializable {    
    
    private String observacao;

    public Observacao() {
    }

    public String getValor() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.observacao != null ? this.observacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Observacao other = (Observacao) obj;
        if ((this.observacao == null) ? (other.observacao != null) : !this.observacao.equals(other.observacao)) {
            return false;
        }
        return true;
    }
}
