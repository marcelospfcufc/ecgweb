/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@Table(name = "sinal")
public class SinalEcg implements Serializable {

    @Id
    private Long id;    
    private Double tempo;    
    private Double intensidade;

    public SinalEcg() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }

    public Double getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(Double intensidade) {
        this.intensidade = intensidade;
    }
}
