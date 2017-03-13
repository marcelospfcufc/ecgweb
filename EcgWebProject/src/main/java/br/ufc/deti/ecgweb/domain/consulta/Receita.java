/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.consulta;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Embeddable
public class Receita implements Serializable {    
    private String receita;

    public Receita() {
    }

    public String getReceita() {
        return receita;
    }

    public void setReceita(String receita) {
        this.receita = receita;
    }
}
