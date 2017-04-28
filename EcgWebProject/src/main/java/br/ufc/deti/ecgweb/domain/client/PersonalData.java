/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain.client;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Marcelo Araujo Lima
 */
@Embeddable
public class PersonalData {
    private String name;        
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false, unique = false)
    private String rg;
    @Column(nullable = false, unique = false)
    private String email;    
    @Column(nullable = false)
    private GenderType gender;

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
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }    
}
