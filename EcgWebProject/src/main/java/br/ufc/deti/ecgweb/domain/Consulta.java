/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        
    
    @Embedded    
    private Receita receita;
    
    @ElementCollection
    @CollectionTable(name = "observacao")
    private final List<Observacao> observacoes = new ArrayList<Observacao>();
    
    @Column(name = "data_consulta")    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)        
    private LocalDateTime dataConsulta;        
    
    @Column(name = "data_criacao")    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)    
    private LocalDateTime dataCriacao;    
    
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "Consulta_Id")
    private final List<Ecg> exames = new ArrayList<Ecg>();

    public Long getId() {        
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void adicionarObservacao(Observacao observacao) {
        observacoes.add(observacao);
    }
    
    public void removerObservacao(Observacao observacao) {
        observacoes.remove(observacao);
    }

    public List<Observacao> getObservacoes() {
        return observacoes;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }
    
    public void adicionarExame(Ecg exame) {        
        exames.add(exame);
    }
    
    public void removerExame(Long exameId) {
        AbstractExame exameAux = null;
        
        for (AbstractExame exameInt : exames) {
            if(exameInt.getId() == exameId) {
                exameAux = exameInt;
            }
        }
        
        if (exameAux != null) 
            exames.remove(exameAux);
    }

    public List<Ecg> getExames() {
        return exames;
    }
    
    public Ecg getExameById(Long id) {
        
        Ecg ecgAux = null;
        
        for(Ecg ecg : exames) {
            if( ecg.getId() == id ) {
                ecgAux = ecg;
            }
        }
        return ecgAux;
    }
}