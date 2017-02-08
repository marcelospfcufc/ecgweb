/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.infrastructure;

import br.ufc.deti.ecgweb.domain.*;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Entity
@Table(name="client")
public class ClientEntity implements Serializable {   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "name", nullable = false)    
    private String name;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ClientCategory category;
    
    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "research_database_id", nullable = true)
    private ResearchDatabase researchDatabase;       */
    
    //@OneToMany(fetch = FetchType.EAGER)
    //@JoinColumn(name = "client_id", referencedColumnName = "id")
    //private Collection<Exam> exams;

    public ClientEntity() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientCategory getCategory() {
        return category;
    }

    public void setCategory(ClientCategory category) {
        this.category = category;
    }

    /*public ResearchDatabase getResearchDatabase() {
        return researchDatabase;
    }

    public void setResearchDatabase(ResearchDatabase researchDatabase) {
        this.researchDatabase = researchDatabase;
    }

    public Collection<Exam> getExams() {
        return exams;
    }

    public void setExams(Collection<Exam> exams) {
        this.exams = exams;
    }
    
    public void addExam(Exam exam) {
        exams.add(exam);
    }*/
    
    
    
    
}
