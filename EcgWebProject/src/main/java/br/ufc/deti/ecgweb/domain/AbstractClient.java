/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.domain;

/**
 *
 * @author javadev
 */
public abstract class AbstractClient {
    
    protected String name;
    protected Identification ident;
    protected String email;
    protected String password;
    protected ContactNumber numbers;

    public AbstractClient() {
    }   
    

    public AbstractClient(String name, Identification ident, String email, String password, ContactNumber numbers) {
        this.name = name;
        this.ident = ident;
        this.email = email;
        this.password = password;
        this.numbers = numbers;
    }   
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Identification getIdent() {
        return ident;
    }

    public void setIdent(Identification ident) {
        this.ident = ident;
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

    public ContactNumber getNumbers() {
        return numbers;
    }

    public void setNumbers(ContactNumber numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean equals(Object obj) {
        
        AbstractClient clientAux = (AbstractClient) obj;
        
        return ( (this.email.equals(clientAux.getEmail())) &&
                (this.name.equals(clientAux.getName())) && 
                (this.password.equals(clientAux.getPassword())) && 
                (this.ident.equals(clientAux.getIdent()))                 
                );
    }
    
    
    
}
