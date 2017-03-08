/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@DiscriminatorValue(value = "Ecg")
public class Ecg extends AbstractExame{
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)    
    @JoinColumn(name = "Exame_Id")
    private final List<Marcacao> marcacoes = new ArrayList<Marcacao>();

    public List<Marcacao> getMarcacoes() {
        return marcacoes;
    }
    
    public void adicionarMarcacao(Marcacao marcacao) {
        marcacoes.add(marcacao);
    }
    
    public void removerMarcacao(Marcacao marcacao) {
        marcacoes.remove(marcacao);
    }
}
