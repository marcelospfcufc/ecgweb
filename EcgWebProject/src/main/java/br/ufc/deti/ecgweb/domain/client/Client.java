/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.client;

import br.ufc.deti.ecgweb.domain.exam.AbstractExam;
import br.ufc.deti.ecgweb.domain.exam.Ecg;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@Table(name = "client")
@DiscriminatorColumn(name = "Role")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        
    @Column(nullable = false, unique = false)
    private String name;        
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String rg;
    @Column(nullable = false, unique = true)
    private String email;
    
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "Client_Id")
    private final List<Ecg> exams = new ArrayList<Ecg>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        //TODO
        //Check value
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        //TODO
        //Check value
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        //TODO
        //Check value
        this.email = email;
    }
    
    public void addEcgExam(Ecg exam) {        
        exams.add(exam);
    }
    
    public void delExam(Long examId) {
        AbstractExam examAux = null;
        
        for (AbstractExam examInt : exams) {
            if(Long.compare(examInt.getId(),examId) == 0) {
                examAux = examInt;
            }
        }
        
        if (examAux != null) 
            exams.remove(examAux);
    }

    public List<Ecg> getExams() {
        return exams;
    }
}
