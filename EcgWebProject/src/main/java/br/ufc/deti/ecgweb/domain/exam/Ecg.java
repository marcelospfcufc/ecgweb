/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.exam;

import br.ufc.deti.ecgweb.domain.appointment.AbstractExam;
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
public class Ecg extends AbstractExam {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Exame_Id")
    private final List<Annotation> marcacoes = new ArrayList<Annotation>();
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Exame_Id")
    private final List<EcgSignal> sinais = new ArrayList<EcgSignal>();
    
    private Long sampleRate;

    public List<Annotation> getMarcacoes() {
        return marcacoes;
    }
    
    public void adicionarMarcacao(Annotation marcacao) {
        marcacoes.add(marcacao);
    }

    public void removerMarcacao(Annotation marcacao) {
        marcacoes.remove(marcacao);
    }

    public void removerMarcacoesByTempo(Double tempo) {
        List<Annotation> marcacoesAux = new ArrayList<Annotation>();
        for (Annotation marcacaoAux : marcacoes) {
            if (Double.compare(tempo, marcacaoAux.getTempo()) == 0) {
                marcacoesAux.add(marcacaoAux);
            }
        }

        for (int i = 0; i < marcacoesAux.size(); i++) {
            marcacoes.remove(marcacoesAux.get(i));
        }
    }

    public List<EcgSignal> getSinais() {
        return sinais;
    }
    
    public List<EcgSignal> getSinaisByTempo(Double tempo) {
        List<EcgSignal> sinaisAux = new ArrayList<EcgSignal>();
        for(EcgSignal sinal : sinais) {
            if(Double.compare(tempo, sinal.getTempo()) == 0) {
                sinaisAux.add(sinal);
            }                
        }        
        return sinaisAux;
    }
    
    public void adicionarSinal(EcgSignal sinal) {
        sinais.add(sinal);
    }
    
    public void removerSinal(EcgSignal sinal) {
        sinais.remove(sinal);
    }
    
    public void removerSinaisByTempo(Double tempo) {
        List<EcgSignal> sinaisAux = getSinaisByTempo(tempo);
        for(EcgSignal sinal : sinaisAux) {
            sinais.remove(sinal);
        }
    }
}
