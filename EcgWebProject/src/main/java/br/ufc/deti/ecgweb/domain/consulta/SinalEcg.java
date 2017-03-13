/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.consulta;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 19 * hash + (this.tempo != null ? this.tempo.hashCode() : 0);
        hash = 19 * hash + (this.intensidade != null ? this.intensidade.hashCode() : 0);
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
        final SinalEcg other = (SinalEcg) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.tempo != other.tempo && (this.tempo == null || !this.tempo.equals(other.tempo))) {
            return false;
        }
        if (this.intensidade != other.intensidade && (this.intensidade == null || !this.intensidade.equals(other.intensidade))) {
            return false;
        }
        return true;
    }
}
