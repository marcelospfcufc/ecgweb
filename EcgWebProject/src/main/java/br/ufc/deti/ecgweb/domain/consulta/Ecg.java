/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.consulta;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@DiscriminatorValue(value = "Ecg")
public class Ecg extends AbstractExame {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Exame_Id")
    private final List<Marcacao> marcacoes = new ArrayList<Marcacao>();
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Exame_Id")
    private final List<SinalEcg> sinais = new ArrayList<SinalEcg>();

    public List<Marcacao> getMarcacoes() {
        return marcacoes;
    }
    
    public void adicionarMarcacao(Marcacao marcacao) {
        marcacoes.add(marcacao);
    }

    public void removerMarcacao(Marcacao marcacao) {
        marcacoes.remove(marcacao);
    }

    public void removerMarcacoesByTempo(Double tempo) {
        List<Marcacao> marcacoesAux = new ArrayList<Marcacao>();
        for (Marcacao marcacaoAux : marcacoes) {
            if (Double.compare(tempo, marcacaoAux.getTempo()) == 0) {
                marcacoesAux.add(marcacaoAux);
            }
        }

        for (int i = 0; i < marcacoesAux.size(); i++) {
            marcacoes.remove(marcacoesAux.get(i));
        }
    }

    public List<SinalEcg> getSinais() {
        return sinais;
    }
    
    public List<SinalEcg> getSinaisByTempo(Double tempo) {
        List<SinalEcg> sinaisAux = new ArrayList<SinalEcg>();
        for(SinalEcg sinal : sinais) {
            if(Double.compare(tempo, sinal.getTempo()) == 0) {
                sinaisAux.add(sinal);
            }                
        }        
        return sinaisAux;
    }
    
    public void adicionarSinal(SinalEcg sinal) {
        sinais.add(sinal);
    }
    
    public void removerSinal(SinalEcg sinal) {
        sinais.remove(sinal);
    }
    
    public void removerSinaisByTempo(Double tempo) {
        List<SinalEcg> sinaisAux = getSinaisByTempo(tempo);
        for(SinalEcg sinal : sinaisAux) {
            sinais.remove(sinal);
        }
    }
}
